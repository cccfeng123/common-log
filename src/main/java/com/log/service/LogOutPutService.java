package com.log.service;


import com.log.model.AuditLog;

/**
 * @author ：JH
 * @date ：2022/5/16 16:41
 * @describe：日志
 */
public interface LogOutPutService {
    void outPutLog(AuditLog var1);
}
