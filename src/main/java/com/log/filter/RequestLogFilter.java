package com.log.filter;

import cn.hutool.extra.servlet.ServletUtil;
import com.log.model.RequestLog;
import com.log.properties.TraceProperties;
import com.log.util.HttpServletUtils;
import com.log.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 用于记录请求日志
 */
@ConditionalOnClass(value = {HttpServletRequest.class, OncePerRequestFilter.class})
@Order
public class RequestLogFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(RequestLogFilter.class);
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final TraceProperties traceProperties;

    public RequestLogFilter(TraceProperties traceProperties) {
        this.traceProperties = traceProperties;
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !traceProperties.isEnable();
    }

    private void log(LocalDateTime start, Map<String, String> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestLog requestLog = new RequestLog();

        requestLog.setUrl(request.getRequestURI());
        requestLog.setMethod(request.getMethod());
        requestLog.setParameter(paramMap);

        String body = HttpServletUtils.isJsonRequest(request) ? ServletUtil.getBody(request) : null;
        requestLog.setBody(body);
        requestLog.setIp(IpUtils.getIpAddr(request));

        LocalDateTime endTime = LocalDateTime.now();
        requestLog.setStartTime(start.format(DATETIME_FORMAT));
        requestLog.setEndTime(endTime.format(DATETIME_FORMAT));
        requestLog.setDuration(endTime.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
        response.getOutputStream().println();
        log.info(format(requestLog));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LocalDateTime start = LocalDateTime.now();
        Map<String, String> paramMap = ServletUtil.getParamMap(request);
        filterChain.doFilter(request, response);
        try {
            log(start, paramMap, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 格式化, 如果有转成json 需求, 可以在此操作
     *
     * @param requestLog
     * @return
     */
    private String format(RequestLog requestLog) {
        return requestLog.toString();
    }
}
