package com.trionesdev.mes.domain.core.dto.manufacture;

import com.trionesdev.mes.domain.core.dto.masterdata.DefectiveDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = true)
public class ManufactureOrderTaskDTO {
    private String orderCode;
    private String processCode;
    private String name;
    private BigDecimal ratio;
    private BigDecimal planQuantity;
    private BigDecimal goodQuantity;
    private BigDecimal defectiveQuantity;
    private List<DefectiveDTO> defectives;
    private Instant planStartTime;
    private Instant planEndTime;
    private Instant actualStartTime;
    private Instant actualEndTime;
}
