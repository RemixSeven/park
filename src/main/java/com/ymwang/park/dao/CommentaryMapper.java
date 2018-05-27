package com.ymwang.park.dao;

import com.ymwang.park.dto.Commentary.AvgScoreParkDto;
import com.ymwang.park.model.Commentary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryMapper {
    int deleteByPrimaryKey(String cId);

    int insert(Commentary record);

    int insertSelective(Commentary record);

    Commentary selectByPrimaryKey(String cId);

    int updateByPrimaryKeySelective(Commentary record);

    int updateByPrimaryKey(Commentary record);
    List<Commentary> queryCommentary(String parkId);
    List<Commentary> commentaryByUser(String userId);
    AvgScoreParkDto queryAvgScore(String parkId);
}