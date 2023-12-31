package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//为当前实体类在编译期设置对应的get/set方法，toString方法，hashCode方法，equals方法等
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")//定的数据库表和 JavaBean 进⾏映射
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class User {
    @TableId(type = IdType.AUTO)
    private Integer UserID;
    private String UserName;
    private Integer UserAge;
    private String UserAccount, UserPassword;
    private String UserSex;
    private String UserExtendInfo;


    /**
     * op
     * 0. op_id
     *
     * 1. user_id
     *
     * 2. op_type --0 加入cart  --1 登录  --2 移除  --3 下单 --4 支付  --5确认收货  --6 评论
     *
     * 3. parms 参数
     *
     */

}
