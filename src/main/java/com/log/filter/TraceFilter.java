package com.log.filter;

import com.log.properties.TraceProperties;
import com.log.util.MDCUtil;
import com.log.util.StringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * TraceFilter, 用于在请求头中添加traceId
 *
 * @author ：JH
 * @date ：2022/5/16 16:41
 * @describe：
 */
@Configuration
@WebFilter(value = "/**")
@Order(MDCUtil.FILTER_ORDER)
@ConditionalOnClass(value = {HttpServletRequest.class, OncePerRequestFilter.class})
public class TraceFilter extends OncePerRequestFilter {
    private final TraceProperties traceProperties;

    public TraceFilter(TraceProperties webTraceProperties) {
        this.traceProperties = webTraceProperties;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !traceProperties.isEnable();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            String traceId = request.getHeader(traceProperties.getHeaderName());
            if (StringUtil.isBlank(traceId)) {
                MDCUtil.addTraceId();
            } else {
                MDCUtil.setTraceId(traceId);
            }
            response.setHeader(traceProperties.getHeaderName(), MDCUtil.getTraceId());
            filterChain.doFilter(request, response);
        } finally {
            MDCUtil.removeTraceId();
        }
    }
}