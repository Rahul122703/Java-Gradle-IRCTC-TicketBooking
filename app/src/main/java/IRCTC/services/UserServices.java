package IRCTC.services;

import IRCTC.entities.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.io.File;

public class UserServices {
    private User user;
    public static final ObjectMapper OM = new ObjectMapper();
    private static final String USER_PATH = "./localDB/users.json";
    private static List<User> usersList;

    public UserServices(User user1) throws Exception {
        this.user = user1;
        File userDataFile = new File(USER_PATH);
        usersList = OM.readValue(userDataFile, new TypeReference<List<User>>() {
        });
    }

}
