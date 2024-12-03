package com.trionesdev.wms.core.domains.good.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.good.dao.criteria.GoodCriteria;
import com.trionesdev.wms.core.domains.good.dao.impl.GoodDAO;
import com.trionesdev.wms.core.domains.good.dao.po.GoodPO;
import com.trionesdev.wms.core.domains.good.dto.GoodDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GoodManager {
    private final GoodDAO goodRepository;

    public void create(GoodPO po) {
        goodRepository.save(po);
    }

    public void updateById(GoodPO po) {
        goodRepository.updateById(po);
    }

    public Optional<GoodPO> findById(String id) {
        return Optional.ofNullable(goodRepository.getById(id));
    }

    public List<GoodPO> findList(GoodCriteria criteria) {
        return goodRepository.selectList(criteria);
    }

    public PageInfo<GoodPO> findPage(GoodCriteria criteria) {
        return goodRepository.selectPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        goodRepository.removeByIds(ids);
    }

    public List<GoodPO> listById(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        return goodRepository.listByIds(ids);
    }
}
