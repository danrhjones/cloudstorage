package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Note {

    private int noteid;
    private String notetitle;
    private String notedescription;
    private Integer userid;
}

