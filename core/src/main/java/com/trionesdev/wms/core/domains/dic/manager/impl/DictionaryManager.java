package com.trionesdev.wms.core.domains.dic.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.dic.dao.criteria.DictionaryCriteria;
import com.trionesdev.wms.core.domains.dic.dao.impl.DictionaryDAO;
import com.trionesdev.wms.core.domains.dic.dao.impl.DictionaryTypeDAO;
import com.trionesdev.wms.core.domains.dic.dao.po.DictionaryPO;
import com.trionesdev.wms.core.domains.dic.dao.po.DictionaryTypePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DictionaryManager {
    private final DictionaryTypeDAO dictionaryTypeDAO;
    private final DictionaryDAO dictionaryDAO;

    public void createDictionaryType(DictionaryTypePO record) {
        dictionaryTypeDAO.save(record);
    }

    public void deleteDictionaryTypeById(String id) {
        dictionaryTypeDAO.removeById(id);
    }

    public void updateDictionaryTypeById(DictionaryTypePO record) {
        dictionaryTypeDAO.updateById(record);
    }

    public Optional<DictionaryTypePO> findDictionaryTypeById(String id) {
        return dictionaryTypeDAO.getOptById(id);
    }

    public List<DictionaryTypePO> findDictionaryTypeList() {
        return dictionaryTypeDAO.list();
    }

    public void createDictionary(DictionaryPO record) {
        dictionaryDAO.save(record);
    }

    public void deleteDictionaryById(String id) {
        dictionaryDAO.removeById(id);
    }

    public void updateDictionaryById(DictionaryPO record) {
        dictionaryDAO.updateById(record);
    }

    public Optional<DictionaryPO> findDictionaryById(String id) {
        return dictionaryDAO.getOptById(id);
    }

    public List<DictionaryPO> findDictionaryList() {
        return dictionaryDAO.list();
    }

    public PageInfo<DictionaryPO> findDictionaryPage(DictionaryCriteria criteria) {
        return dictionaryDAO.selectPage(criteria);
    }
}
