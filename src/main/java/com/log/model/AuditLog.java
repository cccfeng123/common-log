package com.log.model;


/**
 * @author ：JH
 * @date ：2022/5/16 16:41
 * @describe：
 */
public class AuditLog {
    private String traceId;
    private String userId;
    private Long startTime;
    private Integer spendTime;
    private String url;
    private String method;
    private String ip;
    private Object parameter;
    private Object result;

    public AuditLog() {
    }

    public String getTraceId() {
        return this.traceId;
    }

    public String getUserId() {
        return this.userId;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public Integer getSpendTime() {
        return this.spendTime;
    }

    public String getUrl() {
        return this.url;
    }

    public String getMethod() {
        return this.method;
    }

    public String getIp() {
        return this.ip;
    }

    public Object getParameter() {
        return this.parameter;
    }

    public Object getResult() {
        return this.result;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setSpendTime(Integer spendTime) {
        this.spendTime = spendTime;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AuditLog)) {
            return false;
        } else {
            AuditLog other = (AuditLog)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label119: {
                    Object this$traceId = this.getTraceId();
                    Object other$traceId = other.getTraceId();
                    if (this$traceId == null) {
                        if (other$traceId == null) {
                            break label119;
                        }
                    } else if (this$traceId.equals(other$traceId)) {
                        break label119;
                    }

                    return false;
                }

                Object this$userId = this.getUserId();
                Object other$userId = other.getUserId();
                if (this$userId == null) {
                    if (other$userId != null) {
                        return false;
                    }
                } else if (!this$userId.equals(other$userId)) {
                    return false;
                }

                label105: {
                    Object this$startTime = this.getStartTime();
                    Object other$startTime = other.getStartTime();
                    if (this$startTime == null) {
                        if (other$startTime == null) {
                            break label105;
                        }
                    } else if (this$startTime.equals(other$startTime)) {
                        break label105;
                    }

                    return false;
                }

                Object this$spendTime = this.getSpendTime();
                Object other$spendTime = other.getSpendTime();
                if (this$spendTime == null) {
                    if (other$spendTime != null) {
                        return false;
                    }
                } else if (!this$spendTime.equals(other$spendTime)) {
                    return false;
                }

                label91: {
                    Object this$url = this.getUrl();
                    Object other$url = other.getUrl();
                    if (this$url == null) {
                        if (other$url == null) {
                            break label91;
                        }
                    } else if (this$url.equals(other$url)) {
                        break label91;
                    }

                    return false;
                }

                Object this$method = this.getMethod();
                Object other$method = other.getMethod();
                if (this$method == null) {
                    if (other$method != null) {
                        return false;
                    }
                } else if (!this$method.equals(other$method)) {
                    return false;
                }

                label77: {
                    Object this$ip = this.getIp();
                    Object other$ip = other.getIp();
                    if (this$ip == null) {
                        if (other$ip == null) {
                            break label77;
                        }
                    } else if (this$ip.equals(other$ip)) {
                        break label77;
                    }

                    return false;
                }

                label70: {
                    Object this$parameter = this.getParameter();
                    Object other$parameter = other.getParameter();
                    if (this$parameter == null) {
                        if (other$parameter == null) {
                            break label70;
                        }
                    } else if (this$parameter.equals(other$parameter)) {
                        break label70;
                    }

                    return false;
                }

                Object this$result = this.getResult();
                Object other$result = other.getResult();
                if (this$result == null) {
                    if (other$result != null) {
                        return false;
                    }
                } else if (!this$result.equals(other$result)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof AuditLog;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $traceId = this.getTraceId();
        result = result * 59 + ($traceId == null ? 43 : $traceId.hashCode());
        Object $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
        Object $startTime = this.getStartTime();
        result = result * 59 + ($startTime == null ? 43 : $startTime.hashCode());
        Object $spendTime = this.getSpendTime();
        result = result * 59 + ($spendTime == null ? 43 : $spendTime.hashCode());
        Object $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        Object $method = this.getMethod();
        result = result * 59 + ($method == null ? 43 : $method.hashCode());
        Object $ip = this.getIp();
        result = result * 59 + ($ip == null ? 43 : $ip.hashCode());
        Object $parameter = this.getParameter();
        result = result * 59 + ($parameter == null ? 43 : $parameter.hashCode());
        Object $result = this.getResult();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "AuditLog(traceId=" + this.getTraceId() + ", userId=" + this.getUserId() + ", startTime=" + this.getStartTime() + ", spendTime=" + this.getSpendTime() + ", url=" + this.getUrl() + ", method=" + this.getMethod() + ", ip=" + this.getIp() + ", parameter=" + this.getParameter() + ", result=" + this.getResult() + ")";
    }
}
