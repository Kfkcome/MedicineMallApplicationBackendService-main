package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品照片
 *
 * @author kfk
 * @date 2023/07/05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("medicine_photo")
public class CommodityPhoto {
    private Integer PhotosID;
    private Integer CommodityID;
}
