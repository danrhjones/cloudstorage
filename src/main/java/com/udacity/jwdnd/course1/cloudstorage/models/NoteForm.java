package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteForm {

    private Integer noteid;
    private String notetitle;
    private String notedescription;
    private Integer userid;

}
