package com.trionesdev.template.core.domains.dic.manager.impl;

import com.trionesdev.template.core.domains.dic.dao.criteria.CountryCriteria;
import com.trionesdev.template.core.domains.dic.dao.impl.CountryDAO;
import com.trionesdev.template.core.domains.dic.dao.po.CountryPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CountryManager {
    private final CountryDAO countryDAO;

    public List<CountryPO> findList(CountryCriteria criteria) {
        return countryDAO.selectList(criteria);
    }
}
