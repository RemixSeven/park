package com.ymwang.park.utils;

/**
 * @Author: wym
 * @Date: 2018/4/26
 */
import java.io.Serializable;

public class BaseResult implements Serializable {
    private ResultMessage result;

    public BaseResult(ResultMessage rm) {
        this.result = rm;
    }

    public BaseResult success() {
        this.result = ResultMessage.SUCCESS;
        return this;
    }

    public boolean isSuccess() {
        return this.result == ResultMessage.SUCCESS;
    }

    public String getTime() {
        return DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
    }

    public String getMessageCode() {
        return this.result.getCode();
    }

    public String getMessage() {
        return this.result.getMessage();
    }

    public BaseResult() {
    }
}
