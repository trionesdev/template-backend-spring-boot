package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ManufactureProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManufactureProcessManager {
    private final ManufactureProcessRepository processRepository;

    public void create(ManufactureProcessPO manufactureProcess) {
        processRepository.save(manufactureProcess);
    }

    public void deleteById(String id) {
        processRepository.removeById(id);
    }

    public void updateById(ManufactureProcessPO manufactureProcess) {
        processRepository.updateById(manufactureProcess);
    }

    public Optional<ManufactureProcessPO> findById(String id) {
        return Optional.ofNullable(processRepository.getById(id));
    }

    public Optional<ManufactureProcessPO> findByCode(String code) {
        return Optional.ofNullable(processRepository.selectByCode(code));
    }

    public List<ManufactureProcessPO> findList(ManufactureProcessCriteria criteria) {
        return processRepository.selectList(criteria);
    }

    public PageInfo<ManufactureProcessPO> findPage(ManufactureProcessCriteria criteria) {
        return processRepository.selectPage(criteria);
    }

    public List<ManufactureProcessPO> findListByCodes(Collection<String> codes) {
        return processRepository.selectListByCodes(codes);
    }
}
