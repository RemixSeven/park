package com.ymwang.park.controller;

import com.ymwang.park.dto.Commentary.*;
import com.ymwang.park.dto.Park.AllParkDto;
import com.ymwang.park.service.CommentaryService;
import com.ymwang.park.utils.ResultMessage;
import com.ymwang.park.utils.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: wym
 * @Date: 2018/5/7
 */
@RestController
@RequestMapping(value = "/commentary")
public class CommentaryController {
    @Autowired
    CommentaryService commentaryService;
    @RequestMapping(method = RequestMethod.POST,value = "/addCommentary")
    public SingleResult<String> addCommentary(@RequestBody AddCommentaryDto addCommentaryDto){
        String result=commentaryService.addCommentary(addCommentaryDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(result);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/modifyCommentary")
    public SingleResult<String> modifyCommentary(@RequestBody ModifyCommentaryDto modifyCommentaryDto){
        commentaryService.modifyCommentary(modifyCommentaryDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/deleteCommentary")
    public SingleResult<String> deleteCommentary(@RequestBody DeleteCommentary deleteCommentary){
        commentaryService.deleteCommentary(deleteCommentary);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/batchdeleteCommentary")
    public SingleResult<String> batchDeleteCommentary(@RequestBody BatchDeleteCommentary batchDeleteCommentary){
        commentaryService.batchDeleteCommentary(batchDeleteCommentary);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/queryCommentary")
    public SingleResult<QueryCommentaryDto> queryCommentary(@RequestBody QueryCommentaryRuquest queryCommentaryRuquest){
        QueryCommentaryDto queryCommentaryDto=commentaryService.queryCommentary(queryCommentaryRuquest);
        SingleResult<QueryCommentaryDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(queryCommentaryDto);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/commentaryByUser")
    public SingleResult<QueryCommentaryDto> commentaryByUser(@RequestBody QueryCommentaryByUser queryCommentaryByUser){
        QueryCommentaryDto queryCommentaryDto=commentaryService.commentaryByUser(queryCommentaryByUser);
        SingleResult<QueryCommentaryDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(queryCommentaryDto);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/allCommentary")
    public SingleResult<QueryCommentaryDto> allCommentary(@RequestBody AllParkDto allParkDto){
        QueryCommentaryDto queryCommentaryDto=commentaryService.allCommentary(allParkDto);
        SingleResult<QueryCommentaryDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(queryCommentaryDto);
        return response;
    }
}
