package pers.ervinse.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评价
 *
 * @author kfk
 * @date 2023/07/05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("review")
public class Review {
    private Integer ReviewID;//评论id
    private String ReviewText;//评论内容
    private Integer UserID;//评论用户的id
    private Integer CommodityID;//商品id
    private Integer CommentLevel;
    @TableField(exist = false)
    private Integer OrderID;
}
