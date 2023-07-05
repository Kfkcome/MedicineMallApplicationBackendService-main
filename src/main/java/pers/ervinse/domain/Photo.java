package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 照片
 *
 * @author kfk
 * @date 2023/07/05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("photo")
public class Photo {
    Integer PhotosID;
    String PhotoAddress;//图片地址
    @TableField(exist = false)
    private String PhotoBytes;//图片字节
}
