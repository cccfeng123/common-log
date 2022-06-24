package com.log.util;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * MDC 工具类
 */
public class MDCUtil {

    public static final String TRACE_ID = "traceId";
    public static final String TRACE_ID_HEADER = "x-traceId";
    public static final int FILTER_ORDER = Integer.MIN_VALUE + 500;

    /**
     * 添加
     */
    public static void addTraceId() {
        MDC.put(TRACE_ID, createTraceId());
    }

    /**
     * 获取
     *
     * @return
     */
    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }


    /**
     * 设置
     *
     * @param traceId
     */
    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    /**
     * 移除
     */
    public static void removeTraceId() {
        MDC.remove(TRACE_ID);
    }

    public static String createTraceId() {
        // TODO 如果有skywalking 可以获取 skywalking 中的traceId
        return UUID.randomUUID().toString().replace("-", "");
    }

}
