package com.trionesdev.template.core.domains.boss.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossUserCriteria;
import com.trionesdev.template.core.domains.boss.repository.aggregate.entity.BossUser;
import com.trionesdev.template.core.domains.boss.repository.impl.BossUserRepository;
import com.trionesdev.template.core.domains.boss.shared.enums.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BossUserManager {
    private final BossUserRepository bossUserRepository;

    public void create(BossUser bossUser) {
        bossUserRepository.save(bossUser);
    }

    public void deleteById(String id) {
        bossUserRepository.deleteById(id);
    }

    public void updateById(BossUser bossUser) {
        bossUserRepository.updateById(bossUser);
    }

    public Optional<BossUser> findUserById(String id) {
        return bossUserRepository.findById(id);
    }

    public Optional<BossUser> findUserByAccount(BossUser user) {
        Optional<BossUser> userSnap;
        if (Objects.equals(AccountType.PHONE, user.getAccountType())) {
            userSnap = bossUserRepository.findByPhone(user.getAccount());
        } else {
            userSnap = bossUserRepository.findByUsername(user.getAccount());
        }
        return userSnap.filter(userPO -> user.passwordMatch(userPO.getEncodedPassword()));
    }

    public List<BossUser> findUserList(BossUserCriteria criteria) {
        return bossUserRepository.findList(criteria);
    }

    public PageInfo<BossUser> findUserPage(BossUserCriteria criteria) {
        return bossUserRepository.findPage(criteria);
    }

    public List<BossUser> findUserByIds(Collection<String> ids) {
        return bossUserRepository.findListByIds(ids);
    }

}
