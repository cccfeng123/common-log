package com.log.filter;

import com.log.util.HttpServletUtils;
import com.log.wrapper.RequestCacheWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestCacheFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(new RequestCacheWrapper(request), response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 只处理 json 请求内容
        return !HttpServletUtils.isJsonRequest(request);
    }
}
