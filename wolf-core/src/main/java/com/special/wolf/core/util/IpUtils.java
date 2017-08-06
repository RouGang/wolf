package com.special.wolf.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hikaru on 17/8/6.
 */
public class IpUtils {

  private static String[] IP_HEADS = new String[] { "x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP",
      "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", };

  public static String getRemoteAddr(HttpServletRequest request)
  {
    for (String header : IP_HEADS)
    {
      String ip = request.getHeader(header);
      if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)){
        if(ip.indexOf(",") > -1){
          ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
      }
    }
    return request.getRemoteAddr();
  }



}
