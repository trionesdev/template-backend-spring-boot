package com.trionesdev.template.core.domains.bossuser.repository.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.template.core.domains.bossuser.dao.criteria.BossUserCriteria;
import com.trionesdev.template.core.domains.bossuser.dao.impl.BossUserDAO;
import com.trionesdev.template.core.domains.bossuser.dao.po.BossUserPO;
import com.trionesdev.template.core.domains.bossuser.internal.BossUserDomainConvert;
import com.trionesdev.template.core.domains.bossuser.repository.aggregate.entity.BossUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BossUserRepository {
    private final BossUserDomainConvert convert;
    private final BossUserDAO bossUserDAO;

    public void save(BossUser bossUser) {
        var bossUserPo = convert.userEntityToPo(bossUser);
        bossUserDAO.save(bossUserPo);
    }

    public void updateById(BossUser bossUser) {
        var bossUserPo = convert.userEntityToPo(bossUser);
        bossUserDAO.updateById(bossUserPo);
    }

    public Optional<BossUser> findById(String id) {
        return Optional.ofNullable(bossUserDAO.getById(id)).map(convert::usePoToEntity);
    }

    public Optional<BossUser> findByUsername(String username) {
        return Optional.ofNullable(bossUserDAO.selectByUsername(username)).map(convert::usePoToEntity);
    }

    public Optional<BossUser> findByPhone(String phone) {
        return Optional.ofNullable(bossUserDAO.selectByPhone(phone)).map(convert::usePoToEntity);
    }

    public List<BossUser> assembleBossUsers(List<BossUserPO> records) {
        if (CollectionUtils.isEmpty(records)) {
            return new ArrayList<>();
        }
        return records.stream().map(record -> {
            return convert.usePoToEntity(record);
        }).collect(Collectors.toList());
    }

    public List<BossUser> findList(BossUserCriteria criteria) {
        return assembleBossUsers(bossUserDAO.selectList(criteria));
    }

    public PageInfo<BossUser> findPage(BossUserCriteria criteria) {
        var pageInfo = bossUserDAO.selectPage(criteria);
        return PageUtils.of(pageInfo, assembleBossUsers(pageInfo.getRows()));
    }

}
