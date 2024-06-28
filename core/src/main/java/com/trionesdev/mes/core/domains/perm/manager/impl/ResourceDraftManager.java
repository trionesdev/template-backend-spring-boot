package com.trionesdev.mes.core.domains.perm.manager.impl;

import com.trionesdev.mes.core.domains.perm.dao.po.ResourceDraftPO;
import com.trionesdev.mes.core.domains.perm.dao.impl.ResourceDraftDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ResourceDraftManager {
    private final ResourceDraftDAO resourceDraftRepository;

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
