package com.ymwang.park.dto.Commentary;

import lombok.Data;

import java.util.List;


/**
 * @Author: wym
 * @Date: 2018/5/11
 */
@Data
public class QueryCommentaryDto {
    private List<CommentaryDto> commentaryDtos;
    private String count;
}
