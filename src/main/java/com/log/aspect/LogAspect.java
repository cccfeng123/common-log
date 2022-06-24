package com.log.aspect;

import com.log.properties.LogAspectProperties;
import com.log.model.AuditLog;
import com.log.service.LogOutPutService;
import com.log.util.IpUtils;
import com.log.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @author ：JH
 * @date ：2022/5/16 16:41
 * @describe：日志切面
 */
@Aspect
@Component
public class LogAspect implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    LogOutPutService logOutPutService;
    public LogAspect() {
    }

    @Bean
    @ConditionalOnMissingBean
    public LogAspectProperties logAspectProperties() {
        return new LogAspectProperties();
    }


    @Pointcut("@annotation(com.log.anno.Log)")
    private void objectPointcut() {
    }
    @Pointcut("@within(com.log.anno.Log)")
    private void methodPointcut() {
    }



    @Around("objectPointcut()||methodPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

        assert attributes != null;

        HttpServletRequest request = attributes.getRequest();
        Object result = pjp.proceed();
        AuditLog auditLog = this.convertToAuditLog(pjp, request, startTime, result);
        this.logOutPutService.outPutLog(auditLog);
        return result;
    }

    private AuditLog convertToAuditLog(ProceedingJoinPoint pjp, HttpServletRequest request, long startTime, Object result) {
        String userId = request.getHeader("userId");
        String traceId = MDC.get("traceId");
        AuditLog auditLog = new AuditLog();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        long endTime = System.currentTimeMillis();
        auditLog.setUserId(userId);
        auditLog.setTraceId(traceId);
        auditLog.setIp(IpUtils.getIpAddr(request));
        auditLog.setMethod(request.getMethod());
        auditLog.setParameter(this.getParameter(method, pjp.getArgs()));
        auditLog.setResult(result);
        auditLog.setSpendTime((int)(endTime - startTime));
        auditLog.setStartTime(startTime);
        auditLog.setUrl(request.getRequestURL().toString());
        return auditLog;
    }

    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList();
        Parameter[] parameters = method.getParameters();

        for(int i = 0; i < parameters.length; ++i) {
            RequestBody requestBody = (RequestBody)parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }

            RequestParam requestParam = (RequestParam)parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }

                map.put(key, args[i]);
                argList.add(map);
            }
        }

        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, LogOutPutService> beansOfType = applicationContext.getBeansOfType(LogOutPutService.class);
        if (StringUtil.isBlank(this.logAspectProperties().getOutPutType())) {
            this.logOutPutService = (LogOutPutService)beansOfType.get("slf4j");
        } else {
            this.logOutPutService = (LogOutPutService)beansOfType.get(this.logAspectProperties().getOutPutType());
            log.debug("【RRKJ】审计日志配置成功。日志输出方式：{}。", this.logAspectProperties().getOutPutType());
        }
    }
}
