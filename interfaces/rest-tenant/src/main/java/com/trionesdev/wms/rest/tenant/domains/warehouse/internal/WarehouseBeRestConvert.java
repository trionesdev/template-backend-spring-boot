package com.trionesdev.wms.rest.tenant.domains.warehouse.internal;

import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseAreaCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseContainerCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseInboundPlanCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseLocationCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseAreaPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseContainerPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseLocationPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseInboundPlanDTO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseInboundPlanCreateRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseInboundPlanQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseInboundPlanUpdateRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseAreaQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseAreaRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseContainerQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseContainerRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseLocationQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseLocationRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.vo.WarehouseInboundPlanVO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("warehouseBeRestBeanConvert")
public interface WarehouseBeRestConvert {

    WarehouseCriteria from(WarehouseQueryRO args);

    WarehousePO from(WarehouseRO args);

    WarehouseAreaCriteria from(WarehouseAreaQueryRO args);

    WarehouseAreaPO from(WarehouseAreaRO args);

    WarehouseLocationCriteria from(WarehouseLocationQueryRO args);

    WarehouseLocationPO from(WarehouseLocationRO args);

    WarehouseContainerCriteria from(WarehouseContainerQueryRO args);

    WarehouseContainerPO from(WarehouseContainerRO args);

    WarehouseInboundPlanCriteria from(WarehouseInboundPlanQueryRO args);

    WarehouseInboundPlanDTO from(WarehouseInboundPlanCreateRO args);

    WarehouseInboundPlanDTO from(WarehouseInboundPlanUpdateRO args);

    WarehouseInboundPlanVO from(WarehouseInboundPlanDTO args);

    List<WarehouseInboundPlanVO> fromInboundPlanDTO(List<WarehouseInboundPlanDTO> args);
}
