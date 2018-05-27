package com.ymwang.park.service;

import com.ymwang.park.dto.User.*;
import com.ymwang.park.model.User;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/2/7
 */
public interface UserService {
    public void userRegister(UserRequest userRequest);
    public UserDto login(LoginRequest loginRequest);
    public void modifyPassword(ModifyPasswordDto modifyPasswordDto);
    public UserDto modifyUserInfo(ModifyUserInfo modifyUserInfo);
    QueryUserDto queryUser(QueryUserRequest queryUserRequest);
    QueryUserDto queryUserByContent(QueryUserByContent queryUserByContent);
    List<User> getAllUser();
    void forbidUser(ForbidUserDto forbidUserDto);
    void batchForbidUser(BatchForbidUserDto batchForbidUserDto);
}
