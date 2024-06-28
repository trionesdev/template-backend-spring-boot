package com.trionesdev.mes.core.domains.user.repository.impl;

import com.trionesdev.mes.core.domains.user.dao.impl.UserDAO;
import com.trionesdev.mes.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.core.domains.user.internal.entity.User;
import com.trionesdev.mes.infrastructure.ddd.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository implements BaseRepository<User, String> {
    private final UserBeanConvert convert;
    private final UserDAO userDAO;

    public String save(User user) {
        userDAO.save(convert.entityToPO(user));
        return user.getId();
    }

    public void removeById(String id) {
        userDAO.removeById(id);
    }

    public void updateById(User user) {
        userDAO.updateById(convert.entityToPO(user));
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userDAO.getById(id)).map(convert::from);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userDAO.selectByUsername(username)).map(convert::from);
    }

    public Optional<User> findByPhone(String phone) {
        return Optional.ofNullable(userDAO.selectByPhone(phone)).map(convert::from);
    }


}
