package pers.ervinse.exception;

import pers.ervinse.enums.ResponseCode;

public class SystemException extends RuntimeException {

    private int code;

    private String msg;

    public SystemException(ResponseCode httpCodeEnum) {
        super(httpCodeEnum.getMessage());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMessage();
    }

    public SystemException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
