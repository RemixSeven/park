package com.ymwang.park.service;

import com.ymwang.park.dto.Commentary.*;
import com.ymwang.park.dto.Park.AllParkDto;


/**
 * @Author: wym
 * @Date: 2018/5/8
 */
public interface CommentaryService {
    String addCommentary(AddCommentaryDto addCommentaryDto);
    void modifyCommentary(ModifyCommentaryDto modifyCommentaryDto);
    void deleteCommentary(DeleteCommentary deleteCommentary);
    void batchDeleteCommentary(BatchDeleteCommentary batchDeleteCommentary);
    QueryCommentaryDto queryCommentary(QueryCommentaryRuquest queryCommentaryRuquest);
    QueryCommentaryDto commentaryByUser(QueryCommentaryByUser queryCommentaryByUser);
    QueryCommentaryDto allCommentary(AllParkDto allParkDto);
}
