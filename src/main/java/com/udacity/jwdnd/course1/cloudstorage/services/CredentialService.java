package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper,
        EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public void addCredential(Credential credential, int userid) {
        credentialMapper.insertCredential(encryptPassword(credential), userid);
    }

    public List<Credential> getAllCredentials(String username) {
        User user = userMapper.getUser(username);
        List<Credential> credentials = credentialMapper.findByUserId(user.getUserId());

        List<Credential> usersCredentials = new ArrayList<>();
        for (Credential credential : credentials) {
            Credential decryptPassword = decryptPassword(credential);
            usersCredentials.add(decryptPassword);
        }
        return usersCredentials;
    }

    private Credential decryptPassword(Credential credential) {
        credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }

    public int getUserId(String username) {
        User user = userMapper.getUser(username);
        return user.getUserId();
    }

    public Credential getCredential(int credentialid) {

        return credentialMapper.findOne(credentialid);
    }

    private Credential encryptPassword(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        credential.setKey(encodedKey);
        credential
            .setPassword(encryptionService.encryptValue(credential.getPassword(), encodedKey));
        return credential;
    }

    public void updateCredential(Credential credential) {
        credentialMapper.updateCredential(encryptPassword(credential));
    }

    public void deleteCredential(int credentialid) {
        credentialMapper.deleteCredential(credentialid);
    }
}
