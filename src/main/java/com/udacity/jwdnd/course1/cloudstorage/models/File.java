package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class File {

    private String fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private int userid;
    private String filedata;
    private int userId;

}
