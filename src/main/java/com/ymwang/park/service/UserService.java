package com.ymwang.park.service;

import com.ymwang.park.dto.User.*;

/**
 * @Author: wym
 * @Date: 2018/2/7
 */
public interface UserService {
    public void userRegister(UserRequest userRequest);
    public UserDto login(LoginRequest loginRequest);
    public void modifyPassword(ModifyPasswordDto modifyPasswordDto);
    public UserDto modifyUserInfo(ModifyUserInfo modifyUserInfo);
}
