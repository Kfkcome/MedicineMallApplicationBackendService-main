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
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class User {
    @TableId(type = IdType.AUTO)
    private Integer UserID;
    private String UserName;
    private Integer UserAge;
    private String UserAccount, UserPassword;
    private String UserSex;
    private String UserExtendInfo;

}
