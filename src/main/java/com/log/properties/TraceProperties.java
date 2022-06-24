package com.log.properties;

import com.log.util.MDCUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author ：JH
 * @date ：2022/5/16 16:41
 * @describe：trace配置
 */
@ConfigurationProperties("trace")
public class TraceProperties {
    /**
     * 是否启用
     */
    private boolean enable;
    private Request request = new Request();
    private Response response = new Response();
    /**
     * 自定义请求头
     */
    private String headerName = MDCUtil.TRACE_ID_HEADER;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public static class Request {
        private boolean enable = true;
        private List<String> headers;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public List<String> getHeaders() {
            return headers;
        }

        public void setHeaders(List<String> headers) {
            this.headers = headers;
        }
    }

    public static class Response {
        private boolean enable = true;
        private boolean detail;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public boolean isDetail() {
            return detail;
        }

        public void setDetail(boolean detail) {
            this.detail = detail;
        }
    }
}
