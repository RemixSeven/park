package com.ymwang.park.dao;

import com.ymwang.park.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User selectByUserName(String userName);
    List<User> selectByList();
    List<User> getByUserName(String userName);
    List<User> getByPhone(String phone);
    List<User> getByName(String name);
}