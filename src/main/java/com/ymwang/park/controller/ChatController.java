package com.ymwang.park.controller;

import com.ymwang.park.dto.Msg.MarkMsgDto;
import com.ymwang.park.dto.Msg.MsgRequest;
import com.ymwang.park.dto.Msg.SendMsgDto;
import com.ymwang.park.model.MsgContent;
import com.ymwang.park.model.SysMsg;
import com.ymwang.park.service.SysMsgService;
import com.ymwang.park.utils.BizException;
import com.ymwang.park.utils.ResultMessage;
import com.ymwang.park.utils.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @Author: wym
 * 处理通知消息的Controller
 * 登录即可访问
 * @Date: 2018/5/26
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    SysMsgService sysMsgService;
    @RequestMapping(value = "/sendNf", method = RequestMethod.POST)
    public SingleResult<String> sendNf(@RequestBody SendMsgDto sendMsgDto) {
        MsgContent msgContent=new MsgContent();
        msgContent.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        msgContent.setTitle(sendMsgDto.getTitle());
        msgContent.setMessage(sendMsgDto.getMessage());
        if (!sysMsgService.sendMsg(msgContent)) {
            throw new BizException("error", "发送失败!");
        }
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(value ="/sysmsgs",method = RequestMethod.POST)
    public List<SysMsg> getSysMsg(@RequestBody MsgRequest msgRequest) {
        return sysMsgService.getSysMsgByPage(msgRequest);
    }
    @RequestMapping(value = "/markRead", method = RequestMethod.POST)
    public SingleResult<String> markRead(@RequestBody MarkMsgDto markMsgDto) {
        sysMsgService.markRead(markMsgDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
}
