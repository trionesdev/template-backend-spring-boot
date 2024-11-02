package com.trionesdev.wms.core.domains.log.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.log.dao.mapper.OperationLogMapper;
import com.trionesdev.wms.core.domains.log.dao.po.OperationLogPO;
import org.springframework.stereotype.Repository;

@Repository
public class OperationLogDAO extends ServiceImpl<OperationLogMapper, OperationLogPO> {
}
