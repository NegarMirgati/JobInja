package Repositories;
import Entities.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public static User getUserById(String id){
        Iterator it = userList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            User u = (User)pair.getValue();
            if(u.getUsername().equals(id)){
                return u;
            }
        }
        return null;
    }

    public static HashMap<String, User> getUserList() {
        return userList;
    }

    public static void addUser(String username, HashMap<String, Skill> skills) {
        User newUser = new User(username, skills);
        userList.put(username, newUser);
    }

    public static void addUser(User u) {
        String userID = u.getUsername();
        userList.put(userID, u);
    }

    public static void addUser(){
        System.out.println("adding user");
        String bio = "خدا بیامرز میخواست خیلی کارا بکنه ولی پول نداشت";
        Skill s = new  Skill("HTML", 5);
        Skill s1 = new Skill("Javascript", 4);
        Skill s2 = new Skill("C++", 2);
        Skill s3 = new Skill("Java", 3);
        HashMap<String, Skill> map = new HashMap<String, Skill>();
        map.put("HTML", s);
        map.put("Javascript", s1);
        map.put("C++", s2);
        map.put("Java", s3);
        User u = new User("1", "علی", "شریف زاده","برنامەنویس وب", " ",map, bio);
        UserRepo.addUser(u);
    }

}
