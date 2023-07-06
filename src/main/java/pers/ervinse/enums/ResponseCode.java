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
    USERNAME_OR_PASSWORD_ERROR(2003, "用户名或密码错误"),
    UPDATE_ERROR(2004, "没有权限修改他人地址"),
    UPDATE_ERROR_NOT_EXIT(2005, "该地址不存在无法修改"),
    DONOT_HAVE_CART(3001, "购物车不存在"), COMMODITY_NOT_EXITS(3002, "商品不存在"),
    CANNOT_GENERATE_ORDER(2500, "无此商品或商品数量为零无法创建订单"),
    CANNOT_FIND_ORDER(2501, "查询不到此订单"),
    CANNOT_CHANGE_OTHER_ORDER(2502, "不能删除不是本机用户的订单"),
    ORDER_NOT_EXIT(2503, "查询不到此订单"),
    LOGISTICS_NOT_EXIT(2030, "查询不到该订单的物流活动"),
    INSERT_ERROR(2010, "插入失败"),
    REVIEW_NOT_EXIT(2020, "查询不到评论"),
    HEAD_NOT_EXIT(2060, "查询不到头像");

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
