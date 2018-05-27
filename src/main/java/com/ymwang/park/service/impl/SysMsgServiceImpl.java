package com.ymwang.park.service.impl;

import com.ymwang.park.dao.SysMsgMapper;
import com.ymwang.park.dto.Msg.MarkMsgDto;
import com.ymwang.park.dto.Msg.MsgRequest;
import com.ymwang.park.model.MsgContent;
import com.ymwang.park.model.SysMsg;
import com.ymwang.park.model.User;
import com.ymwang.park.service.SysMsgService;
import com.ymwang.park.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/5/26
 */
@Service
@Transactional
public class SysMsgServiceImpl implements SysMsgService {
    @Autowired
    SysMsgMapper sysMsgMapper;
    @Autowired
    UserService userService;
    @Override
    public boolean sendMsg(MsgContent msgContent) {
        int result = sysMsgMapper.sendMsg(msgContent);
        List<User> allUser = userService.getAllUser();
        int result2 = sysMsgMapper.addMsg2AllUser(allUser, msgContent.getId());
        return result2==allUser.size();
    }

    @Override
    public List<SysMsg> getSysMsgByPage(MsgRequest m) {
        int start = (m.getPageNum() - 1) * m.getPageSize();
        return sysMsgMapper.getSysMsg(start,m.getPageSize(), m.getUserId());
    }

    @Override
    public void markRead(MarkMsgDto markMsgDto) {
        sysMsgMapper.markRead(markMsgDto.getFlag(),markMsgDto.getUserId());
    }

}
