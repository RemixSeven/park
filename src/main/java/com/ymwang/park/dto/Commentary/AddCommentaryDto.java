package com.ymwang.park.dto.Commentary;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
@Data
public class AddCommentaryDto {
    private String userId;

    private String parkId;

    private String cDetail;
}
