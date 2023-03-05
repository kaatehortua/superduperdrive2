package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
@Service
public class CredentialService {
    private EncryptionService encryptionService;
    private CredentialMapper mapper;

    public CredentialService(EncryptionService encryptionService, CredentialMapper mapper) {
        this.encryptionService = encryptionService;
        this.mapper = mapper;
    }

    public ArrayList<CredentialModel> getListCredential(int userid){
        return (ArrayList) mapper.getCredential(userid);
    }
    public boolean insertCredentials(CredentialModel credential, int userid){
        SecureRandom rand= new SecureRandom();
        byte[] key= new byte[16];
        rand.nextBytes(key);
        String encode= Base64.getEncoder().encodeToString(key);
        String encrypt=encryptionService.encryptValue(credential.getPass(), encode);

        CredentialModel cred = new CredentialModel();
        cred.setId(credential.getUserid());
        cred.setPass(encrypt);
        cred.setKey(encode);
        cred.setUsername(credential.getUsername());
        cred.setUrl(credential.getUrl());


        return mapper.insertCredential(cred);
    }
    public boolean updateCredential(CredentialModel credential){
        CredentialModel savedCred= mapper.getCredentialbyId(credential.getId());
        credential.setKey(savedCred.getKey());
        String encrypt= encryptionService.encryptValue(credential.getPass(), credential.getKey());
        credential.setPass(encrypt);

        return mapper.updateCredentia(credential);
    }

    public int deleteCred(int credentialid){
        int deletedCred=mapper.deleteCredential(credentialid);
        return deletedCred;
    }
}
