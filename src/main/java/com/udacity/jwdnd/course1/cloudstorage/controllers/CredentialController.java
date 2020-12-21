package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {

    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/credential")
    public String createOrUpdateCredential(Authentication authentication, Credential credential) {
        int userid = userService.getUserid(authentication.getName());

        if (credentialService.getCredential(credential.getCredentialid()) == null) {
            credentialService.addCredential(credential, userid);
        } else {
            credentialService.updateCredential(credential);
        }
        return "redirect:/result?success";
    }

    @GetMapping("/credential/delete/{credentialid}")
    public String deleteCredential(@PathVariable("credentialid") int credentialid) {
        if (credentialService.getCredential(credentialid) == null) {
            return "redirect:/result?error";
        }
        credentialService.deleteCredential(credentialid);
        return "redirect:/result?success";
    }
}