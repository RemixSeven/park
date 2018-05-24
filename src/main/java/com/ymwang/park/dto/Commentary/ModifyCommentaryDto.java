package com.ymwang.park.dto.Commentary;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/23
 */
@Data
public class ModifyCommentaryDto {
    private String cId;
    private String userId;

    private String parkId;

    private String cDetail;
    private int score;
}
