package pers.ervinse.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName operator_log
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("operator_log")
public class OperatorLog implements Serializable {

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 操作类型
     */
    private String type;
    /**
     * 详细介绍
     */
    private String description;
    /**
     * 请求路径
     */
    private String url;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 请求类方法全路径
     */
    @TableField("class_method")
    private String classMethod;
    /**
     * ip
     */
    private String ip;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 操作时间
     */
    @TableField("operator_time")
    private Date operatorTime;
}
