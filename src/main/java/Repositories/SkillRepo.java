package Repositories;
import Commands.Command;
import Entities.*;
import Exceptions.UserNotFoundException;
import HttpConnection.*;
import Parsers.MyJsonParser;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class SkillRepo {
    private static SkillRepo ourInstance = new SkillRepo();
    private static HashMap<String, Skill> skillList = new HashMap<String, Skill>();

    public static SkillRepo getInstance() {
        return ourInstance;
    }

    private SkillRepo() {
    }

    public static void addSkill(String name, int point ){
        Skill newSkill = new Skill(name, point);
        skillList.put(name, newSkill);
    }

    public static void addSkills(){
        System.out.println("adding skills");
        HttpConnection connection = new HttpConnection();
        try {
            ArrayList<JsonElement> skillList =  connection.httpGet(new URL("http://142.93.134.194:8000/joboonja/skill"));
            ArrayList<Command> command_list = MyJsonParser.parseSkillList(skillList);
            for (Command command : command_list) {
                command.execute();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Skill> getSkillList() {
        return skillList;
    }
}
