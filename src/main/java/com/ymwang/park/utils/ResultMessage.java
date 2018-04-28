package com.ymwang.park.utils;

import java.io.Serializable;

/**
 * @Author: wym
 * @Date: 2018/2/7
 */
public interface ResultMessage {
    ResultMessage SUCCESS                   = new Impl("0", "处理成功");

    ResultMessage ERR_SYS_UNKNOW            = new Impl("00001", "系统异常");
    ResultMessage ERR_SYS_INIT              = new Impl("00002", "系统内部初始化异常");

    ResultMessage ERR_REQ_TRANS_CODE        = new Impl("10000", "交易编码错误");
    ResultMessage ERR_REQ_SIGN              = new Impl("10001", "信息非法，可能被篡改");
    ResultMessage ERR_REQ_DATA              = new Impl("10002", "传入数据格式错误");
    ResultMessage ERR_REQ_PARAM_FORMAT      = new Impl("10003", "传入参数格式错误");
    ResultMessage ERR_REQ_PARAM             = new Impl("10004", "传入参数错误");

    ResultMessage ERR_TIMEOUT               = new Impl("10005", "请求超时");


    String getCode();

    String getMessage();

    class Impl implements ResultMessage, Serializable {
        private String code;
        private String message;

        public Impl() {
        }

        public Impl(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object obj) {
            if(null == obj || !(obj instanceof Impl)){
                return false;
            }
            Impl obj2 = (Impl)obj;
            if(null != code && code.equals(obj2.getCode()) && null != message && message.equals(obj2.getMessage())){
                return true;
            }
            return false;
        }
    }
}
