package com.ymwang.park.dao;

import com.ymwang.park.model.MsgContent;
import com.ymwang.park.model.SysMsg;
import com.ymwang.park.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface SysMsgMapper {

    int sendMsg(MsgContent msg);

    int addMsg2AllUser(@Param("users") List<User> users, @Param("mid") String mid);

    List<SysMsg> getSysMsg(@Param("start") int start, @Param("size") Integer size, @Param("userId") String userId);

    int markRead(@Param("flag") String flag, @Param("userId") String userId);
}
