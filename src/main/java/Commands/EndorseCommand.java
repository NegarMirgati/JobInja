package Commands;
import java.sql.SQLException;
import java.util.*;

import Exceptions.EndorseAlreadyDoneException;
import Exceptions.SkillNotFoundException;
import Exceptions.UserNotFoundException;
import DataLayer.DataMappers.user.EndorsementMapper;
import DataLayer.DataMappers.user.UserSkillMapper;

public class EndorseCommand implements Command{
    private String userId;
    private String skillName;
    private String endorserId;
    EndorsementMapper em = new EndorsementMapper();
    UserSkillMapper sm = new UserSkillMapper();


    public EndorseCommand(String userId, String endorserId, String skillName) {
        this.userId = userId;
        this.skillName = skillName;
        this.endorserId = endorserId;
    }

    @Override
    public void execute() throws UserNotFoundException, EndorseAlreadyDoneException, SkillNotFoundException {
        ArrayList<String> values = new ArrayList<>();

        values.add(endorserId);
        values.add(userId);
        values.add(skillName);
        try {
            em.addToTable(values);
            sm.endorseSkill(userId, skillName);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
