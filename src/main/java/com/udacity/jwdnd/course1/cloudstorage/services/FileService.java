package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper,
        UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public File getFile(int fileid) {
        return fileMapper.findOne(fileid);
    }

    public int addFile(MultipartFile multipartFile, int userid) throws IOException {
        File file = new File();
        file.setContenttype(multipartFile.getContentType());
        file.setFiledata(multipartFile.getBytes());
        file.setFilename(multipartFile.getOriginalFilename());
        file.setFilesize(Long.toString(multipartFile.getSize()));;

        return fileMapper.insertFile(file, userid);
    }

    public List<File> getAllfiles(String username) {
        User user = userMapper.getUser(username);
        return fileMapper.findByUserId(user.getUserId());
    }

    public void deleteFile(int fileid) {
        fileMapper.deleteFile(fileid);
    }
}
