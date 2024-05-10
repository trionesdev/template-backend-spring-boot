package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ManufactureBomRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ManufactureBomItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManufactureBomManager {
    private final ManufactureBomRepository manufactureBomDAO;
    private final ManufactureBomItemRepository manufactureBomItemDAO;

}
