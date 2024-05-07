package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.ManufactureBomDAO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.ManufactureBomItemDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManufactureBomManager {
    private final ManufactureBomDAO manufactureBomDAO;
    private final ManufactureBomItemDAO manufactureBomItemDAO;

}
