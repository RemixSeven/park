package com.ymwang.park.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @Author: wym
 * WebSocket 消息处理类
 * @Date: 2018/5/26
 */
@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/ws/nf")
    @SendTo("/topic/nf")
    public String handleNF() {
        return "系统消息";
    }
}
