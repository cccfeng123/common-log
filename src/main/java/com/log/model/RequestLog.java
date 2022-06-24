package com.log.model;

import java.util.Map;

/**
 *RequestLog
 */
public class RequestLog {
    private String method;
    private String startTime;
    private String endTime;
    private Long duration;
    private String url;
    private String ip;
    private Map<String, String> parameter;
    private String body;
    private Object result;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Map<String, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, String> parameter) {
        this.parameter = parameter;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "RequestLog{" +
                "method='" + method + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", duration=" + duration +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", parameter=" + parameter +
                ", body='" + body + '\'' +
                ", result=" + result +
                '}';
    }
}
