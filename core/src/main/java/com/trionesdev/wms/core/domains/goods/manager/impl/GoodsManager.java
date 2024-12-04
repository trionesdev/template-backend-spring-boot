package com.trionesdev.wms.core.domains.goods.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.goods.dao.criteria.GoodsCriteria;
import com.trionesdev.wms.core.domains.goods.dao.impl.GoodsDAO;
import com.trionesdev.wms.core.domains.goods.dao.po.GoodsPO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GoodsManager {
    private final GoodsDAO goodRepository;

    public void create(GoodsPO po) {
        goodRepository.save(po);
    }

    public void updateById(GoodsPO po) {
        goodRepository.updateById(po);
    }

    public Optional<GoodsPO> findById(String id) {
        return Optional.ofNullable(goodRepository.getById(id));
    }

    public List<GoodsPO> findList(GoodsCriteria criteria) {
        return goodRepository.selectList(criteria);
    }

    public PageInfo<GoodsPO> findPage(GoodsCriteria criteria) {
        return goodRepository.selectPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        goodRepository.removeByIds(ids);
    }

    public List<GoodsPO> listById(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        return goodRepository.listByIds(ids);
    }
}
