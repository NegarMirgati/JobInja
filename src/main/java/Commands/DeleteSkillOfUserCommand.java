package Commands;
import DataLayer.DataMappers.user.UserSkillMapper;
import Exceptions.SkillNotFoundException;
import Exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.util.*;

public class DeleteSkillOfUserCommand implements Command{
    private String userID;
    private String skillName;
    UserSkillMapper um = new UserSkillMapper();

    public DeleteSkillOfUserCommand(String username, String name) {
        this.userID = username;
        this.skillName = name;
    }

    @Override
    public void execute() throws UserNotFoundException, SkillNotFoundException {
        ArrayList<String> values = new ArrayList<>();
        values.add(userID);
        values.add(skillName);
        try {
            um.deleteFromTable(values);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

