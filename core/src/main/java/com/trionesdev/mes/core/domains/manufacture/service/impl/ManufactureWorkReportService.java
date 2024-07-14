package com.trionesdev.mes.core.domains.manufacture.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.manufacture.dao.criteria.ManufactureWorkReportCriteria;
import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureWorkReportDTO;
import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureWorkReportDetailDTO;
import com.trionesdev.mes.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.core.domains.manufacture.manager.impl.ManufactureOrderManager;
import com.trionesdev.mes.core.domains.manufacture.manager.impl.ManufactureWorkReportManager;
import com.trionesdev.mes.core.domains.masterdata.dto.ProductDefinitionDTO;
import com.trionesdev.mes.core.domains.masterdata.dto.UnitDTO;
import com.trionesdev.mes.core.domains.masterdata.provider.impl.MasterDataProvider;
import com.trionesdev.mes.core.domains.org.provider.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public PageInfo<ManufactureWorkReportDetailDTO> findPage(ManufactureWorkReportCriteria criteria){
        var pageInfo = workReportManager.findPage(criteria);
        return PageUtils.of(pageInfo, assembleReports(pageInfo.getRows()));
    }

    private List<ManufactureWorkReportDetailDTO> assembleReports(List<ManufactureWorkReportDTO> reports) {
        var productCodes = reports.stream().map(report -> {
            return Optional.ofNullable(report.getOrder()).map(ManufactureWorkReportDTO.Order::getProductCode).orElse(null);
        }).collect(Collectors.toSet());
        var productsMap = masterDataProvider.getProductsByCodes(productCodes).stream().collect( Collectors.toMap(ProductDefinitionDTO::getCode, product -> product));
        var unitIds = reports.stream().map(ManufactureWorkReportDTO::getUnitId).collect(Collectors.toSet());
        var unitsMap = masterDataProvider.getUnitsByIds(unitIds).stream().collect(Collectors.toMap(UnitDTO::getId, unit -> unit,(v1,v2)->v1));
        return reports.stream().map(report -> {
            var reportDetail = convert.reportRecordToDetail(report);
            var productCode = Optional.ofNullable(report.getOrder()).map(ManufactureWorkReportDTO.Order::getProductCode).orElse(null);
            reportDetail.setProduct(productsMap.get(productCode));
            reportDetail.setUnit(unitsMap.get(report.getUnitId()));
        return reportDetail;
        }).toList();
    }

}
