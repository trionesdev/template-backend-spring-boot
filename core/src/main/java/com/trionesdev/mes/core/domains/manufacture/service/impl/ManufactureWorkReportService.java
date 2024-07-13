package com.trionesdev.mes.core.domains.manufacture.service.impl;

import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureWorkReportDTO;
import com.trionesdev.mes.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.core.domains.manufacture.manager.impl.ManufactureOrderManager;
import com.trionesdev.mes.core.domains.manufacture.manager.impl.ManufactureWorkReportManager;
import com.trionesdev.mes.core.domains.masterdata.provider.impl.MasterDataProvider;
import com.trionesdev.mes.core.domains.org.provider.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManufactureWorkReportService {
    private final ManufactureBeanConvert convert;
    private final ManufactureOrderManager orderManager;
    private final ManufactureWorkReportManager workReportManager;
    private final MasterDataProvider masterDataProvider;
    private final OrgProvider orgProvider;

    public void createReport(ManufactureWorkReportDTO report) {
        var orderTaskReport = convert.reportDtoToEntity(report);
        workReportManager.create(orderTaskReport);
    }

//    public List<TenantMemberDTO> findTaskReportMembers(String taskId) {
//        ManufactureOrderTaskPO taskPO = orderManager.findTaskById(taskId).orElse(null);
//        if (Objects.isNull(taskPO)) {
//            return Collections.emptyList();
//        }
//        ProcessPermissionGrantDTO grantDTO = masterDataProvider.getProcessPermissionGrant(taskPO.getProcessCode());
//        if (Objects.isNull(grantDTO)) {
//            return Collections.emptyList();
//        }
//        if (grantDTO.getType() == ProcessPermissionGrantDTO.ProcessGrantType.ALL) {
//            return
//        }
//    }

}
