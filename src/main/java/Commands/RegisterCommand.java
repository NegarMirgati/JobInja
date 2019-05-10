package Commands;
import DataLayer.DataMappers.user.UserMapper;
import Repositories.*;
import Entities.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class RegisterCommand implements Command{
    private String username;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String bio;
    private String proLink;
    private String password;
    UserMapper um = new UserMapper();

    public RegisterCommand(String username, String password, String firstName, String lastName, String jobTitle, String bio, String proLink) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.bio = bio;
        this.proLink = proLink;
        this.password = password;
    }

    public void execute() throws SQLException {
        HashMap<String, String> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("firstName", firstName);
        userData.put("lastName", lastName);
        userData.put("jobTitle", jobTitle);
        userData.put("profilePictureURL", proLink);
        userData.put("bio", bio);
        userData.put("password", password);

        um.addToTable(userData);
    }

}
