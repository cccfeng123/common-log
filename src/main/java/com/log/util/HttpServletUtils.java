package com.log.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：JH
 * @date ：2022/5/16 16:41
 * @describe：http请求工具类
 */
public class HttpServletUtils {
    public static boolean isJsonRequest(HttpServletRequest request) {
        return StrUtil.startWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE);
    }
}
