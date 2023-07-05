package pers.ervinse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.ervinse.domain.OperatorLog;

/**
 * @author YangLin
 * @description 针对表【operator_log】的数据库操作Mapper
 * @createDate 2023-07-05 21:48:09
 * @Entity .domain.OperatorLog
 */
public interface OperatorLogMapper extends BaseMapper<OperatorLog> {

    void insertOne(OperatorLog operLog);
}




