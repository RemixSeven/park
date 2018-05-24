package com.ymwang.park.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymwang.park.dao.CommentaryMapper;
import com.ymwang.park.dao.ParkMapper;
import com.ymwang.park.dao.UserMapper;
import com.ymwang.park.dto.Commentary.*;
import com.ymwang.park.model.Commentary;
import com.ymwang.park.model.Park;
import com.ymwang.park.model.User;
import com.ymwang.park.service.CommentaryService;
import com.ymwang.park.utils.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
@Service
public class CommentaryServiceImpl implements CommentaryService {
    @Autowired
    CommentaryMapper commentaryMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ParkMapper parkMapper;
    @Override
    public void addCommentary(AddCommentaryDto addCommentaryDto) {
        Commentary commentary=new Commentary();
        commentary.setcId(UUID.randomUUID().toString().replaceAll("-", ""));
        commentary.setUserId(addCommentaryDto.getUserId());
        commentary.setParkId(addCommentaryDto.getParkId());
        commentary.setcDetail(addCommentaryDto.getCDetail());
        commentary.setScore(addCommentaryDto.getScore());
        commentaryMapper.insertSelective(commentary);
    }

    @Override
    public void modifyCommentary(ModifyCommentaryDto modifyCommentaryDto) {
        Commentary commentary=new Commentary();
        commentary.setcId(modifyCommentaryDto.getCId());
        commentary.setUserId(modifyCommentaryDto.getUserId());
        commentary.setParkId(modifyCommentaryDto.getParkId());
        commentary.setcDetail(modifyCommentaryDto.getCDetail());
        commentary.setScore(modifyCommentaryDto.getScore());
        commentaryMapper.updateByPrimaryKeySelective(commentary);
    }

    @Override
    public void deleteCommentary(DeleteCommentary deleteCommentary) {
        Commentary commentary=commentaryMapper.selectByPrimaryKey(deleteCommentary.getCId());
        if (commentary.getUserId()==deleteCommentary.getUserId()){
            commentaryMapper.deleteByPrimaryKey(deleteCommentary.getCId());
        }else {
            throw new BizException("api.commentary.delete.authority","您没有删除该评论的权限，只有评价者本人才能删除");
        }
    }

    @Override
    public QueryCommentaryDto queryCommentary(QueryCommentaryRuquest queryCommentaryRuquest) {
        QueryCommentaryDto queryCommentaryDto=new QueryCommentaryDto();
        PageHelper.startPage(queryCommentaryRuquest.getPageNum(),queryCommentaryRuquest.getPageSize());
        List<Commentary> commentaries=commentaryMapper.queryCommentary(queryCommentaryRuquest.getParkId());
        List<CommentaryDto> commentaryDtos=new ArrayList<>();
        for (Commentary commentary:commentaries){
            CommentaryDto commentaryDto=new CommentaryDto();
            commentaryDto.setCId(commentary.getcId());
            commentaryDto.setCDetail(commentary.getcDetail());
            commentaryDto.setUserId(commentary.getUserId());
            commentaryDto.setParkId(commentary.getParkId());
            commentaryDto.setScore(commentary.getScore());
            commentaryDto.setCommentaryTime(commentary.getUpdateTime());
            User user=userMapper.selectByPrimaryKey(commentary.getUserId());
            commentaryDto.setUserName(user.getUserName());
            commentaryDto.setName(user.getName());
            commentaryDtos.add(commentaryDto);
        }
        PageInfo<CommentaryDto> pageInfo=new PageInfo<CommentaryDto>(commentaryDtos);
        long total=pageInfo.getTotal();
        queryCommentaryDto.setCount(String.valueOf(total));
        queryCommentaryDto.setCommentaryDtos(commentaryDtos);
        return queryCommentaryDto;
    }

    @Override
    public QueryCommentaryDto commentaryByUser(QueryCommentaryByUser queryCommentaryByUser) {
        QueryCommentaryDto queryCommentaryDto=new QueryCommentaryDto();
        PageHelper.startPage(queryCommentaryByUser.getPageNum(),queryCommentaryByUser.getPageSize());
        List<Commentary> commentaries=commentaryMapper.commentaryByUser(queryCommentaryByUser.getUserId());
        List<CommentaryDto> commentaryDtos=new ArrayList<>();
        for (Commentary commentary:commentaries){
            CommentaryDto commentaryDto=new CommentaryDto();
            commentaryDto.setCId(commentary.getcId());
            commentaryDto.setCDetail(commentary.getcDetail());
            commentaryDto.setUserId(commentary.getUserId());
            commentaryDto.setParkId(commentary.getParkId());
            commentaryDto.setCommentaryTime(commentary.getUpdateTime());
            Park park=parkMapper.selectByPrimaryKey(commentary.getParkId());
            commentaryDto.setParkName(park.getParkName());
            User user=userMapper.selectByPrimaryKey(commentary.getUserId());
            commentaryDto.setUserName(user.getUserName());
            commentaryDtos.add(commentaryDto);
        }
        PageInfo<CommentaryDto> pageInfo=new PageInfo<CommentaryDto>(commentaryDtos);
        long total=pageInfo.getTotal();
        queryCommentaryDto.setCount(String.valueOf(total));
        queryCommentaryDto.setCommentaryDtos(commentaryDtos);
        return queryCommentaryDto;
    }
}
