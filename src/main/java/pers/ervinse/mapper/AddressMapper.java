package pers.ervinse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ervinse.domain.Address;

/**
 * 地址映射器
 *
 * @author kfk
 * @date 2023/07/05
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {
}
