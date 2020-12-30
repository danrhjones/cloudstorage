package tech.danjones.cloudstorage.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialForm {

    private Integer credentialid;
    private String url;
    private String username;
    private String password;
    private Integer userid;
}
