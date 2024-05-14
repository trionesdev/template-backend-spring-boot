package com.trionesdev.mes.domain.core.domains.manufacture.service.impl;

import com.trionesdev.mes.domain.core.domains.manufacture.manager.impl.ManufactureOrderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManufactureOrderService {
    private final ManufactureOrderManager manufactureOrderManager;
}
