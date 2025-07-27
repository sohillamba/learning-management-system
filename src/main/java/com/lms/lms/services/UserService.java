package com.lms.lms.services;

import com.lms.lms.entities.User;

import java.util.List;

public interface UserService {

    User create(User req);

    User getById(Long id);

    List<User> getAll();

    User update(Long id, User req);

    void delete(Long id);
}
