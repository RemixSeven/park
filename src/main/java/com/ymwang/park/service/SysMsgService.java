package com.ymwang.park.service;

import com.ymwang.park.dto.Msg.MarkMsgDto;
import com.ymwang.park.dto.Msg.MsgRequest;
import com.ymwang.park.model.MsgContent;
import com.ymwang.park.model.SysMsg;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/5/26
 */
public interface SysMsgService {
    public boolean sendMsg(MsgContent msg);
    List<SysMsg> getSysMsgByPage(MsgRequest m);
    void markRead(MarkMsgDto markMsgDto);
}
