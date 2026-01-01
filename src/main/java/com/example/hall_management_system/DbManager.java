package com.example.hall_management_system;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.logging.Logger;

public class DbManager {
    private Connection connection;
    private Logger logger= Logger.getLogger(this.getClass().getName());
    public void getConnection()
    {
        try
        {
            if(connection ==null || connection.isClosed())
            {
                connection= DriverManager.getConnection("jdbc:sqlite:myhall.db");

                Statement statement=connection.createStatement();
                statement.execute("PRAGMA foreign_keys =ON");
                logger.info("connected to Database");
                createTable();
                createStudentStatusTable();
                createNoticeTable();

            }
        }
        catch (SQLException e)
        {
            logger.info(e.toString());

        }
    }

    private void createTable()
    {
        getConnection();
        String query ="create table if not exists studentsrecords(roll integer not null primary key, name text not null,email text not null,address text not null,dept text not null,cgpa text not null,birthdate text not null,image BLOB,password TEXT NOT NULL) ";
        try (PreparedStatement statement=connection.prepareStatement(query))
        {
            statement.executeUpdate();
            logger.info("Table created");

        }
        catch (SQLException e)
        {
            logger.info(e.toString());
        }
    }

    private void createStudentStatusTable()
    {
        getConnection();
        String query ="create table if not exists studentstatus(roll integer primary key,status Text not null,removeStatus Text not null,foreign key(roll) references studentsrecords(roll) on delete cascade)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }

    }

    private void createNoticeTable() {
        getConnection();
        String sql = """
        CREATE TABLE IF NOT EXISTS notices (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            message TEXT NOT NULL,
            created_at TEXT NOT NULL
        )
    """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }


    private  void closeconection() throws SQLException
    {
        if(connection!=null || !connection.isClosed())
        {
            connection.close();
        }
    }


    public   void insertStudent(Integer roll,String name,String email,String address,String dept,String cgpa,String birthdate,byte[] image,String password)
    {
        getConnection();
        String query =" insert into studentsrecords(roll,name,email,address,dept,cgpa,birthdate,image,password) values(?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement statement=connection.prepareStatement(query))
        {
            statement.setInt (1,roll);
            statement.setString(2,name);
            statement.setString(3,email);
            statement.setString(4,address);
            statement.setString(5,dept);
            statement.setString(6,cgpa);
            statement.setString(7,birthdate);
            statement.setBytes(8,image);
            statement.setString(9,password);
            statement.executeUpdate();
            logger.info("Student Inserted");
        }
        catch (SQLException e)
        {
            logger.info(e.toString());
        }
    }

    public  void insertStudentStatus(Integer roll,String status,String removeStatus)
    {
        getConnection();
        String sql="insert into studentstatus(roll,status,removeStatus) values (?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(sql))
        {
            ps.setInt(1,roll);
            ps.setString(2,status);
            ps.setString(3,removeStatus);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.info(e.toString());
        }

    }

    public void insertNotice(String title, String message) {
        getConnection();
        String sql = "INSERT INTO notices(title, message, created_at) VALUES(?,?,datetime('now'))";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, message);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }


    public void deleteStudent(Integer roll) throws SQLException
    {
        getConnection();
        String query="delete from studentsrecords where roll=?";
        try(PreparedStatement statement= connection.prepareStatement(query))
        {
            statement.setInt(1,roll);

            statement.executeUpdate();
            logger.info("Student Deleted");

        }
    }

    public  void updateStudent(Integer roll,String name,String email,String address,String dept,String cgpa,String birthdate,byte[] image,String password) throws SQLException
    {
            getConnection();
            String query="update studentsrecords set name=?,email=?,address=?,dept=?,cgpa=?,birthdate=?,image=?,password=?  where roll=?";
            try(PreparedStatement statement= connection.prepareStatement(query))
            {
                statement.setString(1,name);
                statement.setString(2,email);
                statement.setString(3,address);
                statement.setString(4,dept);
                statement.setString(5,cgpa);
                statement.setString(6,birthdate);
                statement.setBytes(7,image);
                statement.setString(8,password);
                statement.setInt(9,roll);
                statement.executeUpdate();
                logger.info("Student updated");
            }
    }

    public void updateStudentStatus(int roll,String status,String removeStatus)
    {
        getConnection();
        String sql="update studentstatus set status=?, removeStatus=? where roll=?";
        try (PreparedStatement ps= connection.prepareStatement(sql))
        {
            ps.setString(1,status);
            ps.setString(2,removeStatus);
            ps.setInt(3,roll);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public List<Student> readStudents()
    {
        getConnection();
//        String query = "select * from studentsrecords s,studentstatus st where st.status=?";
        String query =
                "SELECT s.roll, s.name, s.image, s.dept " +
                        "FROM studentsrecords s " +
                        "JOIN studentstatus st ON s.roll = st.roll " +
                        "WHERE st.status = ?";
        List<Student> students=new ArrayList<>();
        try(PreparedStatement statement=connection.prepareStatement(query))
        {
            statement.setString(1, "true");
            ResultSet rs =statement.executeQuery();
            while(rs.next())
            {
                int roll=rs.getInt("roll");
                String name=rs.getString("name");
                byte[] image = rs.getBytes("image");
                String dept=rs.getString(("dept"));
                students.add(new Student(roll,name,image,dept));
            }
        }
        catch (SQLException e)
        {
            logger.info(e.toString());
        }
        return students;
    }

    public List<Student> readPendingStudents() {
        getConnection();

        String query = "select s.roll, s.name, s.image, s.dept " +
                "from studentsrecords s, studentstatus st " +
                "where s.roll = st.roll and st.status = ?";

        List<Student> students = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "false");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int roll = rs.getInt("roll");
                String name = rs.getString("name");
                byte[] image = rs.getBytes("image");
                String dept = rs.getString("dept");

                students.add(new Student(roll, name, image, dept));
            }

        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return students;
    }

    public List<Notice> readNotices() {
        getConnection();
        List<Notice> notices = new ArrayList<>();
        String sql = "SELECT * FROM notices ORDER BY id DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                notices.add(new Notice(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("message"),
                        rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return notices;
    }



    public Student getStudentByRoll(int roll) {

        getConnection();

        String sql = "SELECT * FROM studentsrecords WHERE roll=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, roll);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("roll"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("dept"),
                        rs.getString("cgpa"),
                        rs.getString("birthdate"),
                        rs.getBytes("image"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStudentStatus(int roll)
    {
        getConnection();
        String sql="select * from studentstatus where roll=?";
        try(PreparedStatement ps= connection.prepareStatement(sql))
        {
            ps.setInt(1,roll);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                return rs.getString("status");
            }
        }
        catch (SQLException e)
        {
            logger.info(e.toString());
        }
        return null;
    }
    public String getStudentRemoveStatus(int roll)
    {
        getConnection();
        String sql="select * from studentstatus where roll=?";
        try(PreparedStatement ps= connection.prepareStatement(sql))
        {
            ps.setInt(1,roll);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                return rs.getString("removeStatus");
            }
        }
        catch (SQLException e)
        {
            logger.info(e.toString());
        }
        return null;
    }

    public boolean rollExists(int roll) {
        getConnection();
        String sql = "SELECT 1 FROM studentsrecords WHERE roll=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean passwordMatches(int roll, String password) {
        getConnection();
        String sql = "SELECT 1 FROM studentsrecords WHERE roll=? AND password=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
