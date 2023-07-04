package pers.ervinse.handler.mybatisPlus;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MetaObjectHandler implements com.baomidou.mybatisplus.core.handlers.MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
//            userId = SecurityContextUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            //反正刚注册时没有userid
            userId = 0L;
        }
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setFieldValByName("updateBy", SecurityContextUtils.getUserId(), metaObject);
    }
}
