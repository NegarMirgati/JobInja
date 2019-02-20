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

}
