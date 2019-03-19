package Repositories;
import Entities.*;
import Exceptions.AddSkillAlreadyDoneException;
import Exceptions.EndorseAlreadyDoneException;
import Exceptions.SkillNotFoundException;
import Exceptions.UserNotFoundException;

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


    public static User findItemInUserList(String username) throws UserNotFoundException {
        if(userList.get(username) != null)
            return userList.get(username);
        else
            throw new UserNotFoundException("User Not Found");
    }

    public static User getUserById(String id) throws UserNotFoundException{
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

    public static void addUser2(){
        System.out.println("adding user");
        String bio = "Stay hungry, stay foolish.";
        Skill s = new  Skill("Linux", 5);
        Skill s1 = new Skill("SEO", 4);
        Skill s2 = new Skill("C", 2);
        Skill s3 = new Skill("Java", 3);
        HashMap<String, Skill> map = new HashMap<String, Skill>();
        map.put("Linux", s);
        map.put("SEO", s1);
        map.put("C", s2);
        map.put("Java", s3);
        User u = new User("2", "استیو", "جابز","business magnate", "https://2ch.hk/b/arch/2018-12-06/src/187673928/15440521664830.jpg",map, bio);
        UserRepo.addUser(u);
    }

    public static void addUser3(){
        System.out.println("adding user");
        String bio = "Your most unhappy customers are your greatest source of learning.";
        Skill s = new  Skill("HTML", 5);
        Skill s1 = new Skill("C", 6);
        Skill s2 = new Skill("Photoshop", 10);

        HashMap<String, Skill> map = new HashMap<String, Skill>();
        map.put("HTML", s);
        map.put("C", s1);
        map.put("Photoshop", s2);

        User u = new User("3", "بیل", "گیتس","business magnate", "https://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2018/07/11/105322791-1531301768595gettyimages-467620670.1910x1000.jpg",map, bio);
        UserRepo.addUser(u);
    }

    public static void endorse(String id, String skill) throws UserNotFoundException, EndorseAlreadyDoneException, SkillNotFoundException {
        User u = findItemInUserList(id);
        if(!(u.getSkills().containsKey(skill)))
            throw new SkillNotFoundException("Skill not found");

        if ( !(u.getSkills().get(skill).hasEndorsed(id))){
            u.endorse(skill);
            u.getSkills().get(skill).addEndorser(id);
        }
        else{
            throw new EndorseAlreadyDoneException("Conflict");
        }
    }

    public static void delSkill(String userId, String SkillName) throws UserNotFoundException, SkillNotFoundException {
        User u = UserRepo.findItemInUserList(userId);
        u.delSkill(SkillName);
    }

    public static void addSkillToUser(String uId, String skillName) throws UserNotFoundException, AddSkillAlreadyDoneException {
        User u = findItemInUserList(uId);
        if (!u.getSkills().containsKey(skillName)) {
            Skill s = new Skill(skillName, 0);
            u.addSkill(skillName, s);
        }
        else throw new AddSkillAlreadyDoneException("skill is already in skill list");
    }

}
