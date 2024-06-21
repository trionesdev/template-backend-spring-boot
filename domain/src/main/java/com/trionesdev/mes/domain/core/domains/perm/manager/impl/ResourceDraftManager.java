package com.trionesdev.mes.domain.core.domains.perm.manager.impl;

import com.trionesdev.mes.domain.core.domains.perm.repository.impl.ResourceDraftRepository;
import com.trionesdev.mes.domain.core.domains.perm.repository.po.ResourceDraftPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ResourceDraftManager {
    private final ResourceDraftRepository resourceDraftRepository;

    public void create(ResourceDraftPO record) {
        resourceDraftRepository.save(record);
    }

    public void deleteById(String id) {
        resourceDraftRepository.removeById(id);
    }

    public void updateById(ResourceDraftPO record) {
        resourceDraftRepository.updateById(record);
    }

}
