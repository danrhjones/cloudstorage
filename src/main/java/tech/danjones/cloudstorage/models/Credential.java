package tech.danjones.cloudstorage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class Credential {

    private Integer credentialid;
    private String url;
    private String username;
    private String password;
    private String key;
    private Integer userid;

}
