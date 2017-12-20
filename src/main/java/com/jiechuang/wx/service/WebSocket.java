package com.jiechuang.wx.service;

import com.jiechuang.wx.servlet.Servlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: lijie
 * @Date: 15:49 2017/11/23
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {




    private Session session;


    private static CopyOnWriteArraySet<WebSocket> webSockets=new CopyOnWriteArraySet<>();
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSockets.add(this);

        log.info("【websocket消息】有新的连接，总数：{}",webSockets.size());
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("【websocket消息】连接断开，总数{}",webSockets.size());
    }

    @OnMessage
    public void onMessage(String message){

        log.info("【websocket消息】收到客户端发来的消息，message={}",message);
    }

    public void sendMessage(String messgae){
        for(WebSocket webSocket:webSockets){
            log.info("【websocket消息】广播消息，message={}",messgae);
            try{
                webSocket.session.getBasicRemote().sendText(messgae);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
