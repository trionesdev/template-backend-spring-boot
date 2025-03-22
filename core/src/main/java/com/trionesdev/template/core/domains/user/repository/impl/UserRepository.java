package com.trionesdev.template.core.domains.user.repository.impl;

import com.trionesdev.template.core.domains.user.dao.impl.UserDAO;
import com.trionesdev.template.core.domains.user.internal.UserDomainConvert;
import com.trionesdev.template.core.domains.user.internal.entity.User;
import com.trionesdev.template.infrastructure.ddd.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository implements BaseRepository<User, String> {
    private final UserDomainConvert convert;
    private final UserDAO userDAO;

    public String save(User user) {
        var userPo = convert.entityToPO(user);
        userDAO.save(userPo);
        user.setId(userPo.getId());
        return userPo.getId();
    }

    public void removeById(String id) {
        userDAO.removeById(id);
    }

    public void updateById(User user) {
        userDAO.updateById(convert.entityToPO(user));
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userDAO.getById(id)).map(convert::userPoToEntity);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userDAO.selectByUsername(username)).map(convert::userPoToEntity);
    }

    public Optional<User> findByPhone(String phone) {
        return Optional.ofNullable(userDAO.selectByPhone(phone)).map(convert::userPoToEntity);
    }
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userDAO.selectByEmail(email)).map(convert::userPoToEntity);
    }

}
