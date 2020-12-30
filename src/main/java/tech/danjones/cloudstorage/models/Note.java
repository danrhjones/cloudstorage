package tech.danjones.cloudstorage.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Note {

    private Integer noteid;
    private String notetitle;
    private String notedescription;
    private Integer userid;
}

