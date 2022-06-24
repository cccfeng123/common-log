package com.log.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ：JH
 * @date ：2022/5/16 16:41
 * @describe：获取IP工具类
 */
public class IpUtils {
    public IpUtils() {
    }

    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return null;
        } else {
            String ip = null;
            String ipAddresses = request.getHeader("X-Forwarded-For");
            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("HTTP_CLIENT_IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("X-Real-IP");
            }

            if (ipAddresses != null && ipAddresses.length() != 0) {
                ip = ipAddresses.split(",")[0];
            }

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ip = request.getRemoteAddr();
            }

            return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
        }
    }

    public static boolean internalIp(String ip) {
        byte[] addr = textToNumericFormatV4(ip);
        return internalIp(addr) || "127.0.0.1".equals(ip);
    }

    private static boolean internalIp(byte[] addr) {
        if (addr != null && addr.length >= 2) {
            byte b0 = addr[0];
            byte b1 = addr[1];

            switch(b0) {
            case -84:
                if (b1 >= 16 && b1 <= 31) {
                    return true;
                }
            case -64:
                switch(b1) {
                case -88:
                    return true;
                }
            default:
                return false;
            case 10:
                return true;
            }
        } else {
            return true;
        }
    }

    public static byte[] textToNumericFormatV4(String text) {
        if (text.length() == 0) {
            return null;
        } else {
            byte[] bytes = new byte[4];
            String[] elements = text.split("\\.", -1);

            try {
                long l;
                int i;
                switch(elements.length) {
                case 1:
                    l = Long.parseLong(elements[0]);
                    if (l < 0L || l > 4294967295L) {
                        return null;
                    }

                    bytes[0] = (byte)((int)(l >> 24 & 255L));
                    bytes[1] = (byte)((int)((l & 16777215L) >> 16 & 255L));
                    bytes[2] = (byte)((int)((l & 65535L) >> 8 & 255L));
                    bytes[3] = (byte)((int)(l & 255L));
                    break;
                case 2:
                    l = (long)Integer.parseInt(elements[0]);
                    if (l >= 0L && l <= 255L) {
                        bytes[0] = (byte)((int)(l & 255L));
                        l = (long)Integer.parseInt(elements[1]);
                        if (l < 0L || l > 16777215L) {
                            return null;
                        }

                        bytes[1] = (byte)((int)(l >> 16 & 255L));
                        bytes[2] = (byte)((int)((l & 65535L) >> 8 & 255L));
                        bytes[3] = (byte)((int)(l & 255L));
                        break;
                    }

                    return null;
                case 3:
                    for(i = 0; i < 2; ++i) {
                        l = (long)Integer.parseInt(elements[i]);
                        if (l < 0L || l > 255L) {
                            return null;
                        }

                        bytes[i] = (byte)((int)(l & 255L));
                    }

                    l = (long)Integer.parseInt(elements[2]);
                    if (l < 0L || l > 65535L) {
                        return null;
                    }

                    bytes[2] = (byte)((int)(l >> 8 & 255L));
                    bytes[3] = (byte)((int)(l & 255L));
                    break;
                case 4:
                    for(i = 0; i < 4; ++i) {
                        l = (long)Integer.parseInt(elements[i]);
                        if (l < 0L || l > 255L) {
                            return null;
                        }

                        bytes[i] = (byte)((int)(l & 255L));
                    }

                    return bytes;
                default:
                    return null;
                }

                return bytes;
            } catch (NumberFormatException var6) {
                return null;
            }
        }
    }

    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException var1) {
            return "127.0.0.1";
        }
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException var1) {
            return "未知";
        }
    }
}
