package com.trionesdev.wms.core.domains.dic.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.dic.dao.criteria.DictionaryCriteria;
import com.trionesdev.wms.core.domains.dic.dao.po.DictionaryPO;
import com.trionesdev.wms.core.domains.dic.manager.impl.DictionaryManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DictionaryService {
    private final DictionaryManager dictionaryManager;


    public void createDictionary(DictionaryPO dictionaryPO) {
        dictionaryManager.createDictionary(dictionaryPO);
    }

    public void deleteDictionaryById(String id) {
        dictionaryManager.deleteDictionaryById(id);
    }

    public void updateDictionaryById(DictionaryPO dictionaryPO) {
        dictionaryManager.updateDictionaryById(dictionaryPO);
    }

    public Optional<DictionaryPO> findDictionaryById(String id) {
        return dictionaryManager.findDictionaryById(id);
    }

    public PageInfo<DictionaryPO> findDictionaryPage(DictionaryCriteria criteria) {
        return dictionaryManager.findDictionaryPage(criteria);
    }

}
