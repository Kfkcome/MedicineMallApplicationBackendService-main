package pers.ervinse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.ervinse.domain.Commodity;

import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {


    List<Commodity> selectHotCommodity();

    Commodity selectByCommodityIDCommodity(@Param("CommodityID") Integer CommodityID);
}
