package DataLayer.DataMappers.user;
import Entities.Skill;
import Entities.User;
import DataLayer.DataMappers.Mapper;
import java.sql.*;
import DataLayer.*;
import java.util.*;
import java.util.Map;

public class UserMapper extends Mapper<User, String> implements IUserMapper{

    private static final String COLUMNS = "username, firstName, lastName, jobTitle, profilePictureURL, bio";

    public void initialize() throws SQLException{
        System.out.println("here for user Table!!");
        Connection con = DBCPDBConnectionPool.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "user" + " " + "(username TEXT PRIMARY KEY, firstName TEXT," +
                " lastName TEXT, jobTitle TEXT, profilePictureURL TEXT, bio TEXT)");
        try {
            fillTable(con);
        } catch (SQLException e){
            e.printStackTrace();
        }

        st.close();
        con.close();
    }

    private static User getUser1Data(){
        String bio = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.\n" +
                "          چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. کتابهای زیادی در شصت و سه درصد گذشته، حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.\n" +
                " ";
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
        return new User("1", "پسر", "عمه زا","خارخاسک", proLink, map, bio);

    }

    private static User getUser2Data(){
        String bio = "در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.";
        Skill s = new  Skill("Linux", 5);
        Skill s1 = new Skill("SEO", 4);
        Skill s2 = new Skill("C", 2);
        Skill s3 = new Skill("Java", 3);
        HashMap<String, Skill> map = new HashMap<String, Skill>();
        map.put("Linux", s);
        map.put("SEO", s1);
        map.put("C", s2);
        map.put("Java", s3);
        return new User("2", "فامیل", "دور","پدر نمونه", "http://cdn-tehran.wisgoon.com/dlir-s3/10531477474534427630.jpeg",map, bio);
    }

    private static User getUser3Data(){
        System.out.println("adding user");
        String bio = "کلاه\u200Cقرمزی نام شخصیت عروسکی ایرانی است که توسط ایرج طهماسب و حمید جِبِلّی خلق و توسط مرضیه محبوب طراحی و ساخته شده\u200Cاست. فیلم\u200Cهای ساخته\u200Cشده براساس این شخصیت از پرفروش\u200Cترین فیلم\u200Cهای تاریخ سینمای ایران است.[۱] کلاه\u200Cقرمزی با بیش از دو دهه فعالیت در عرصهٔ سینما و تلویزیون، از یک عروسک به پدیده\u200Cای فرهنگی تبدیل شد که توانست مخاطبان بسیاری در بین نسل\u200Cهای گوناگون به\u200Cویژه متولدین دههٔ ۱۳۶۰ بیابد.";
        Skill s = new  Skill("HTML", 5);
        Skill s1 = new Skill("C", 6);
        Skill s2 = new Skill("Photoshop", 10);

        HashMap<String, Skill> map = new HashMap<String, Skill>();
        map.put("HTML", s);
        map.put("C", s1);
        map.put("Photoshop", s2);

        return new User("3", "پسر", "خاله","نونوا", "http://www.soonami.ir/wp-content/uploads/49384726_617182022059223_729630059174640160_n.jpg",map, bio);

    }

    private static void fillTable(Connection con) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        users.add(getUser1Data());
        users.add(getUser2Data());
        users.add(getUser3Data());
        System.out.println("adding users...");
        ArrayList<String> attrs = getAttrs();
        for (int i = 0; i < users.size(); i++){
            HashMap<String, String> userData = new HashMap<>();

            userData.put("username", users.get(i).getUsername());
            userData.put("firstName", users.get(i).getFirstName());
            userData.put("lastName", users.get(i).getLastName());
            userData.put("jobTitle", users.get(i).getJobTitle());
            userData.put("profilePictureURL", users.get(i).getProfilePictureURL());
            userData.put("bio", users.get(i).getBio());
            addToTable(con, "user", userData, attrs);

            ArrayList<String> attr = UserSkillMapper.createAttribute();
            User u = users.get(i);
            HashMap<String, Skill> skills = u.getSkills();
            for (Map.Entry<String, Skill> entry : skills.entrySet()) {
                Skill s = entry.getValue();
                ArrayList<String> values = new ArrayList<>();
                values.add(u.getUsername());
                values.add(entry.getKey());
                values.add(Integer.toString(s.getPoint()));
                UserSkillMapper.addToTable("userSkills",attr, values);
                }
            }
        }

    private static ArrayList<String> getAttrs(){
        ArrayList<String> attrs = new ArrayList<String>(
                Arrays.asList("username",
                "firstName",
                "lastName",
                "jobTitle",
                "profilePictureURL",
                "bio"));
        return attrs;
    }

    public static void addToTable(Connection con,String tableName, HashMap<String, String> userData, ArrayList<String> attrs) throws SQLException {
        String sqlCommand = insertCommand(tableName, attrs);
        PreparedStatement prp = con.prepareStatement(sqlCommand);
        for(int j = 0; j < attrs.size(); j++) {
            prp.setString(j + 1, userData.get(attrs.get(j)));
        }
        prp.executeUpdate();
        prp.close();
    }


    private static String insertCommand(String tableName, ArrayList<String> attributes){
        String sqlCommand = "INSERT OR IGNORE INTO " + tableName + "(";
        for(String attr: attributes)
            sqlCommand += attr + ",";
        sqlCommand = sqlCommand.substring(0, sqlCommand.length()-1);
        sqlCommand += ") VALUES(";
        for(int i = 0; i < attributes.size(); i++)
            sqlCommand += "?,";
        sqlCommand = sqlCommand.substring(0, sqlCommand.length()-1);
        sqlCommand += ");";
        return sqlCommand;
    }

    @Override
    protected User convertResultSetToDomainModel(ResultSet rs) throws SQLException{
        UserSkillMapper u = new UserSkillMapper();
        return new User(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        u.findUserSkillsById(rs.getString(1)),
                        rs.getString(6)
        );
    }

    @Override
    protected String getFindStatement() {
        return "SELECT " + COLUMNS +
                " FROM user" +
                " WHERE username = ?";
    }

}
