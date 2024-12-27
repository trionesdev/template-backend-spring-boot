package com.trionesdev.template.core.domains.dic.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.dic.dao.criteria.DictionaryCriteria;
import com.trionesdev.template.core.domains.dic.dao.po.DictionaryPO;
import com.trionesdev.template.core.domains.dic.manager.impl.DictionaryManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<DictionaryPO> findDictionaryList(DictionaryCriteria criteria) {
        return dictionaryManager.findDictionaryList(criteria);
    }

    public PageInfo<DictionaryPO> findDictionaryPage(DictionaryCriteria criteria) {
        return dictionaryManager.findDictionaryPage(criteria);
    }

}
