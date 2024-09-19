package com.trionesdev.wms.core.domains.dic.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.dic.dao.criteria.DictionaryCriteria;
import com.trionesdev.wms.core.domains.dic.dao.po.DictionaryPO;
import com.trionesdev.wms.core.domains.dic.dao.po.DictionaryTypePO;
import com.trionesdev.wms.core.domains.dic.manager.impl.DictionaryManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DictionaryService {
    private final DictionaryManager dictionaryManager;

    public void createDictionaryType(DictionaryTypePO dictionaryTypePO) {
        dictionaryManager.createDictionaryType(dictionaryTypePO);
    }

    public void deleteDictionaryTypeById(String id) {
        dictionaryManager.deleteDictionaryTypeById(id);
    }

    public void updateDictionaryTypeById(DictionaryTypePO dictionaryTypePO) {
        dictionaryManager.updateDictionaryTypeById(dictionaryTypePO);
    }

    public Optional<DictionaryTypePO> findDictionaryTypeById(String id) {
        return dictionaryManager.findDictionaryTypeById(id);
    }

    public List<DictionaryTypePO> findDictionaryTypeList() {
        return dictionaryManager.findDictionaryTypeList();
    }

    public void createDictionary(DictionaryPO dictionaryPO) {
        dictionaryManager.createDictionary(dictionaryPO);
    }

    public void deleteDictionaryById(DictionaryPO dictionaryPO) {
        dictionaryManager.deleteDictionaryById(dictionaryPO.getId());
    }

    public void updateDictionaryById(DictionaryPO dictionaryPO) {
        dictionaryManager.updateDictionaryById(dictionaryPO);
    }

    public PageInfo<DictionaryPO> findDictionaryPage(DictionaryCriteria criteria) {
        return dictionaryManager.findDictionaryPage(criteria);
    }

}
