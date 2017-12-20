package com.jiechuang.wx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;



/**
 * @Author: lijie
 * @Date: 15:46 2017/11/23
 */
@Component
public class WebSocketConfig {

@Bean
    public ServerEndpointExporter serverEndpointExporter(){

    return new ServerEndpointExporter();
     }

}
