package com.ymwang.park.dto.Commentary;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
@Data
public class CommentaryDto {
    private String cId;
    private String userId;
    private String parkId;
    private String parkName;
    private String userName;
    private String name;
    private String cDetail;
    private int score;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date commentaryTime;
}
