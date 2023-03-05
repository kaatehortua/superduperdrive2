package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import org.springframework.stereotype.Service;

import javax.annotation.processing.Generated;
import java.security.SecureRandom;
import java.util.Base64;
@Service
public class UserService {

    private UserMapper mapper;
    private HashService serviceH;

    private Integer avaliable=null;


    public UserService(UserMapper mapper, HashService serviceH) {
        this.mapper = mapper;
        this.serviceH = serviceH;
    }

 public boolean checkUserName(String name) throws Exception {
     return mapper.getUser(name)==null;
    }

    public int insertUser(UserModel user){
        SecureRandom random= new SecureRandom();
        byte[] salt= new byte[16];
        random.nextBytes(salt);
        String encode= Base64.getEncoder().encodeToString(salt);
        String hash=serviceH.getHashedValue(user.getPassword(),encode);
        return mapper.insert(new UserModel(null, user.getUsername(),encode,hash,user.getFirstname(),user.getLastname()));
    }
    public UserModel getUser(String username) {
        return mapper.getUser(username);
    }
}
