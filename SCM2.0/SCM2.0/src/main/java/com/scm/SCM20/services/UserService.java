package com.scm.SCM20.services;

import com.scm.SCM20.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(String id);
    User saveUser(User user);
    void deleteUser(String id);
    Optional<User> updateUser(User user);
    List<User> getAllUsers();
    Boolean isUserExist(String id);
    Boolean isUserExistByEmail(String email);

    User getUserByEmail(String email);
}
