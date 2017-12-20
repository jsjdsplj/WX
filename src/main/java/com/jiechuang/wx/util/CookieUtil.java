package com.jiechuang.wx.util;

import com.sun.net.httpserver.HttpsServer;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import javax.servlet.Servlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: lijie
 * @Date: 21:50 2017/11/22
 */
public class CookieUtil {


    public static  void set(HttpServletResponse response,
                            String name,
                            String value,
                            int maxAge){
        Cookie cookie=new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(7200);
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request,
                           String name){


        Map<String,Cookie> cookieMap=readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }else {
            return null;
        }

    }

    //将cookie封住成map
    private static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap=new HashMap<>();
        Cookie[] cookies=request.getCookies();
        if(cookies !=null){
            for(Cookie cookie:cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
