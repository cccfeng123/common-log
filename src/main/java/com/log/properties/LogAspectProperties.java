//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.log.properties;

import org.springframework.stereotype.Component;

/**
 * @author ：JH
 * @date ：2022/5/16 16:41
 * @describe：日志切面配置
 */
@Component
public class LogAspectProperties {
    private boolean enable = false;
    private String outPutType = "slf4j";

    public LogAspectProperties() {
    }

    public boolean isEnable() {
        return this.enable;
    }

    public String getOutPutType() {
        return this.outPutType;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setOutPutType(String outPutType) {
        this.outPutType = outPutType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof LogAspectProperties)) {
            return false;
        } else {
            LogAspectProperties other = (LogAspectProperties)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isEnable() != other.isEnable()) {
                return false;
            } else {
                Object this$outPutType = this.getOutPutType();
                Object other$outPutType = other.getOutPutType();
                if (this$outPutType == null) {
                    if (other$outPutType != null) {
                        return false;
                    }
                } else if (!this$outPutType.equals(other$outPutType)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof LogAspectProperties;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 59 + (this.isEnable() ? 79 : 97);
        Object $outPutType = this.getOutPutType();
        result = result * 59 + ($outPutType == null ? 43 : $outPutType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "LogAspectProperties(enable=" + this.isEnable() + ", outPutType=" + this.getOutPutType() + ")";
    }
}
