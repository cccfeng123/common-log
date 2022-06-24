package com.log.service.impl;


import com.log.model.AuditLog;
import com.log.service.LogOutPutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author ：JH
 * @date ：2022/5/16 16:41
 * @describe：日志实现类
 */
@Component("slf4j")
public class Slf4jLogOutPutServiceImpl implements LogOutPutService {
    private static final Logger log = LoggerFactory.getLogger(Slf4jLogOutPutServiceImpl.class);
    private static final String MSG_PATTERN = "{}|{}|{}|{}|{}|{}|{}|{}|{}";

    public Slf4jLogOutPutServiceImpl() {
    }

    @Override
    public void outPutLog(AuditLog auditLog) {
        log.info(auditLog.toString());
    }
}
