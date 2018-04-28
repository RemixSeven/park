package com.ymwang.park.utils;
import io.swagger.annotations.ApiModel;
/**
 * @Author: wym
 * @Date: 2018/2/7
 */


@ApiModel(
        description = "业务返回对象"
)
public class SingleResult<T> extends BaseResult {
    private T data;

    public SingleResult(ResultMessage rm) {
        super(rm);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SingleResult() {
    }
}
