package pers.ervinse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.ervinse.domain.OperatorLog;

/**
 * @author YangLin
 * @description 针对表【operator_log】的数据库操作Service
 * @createDate 2023-07-05 21:48:09
 */
public interface OperatorLogService extends IService<OperatorLog> {

    void insertOperlog(OperatorLog operLog);
}
