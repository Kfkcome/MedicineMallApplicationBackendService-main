package pers.ervinse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ervinse.domain.Merchant;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {
}
