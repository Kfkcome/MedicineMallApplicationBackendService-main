package pers.ervinse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.ervinse.domain.CommodityPhoto;
import pers.ervinse.domain.Photo;

import java.util.List;

/**
 * 照片映射器
 *
 * @author kfk
 * @date 2023/07/05
 */
@Mapper
public interface PhotoMapper extends BaseMapper<Photo> {
    List<CommodityPhoto> selectAllPhotoByCommodityID(@Param("CommodityID")Integer CommodityID);
    CommodityPhoto selectOnePhotoByCommodityID(@Param("CommodityID")Integer CommodityID);
}
