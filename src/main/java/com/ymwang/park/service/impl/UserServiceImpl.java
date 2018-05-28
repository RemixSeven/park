package com.ymwang.park.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymwang.park.dao.UserMapper;
import com.ymwang.park.dao.WalletMapper;
import com.ymwang.park.dto.User.*;
import com.ymwang.park.model.User;
import com.ymwang.park.model.Wallet;
import com.ymwang.park.service.UserService;
import com.ymwang.park.utils.BizException;
import com.ymwang.park.utils.MD5Util;
import com.ymwang.park.utils.PatternUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wym
 * @Date: 2018/2/7
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    WalletMapper walletMapper;
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
        if (user != null && user.getPassword().equals(MD5Util.encrypt16(loginRequest.getPassword()))) {
            if (user.getValid().equals("0")){
                throw new BizException("api.user.forbid","账户被禁用，请联系管理人员");
            }
            UserDto userDto=new UserDto();
            userDto.setName(user.getName());
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setPhone(user.getPhone());
            userDto.setUserType(user.getUserType());
            Wallet wallet=walletMapper.queryWallet(user.getUserId());
            userDto.setWalletId(wallet.getWalletId());
            return userDto;
        }else {
            throw new BizException("api.password","密码错误");
        }

    }

    @Override
    public void modifyPassword(ModifyPasswordDto modifyPasswordDto) {
        User user=userMapper.selectByUserName(modifyPasswordDto.getUsername());
        if (user != null && user.getPassword().equals(MD5Util.encrypt16(modifyPasswordDto.getPassword()))){
            user.setPassword(MD5Util.encrypt16(modifyPasswordDto.getNewPassword()));
            userMapper.updateByPrimaryKeySelective(user);
        }else {
            throw new BizException("api.modifyPassword","原密码错误，不能修改");
        }
    }

    @Override
    public UserDto modifyUserInfo(ModifyUserInfo modifyUserInfo) {
        if(PatternUtil.checkMobile(modifyUserInfo.getPhone())==false){
            throw new BizException("api.user.mobile", "手机号格式错误");
        }
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

    @Override
    public QueryUserDto queryUser(QueryUserRequest queryUserRequest) {
        QueryUserDto queryUserDto=new QueryUserDto();
        List<User> userList=userMapper.selectByList();
        PageHelper.startPage(queryUserRequest.getPageNum(),queryUserRequest.getPageSize());
        List<User> users=userMapper.selectByList();
        List<UserDto> userDtos=new ArrayList<>();
        for (User user:users){
            UserDto userDto=new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setName(user.getName());
            userDto.setPhone(user.getPhone());
            userDto.setUserType(user.getUserType());
            userDtos.add(userDto);
        }
        int sum=userList.size();
        PageInfo<UserDto> pageInfo=new PageInfo<UserDto>(userDtos);
        long total=pageInfo.getTotal();
        queryUserDto.setCount(String.valueOf(total));
        queryUserDto.setUserDtos(userDtos);
        queryUserDto.setSum(sum);
        return queryUserDto;
    }

    @Override
    public QueryUserDto queryUserByContent(QueryUserByContent queryUserByContent) {
        QueryUserDto queryUserDto=new QueryUserDto();
        List<User> users=new ArrayList<>();
        List<User> userList=new ArrayList<>();
        PageHelper.startPage(queryUserByContent.getPageNum(),queryUserByContent.getPageSize());
        PatternUtil.PatternEnum patternEnum=PatternUtil.transform(queryUserByContent.getContent());
        switch (patternEnum){
            case CHARACTER:
                users=userMapper.getByUserName(queryUserByContent.getContent());
                userList=userMapper.getByUserName(queryUserByContent.getContent());
                break;
            case CHINESE:
                users=userMapper.getByName(queryUserByContent.getContent());
                userList=userMapper.getByName(queryUserByContent.getContent());
                break;
            case MOBILE:
                users=userMapper.getByPhone(queryUserByContent.getContent());
                userList=userMapper.getByName(queryUserByContent.getContent());
                break;
            default:
                break;
        }
        List<UserDto> userDtos=new ArrayList<>();
        for (User user:users){
            UserDto userDto=new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setName(user.getName());
            userDto.setPhone(user.getPhone());
            userDto.setUserType(user.getUserType());
            userDtos.add(userDto);
        }
        PageInfo<UserDto> pageInfo=new PageInfo<UserDto>(userDtos);
        long total=pageInfo.getTotal();
        int sum=userList.size();
        queryUserDto.setCount(String.valueOf(total));
        queryUserDto.setUserDtos(userDtos);
        queryUserDto.setSum(sum);
        return queryUserDto;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users=userMapper.selectByList();
        return users;
    }

    @Override
    public void forbidUser(ForbidUserDto forbidUserDto) {
        User user=userMapper.selectByPrimaryKey(forbidUserDto.getUserId());
        user.setValid("0");
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void batchForbidUser(BatchForbidUserDto batchForbidUserDto) {
        String users=batchForbidUserDto.getUsers();
        String[] split = users.split(",");
        for (String i:split){
            User user=userMapper.selectByPrimaryKey(i);
            user.setValid("0");
            userMapper.updateByPrimaryKeySelective(user);
        }
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
            if(PatternUtil.checkMobile(userRequest.getPhone())==false){
                throw new BizException("api.user.mobile", "手机号格式错误");
            }
            if(PatternUtil.checkCharacter(userRequest.getUserName())==false){
                throw new BizException("api.user.username", "用户名只能是字母和数字");
            }
            User user = new User();
            user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setUserName(userRequest.getUserName());
            user.setPhone(userRequest.getPhone());
            user.setUserType(userRequest.getUserType());
            user.setName(userRequest.getName());
            user.setPassword(MD5Util.encrypt16(userRequest.getPassword()));
            user.setValid("1");
            userMapper.insertSelective(user);
            Wallet wallet=new Wallet();
            wallet.setWalletId(UUID.randomUUID().toString().replaceAll("-", ""));
            wallet.setBalance(0);
            wallet.setUserId(user.getUserId());
            walletMapper.insertSelective(wallet);
        }
    }
}
