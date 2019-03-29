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
        String bio = "ملطان غم مادر";
        String proLink  = "https://static.yeklist.com/kala/images/1/p-96.jpg";
        Skill s = new  Skill("HTML", 5);
        Skill s1 = new Skill("Javascript", 4);
        Skill s2 = new Skill("C++", 2);
        Skill s3 = new Skill("Java", 3);
        HashMap<String, Skill> map = new HashMap<String, Skill>();
        map.put("HTML", s);
        map.put("Javascript", s1);
        map.put("C++", s2);
        map.put("Java", s3);
        User u = new User("1", "پسر", "عمه زا","خارخاسک", proLink, map, bio);
        UserRepo.addUser(u);
    }

    public static void addUser2(){
        System.out.println("adding user");
        String bio = "سیرداغ";
        Skill s = new  Skill("Linux", 5);
        Skill s1 = new Skill("SEO", 4);
        Skill s2 = new Skill("C", 2);
        Skill s3 = new Skill("Java", 3);
        HashMap<String, Skill> map = new HashMap<String, Skill>();
        map.put("Linux", s);
        map.put("SEO", s1);
        map.put("C", s2);
        map.put("Java", s3);
        User u = new User("2", "فامیل", "دور","پدر نمونه", "http://cdn-tehran.wisgoon.com/dlir-s3/10531477474534427630.jpeg",map, bio);
        UserRepo.addUser(u);
    }

    public static void addUser3(){
        System.out.println("adding user");
        String bio = "مگه چیه؟ عیده دیگه";
        Skill s = new  Skill("HTML", 5);
        Skill s1 = new Skill("C", 6);
        Skill s2 = new Skill("Photoshop", 10);

        HashMap<String, Skill> map = new HashMap<String, Skill>();
        map.put("HTML", s);
        map.put("C", s1);
        map.put("Photoshop", s2);

        User u = new User("3", "پسر", "خاله","نونوا", "http://www.soonami.ir/wp-content/uploads/49384726_617182022059223_729630059174640160_n.jpg",map, bio);
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
