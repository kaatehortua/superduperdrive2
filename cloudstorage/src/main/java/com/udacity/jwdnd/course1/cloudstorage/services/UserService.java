package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService=hashService;
    }

    public int createUser(User user){
        SecureRandom rand= new SecureRandom();
        byte[] salt=new byte[16];
        rand.nextBytes(salt);
        String encode= Base64.getEncoder().encodeToString(salt);
        String hased=hashService.getHashedValue(user.getPassword(), encode);


        return userMapper.insert(new User(null, user.getUsername(),hased, user.getFirstName(), user.getLastName(),encode));
    }
    public boolean avaliable(String username){
        return userMapper.getUser(username)==null;
    }
    public User getUser(String username){
        return userMapper.getUser(username);
    }

    public User getAuth(Authentication aut){
        return userMapper.getUser(aut.getName());
    }
}
