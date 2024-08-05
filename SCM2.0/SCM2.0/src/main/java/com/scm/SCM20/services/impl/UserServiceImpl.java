package com.scm.SCM20.services.impl;

import com.scm.SCM20.entities.User;
import com.scm.SCM20.helper.AppConstant;
import com.scm.SCM20.helper.ResourceNotFoundException;
import com.scm.SCM20.repositories.UserRepo;
import com.scm.SCM20.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public User saveUser(User user) {
        // generate user id
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // password encoder
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //roles assign
        user.setRoleList(List.of(AppConstant.Role_Name));

        return userRepo.save(user);
    }

    @Override
    public void deleteUser(String id) {
        User user2= userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);
    }

    @Override
    public Optional<User> updateUser(User user) {

        User user2= userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        user2.setAbout(user.getAbout());
        user2.setEmail(user.getEmail());
        user2.setName(user.getName());
        user2.setPassword(user.getPassword());
        user2.setUserId(user.getUserId());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setContacts(user.getContacts());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneNumberVerified(user.isPhoneNumberVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderId(user.getProviderId());
        user2.setEnabled(user.isEnabled());

        return Optional.of(userRepo.save(user2));
    }

    @Override
    public List<User> getAllUsers() {

        return userRepo.findAll();
    }

    @Override
    public Boolean isUserExist(String id) {

        User user2 = userRepo.findById(id).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public Boolean isUserExistByEmail(String email) {

         User user2 = userRepo.findByEmail(email).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
}
