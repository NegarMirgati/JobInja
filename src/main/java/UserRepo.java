import java.util.HashMap;

public class UserRepo {
    private static UserRepo ourInstance = new UserRepo();
    private static HashMap<String, User> userList = new HashMap<String, User>();

    public static UserRepo getInstance() {
        return ourInstance;
    }

    private UserRepo() {
    }



    public static User findItemInUserList(String username) {
        return userList.get(username);
    }

    public static HashMap<String, User> getUserList() {
        return userList;
    }

    public static void addUser(String username, HashMap<String, Skill> skills) {
        User newUser = new User(username, skills);
        userList.put(username, newUser);
    }
}
