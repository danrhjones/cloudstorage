package tech.danjones.cloudstorage.services;

import tech.danjones.cloudstorage.mapper.FileMapper;
import tech.danjones.cloudstorage.mapper.UserMapper;
import tech.danjones.cloudstorage.models.File;
import tech.danjones.cloudstorage.models.User;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper,
        UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public File getFile(int fileid) {
        return fileMapper.findOne(fileid);
    }

    public File getFile(String filename) {
        return fileMapper.findByFilename(filename);
    }

    public void addFile(MultipartFile multipartFile, int userid) throws IOException {
        File file = new File();
        file.setContenttype(multipartFile.getContentType());
        file.setFiledata(multipartFile.getBytes());
        file.setFilename(multipartFile.getOriginalFilename());
        file.setFilesize(Long.toString(multipartFile.getSize()));

        fileMapper.insertFile(file, userid);
    }

    public List<File> getAllfiles(String username) {
        User user = userMapper.getUser(username);
        return fileMapper.findByUserId(user.getUserId());
    }

    public void deleteFile(int fileid) {
        fileMapper.deleteFile(fileid);
    }

}
