package com.trionesdev.mes.core.domains.manufacture.service.impl;

import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureOrderTaskReportDetailDTO;
import com.trionesdev.mes.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.core.domains.manufacture.manager.impl.ManufactureOrderManager;
import com.trionesdev.mes.core.domains.manufacture.manager.impl.ManufactureOrderTaskReportManager;
import com.trionesdev.mes.core.domains.masterdata.provider.impl.MasterDataProvider;
import com.trionesdev.mes.core.domains.org.provider.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManufactureOrderTaskReportService {
    private final ManufactureBeanConvert convert;
    private final ManufactureOrderManager orderManager;
    private final ManufactureOrderTaskReportManager orderTaskReportManager;
    private final MasterDataProvider masterDataProvider;
    private final OrgProvider orgProvider;

    public void createReport(ManufactureOrderTaskReportDetailDTO report) {
        var orderTaskReport = convert.reportDtoToEntity(report);
        orderTaskReportManager.create(orderTaskReport);
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
