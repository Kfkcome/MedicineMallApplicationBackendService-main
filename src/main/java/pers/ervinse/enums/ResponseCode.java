package pers.ervinse.enums;


public enum ResponseCode {

    SUCCESS(200, "操作成功!"),
    FAILURE(201, "操作失败"),
    /**
     * 系统相关的错误码：5开头
     **/
    INTERNAL_SERVER_ERROR(500, "系统异常，请稍后重试"),
    /**
     * 参数相关的错误码：1开头
     **/
    PARAM_ERROR(1000, "参数异常"),

    /**
     * 权限相关的错误码：2开头
     **/

    FORBIDDEN(403, "需要登录后访问"),

    INVALID_TOKEN(2001, "访问令牌不合法"),
    ACCESS_DENIED(2002, "没有权限访问该资源"),
    USERNAME_OR_PASSWORD_ERROR(2003, "用户名或密码错误");

    private final int code;

    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
