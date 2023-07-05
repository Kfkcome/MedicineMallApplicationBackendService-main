package pers.ervinse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.ervinse.domain.OperatorLog;
import pers.ervinse.mapper.OperatorLogMapper;
import pers.ervinse.service.OperatorLogService;

/**
 * @author YangLin
 * @description 针对表【operator_log】的数据库操作Service实现
 * @createDate 2023-07-05 21:48:09
 */
@Service
public class OperatorLogServiceImpl extends ServiceImpl<OperatorLogMapper, OperatorLog>
        implements OperatorLogService {

    @Transactional
    @Override
    public void insertOperlog(OperatorLog operLog) {

//        baseMapper.insertOne(operLog);
        save(operLog);
    }
}




