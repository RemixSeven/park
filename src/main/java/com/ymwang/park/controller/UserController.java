package com.ymwang.park.controller;

import com.ymwang.park.dto.User.*;
import com.ymwang.park.service.UserService;
import com.ymwang.park.utils.ResultMessage;
import com.ymwang.park.utils.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wym
 * @Date: 2018/2/7
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(method = RequestMethod.POST, path = "register")
    public SingleResult<String> register(@RequestBody UserRequest userRequest){
        userService.userRegister(userRequest);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public SingleResult<UserDto> login(@RequestBody LoginRequest loginRequest/*@RequestParam String username,@RequestParam String password*/){
        UserDto userDto=userService.login(loginRequest);
        SingleResult<UserDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(userDto);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/modifyPassword")
    public SingleResult<String> modifyPassword(@RequestBody ModifyPasswordDto modifyPasswordDto){
        userService.modifyPassword(modifyPasswordDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/modifyUserInfo")
    public SingleResult<UserDto> modifyUserInfo(@RequestBody ModifyUserInfo modifyUserInfo){
        UserDto userDto=userService.modifyUserInfo(modifyUserInfo);
        SingleResult<UserDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(userDto);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryUser")
    public SingleResult<QueryUserDto> queryUser(@RequestBody QueryUserRequest queryUserRequest){
        QueryUserDto queryUserDto=userService.queryUser(queryUserRequest);
        SingleResult<QueryUserDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(queryUserDto);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryUserByContent")
    public SingleResult<QueryUserDto> queryUserByContent(@RequestBody QueryUserByContent queryUserByContent){
        QueryUserDto queryUserDto=userService.queryUserByContent(queryUserByContent);
        SingleResult<QueryUserDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(queryUserDto);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/forbidUser")
    public SingleResult<String> forbidUser(@RequestBody ForbidUserDto forbidUserDto){
        userService.forbidUser(forbidUserDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/batchForbidUser")
    public SingleResult<String> forbidUser(@RequestBody BatchForbidUserDto batchForbidUserDto){
        userService.batchForbidUser(batchForbidUserDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
}
