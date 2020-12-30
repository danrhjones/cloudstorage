package tech.danjones.cloudstorage.services;


import tech.danjones.cloudstorage.mapper.UserMapper;
import tech.danjones.cloudstorage.models.User;
import java.security.SecureRandom;
import java.util.Base64;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @PostConstruct
    public void postConstruct() {
//        createUser("admin", "admin");
//        createUser("user", "user");
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public void createUser(String username, String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(password, encodedSalt);
        userMapper.insert(new User(null, username, encodedSalt, hashedPassword, "Jonny", "Rotton"));
    }

    public int getUserid(String username) {
        User user = userMapper.getUser(username);
        return user.getUserId();
    }

}
