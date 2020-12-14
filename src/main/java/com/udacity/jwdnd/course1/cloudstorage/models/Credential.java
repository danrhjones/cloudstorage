package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class Credential {

    private String credentialId;
    private String url;
    private String username;
    private String password;
    private int userId;

}
