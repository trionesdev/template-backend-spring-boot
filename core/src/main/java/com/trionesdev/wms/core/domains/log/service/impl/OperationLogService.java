package com.trionesdev.wms.core.domains.log.service.impl;

import com.google.common.collect.Lists;
import com.trionesdev.commons.context.actor.ActorRoleEnum;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.wms.core.domains.log.dao.criteria.OperationLogCriteria;
import com.trionesdev.wms.core.domains.log.dao.po.OperationLogPO;
import com.trionesdev.wms.core.domains.log.dto.OperationLogExtDTO;
import com.trionesdev.wms.core.domains.log.dto.OperationLogExtDTO.Operator;
import com.trionesdev.wms.core.domains.log.internal.OperationLogConvert;
import com.trionesdev.wms.core.domains.log.manager.impl.OperationLogManager;
import com.trionesdev.wms.core.domains.org.dto.TenantMemberDetailDTO;
import com.trionesdev.wms.core.domains.org.provider.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OperationLogService {
    private final OperationLogConvert convert;
    private final OperationLogManager operationLogManager;
    private final OrgProvider userProvider;

    private List<OperationLogExtDTO> assembleOperationLogExtBatch(List<OperationLogPO> records) {
        if (CollectionUtils.isEmpty(records)) {
            return Lists.newArrayList();
        }
        var userIds = records.stream().filter(record -> Objects.equals(ActorRoleEnum.TENANT_MEMBER.name(), record.getActorRole())).map(OperationLogPO::getActorId).collect(Collectors.toList());
        var userMap = userProvider.getMembersByUserIds(userIds).stream().collect(Collectors.toMap(TenantMemberDetailDTO::getUserId, v -> v, (v1, v2) -> v1));
        return records.stream().map(record -> {
            var operationLog = convert.operationLogPoToExtDto(record);
            if (Objects.equals(ActorRoleEnum.TENANT_MEMBER.name(), record.getActorRole())) {
                Optional.ofNullable(userMap.get(record.getActorId())).ifPresent(user -> {
                    operationLog.setOperator(Operator.builder().username(user.getUsername()).nickname(user.getNickname()).build());
                });
            } else {
                operationLog.setOperator(Operator.builder().nickname("运营人员").build());
            }
            return operationLog;
        }).collect(Collectors.toList());
    }

    public PageInfo<OperationLogExtDTO> findPage(OperationLogCriteria criteria) {
        var pageInfo = operationLogManager.findPage(criteria);
        return PageUtils.of(pageInfo, assembleOperationLogExtBatch(pageInfo.getRows()));
    }

}
