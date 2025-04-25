package com.trionesdev.template.core.domains.bossuser.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.bossuser.dao.mapper.BossUserMapper;
import com.trionesdev.template.core.domains.bossuser.dao.po.BossUserPO;
import org.springframework.stereotype.Repository;

@Repository
public class BossUserDAO extends ServiceImpl<BossUserMapper, BossUserPO> {
}
