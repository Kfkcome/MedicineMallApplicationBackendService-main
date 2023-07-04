package pers.ervinse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.ervinse.domain.Goods;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {


    List<Goods> selectHotGoods();
    Goods selectByCommodityIDGoods(@Param("CommodityID") Integer CommodityID);
}
