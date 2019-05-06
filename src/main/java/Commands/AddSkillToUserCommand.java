package Commands;

import DataLayer.DataMappers.user.UserMapper;
import DataLayer.DataMappers.user.UserSkillMapper;
import Exceptions.AddSkillAlreadyDoneException;
import Exceptions.InvalidSkillException;
import Exceptions.UserNotFoundException;
import Repositories.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddSkillToUserCommand implements Command {
    private String uId;
    private String skillName;
    private UserSkillMapper um = new UserSkillMapper();

    public AddSkillToUserCommand(String uId, String skillName) {
        this.uId = uId;
        this.skillName = skillName;
    }

    public void execute() throws UserNotFoundException, AddSkillAlreadyDoneException{
        System.out.println("LDLDLDLDLDLDLDLDLDLDLDLDLDLDL");
        ArrayList<String> values = new ArrayList<>();
        values.add(uId);
        values.add(skillName);
        values.add("0");
        try {
            um.addToTable("userSkills", um.createAttribute(), values);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
