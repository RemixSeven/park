package com.ymwang.park.service.impl;

import com.ymwang.park.controller.dto.*;
import com.ymwang.park.dao.UserMapper;
import com.ymwang.park.model.User;
import com.ymwang.park.service.UserService;
import com.ymwang.park.utils.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: wym
 * @Date: 2018/2/7
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void userRegister(UserRequest userRequest) throws BizException{
        if (userRequest.getUserType().equals("0")) {
           register(userRequest);
        }else {
            if (StringUtils.equals(userRequest.getCode(),"WSQAJL")){
                register(userRequest);
            }else {
                throw new BizException("api.register.authority","您没有权限注册管理员");
            }
        }
    }

    @Override
    public UserDto login(LoginRequest loginRequest) {
        User user=userMapper.selectByUserName(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            UserDto userDto=new UserDto();
            userDto.setName(user.getName());
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setPhone(user.getPhone());
            userDto.setUserType(user.getUserType());
            return userDto;
        }else {
            throw new BizException("api.password","密码错误");
        }

    }

    @Override
    public void modifyPassword(ModifyPasswordDto modifyPasswordDto) {
        User user=userMapper.selectByUserName(modifyPasswordDto.getUsername());
        if (user != null && user.getPassword().equals(modifyPasswordDto.getPassword())){
            user.setPassword(modifyPasswordDto.getNewPassword());
            userMapper.updateByPrimaryKeySelective(user);
        }else {
            throw new BizException("api.modifyPassword","原密码错误，不能修改");
        }
    }

    @Override
    public UserDto modifyUserInfo(ModifyUserInfo modifyUserInfo) {
        User user=userMapper.selectByUserName(modifyUserInfo.getUserName());
        user.setName(modifyUserInfo.getName());
        user.setPhone(modifyUserInfo.getPhone());
        userMapper.updateByPrimaryKeySelective(user);
        User user1=userMapper.selectByPrimaryKey(user.getUserId());
        UserDto userDto=new UserDto();
        userDto.setName(user1.getName());
        userDto.setUserId(user1.getUserId());
        userDto.setUserName(user1.getUserName());
        userDto.setPhone(user1.getPhone());
        userDto.setUserType(user1.getUserType());
        return userDto;
    }

    private boolean isUserNameExist(UserRequest userRequest) {
        if ((userMapper.selectByUserName(userRequest.getUserName()))==null)
        {
            return false;
        }
        return true;
    }
    public void register(UserRequest userRequest){
        if (isUserNameExist(userRequest)) {
            throw new BizException("api.username.exist", "该用户已存在，不能重复添加");
        } else {
            User user = new User();
            user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setUserName(userRequest.getUserName());
            user.setPhone(userRequest.getPhone());
            user.setUserType(userRequest.getUserType());
            user.setName(userRequest.getName());
            user.setPassword(userRequest.getPassword());
            userMapper.insertSelective(user);
        }
    }
}
