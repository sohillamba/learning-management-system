package com.lms.lms.services.impl;

import com.lms.lms.entities.User;
import com.lms.lms.exception.ResourceNotFoundException;
import com.lms.lms.repo.UserRepository;
import com.lms.lms.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl (UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User create(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id is null or invalid!");
        }
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();

    }

    @Override
    public User update(Long id, User user) {
        if (id == null) {
            throw new IllegalArgumentException("User id is null or invalid!");
        }
        User existing = getById(id);
        existing.setEmail(user.getEmail());
        existing.setUsername(user.getUsername());
        // password update handled separately
        return userRepo.save(user);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id is null or invalid!");
        }
        userRepo.deleteById(id);
    }
}
