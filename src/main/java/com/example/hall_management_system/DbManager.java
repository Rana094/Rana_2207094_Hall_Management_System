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
                logger.info("connected to Database");
                createTable();
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
                statement.setInt(8,roll);
                statement.setString(9,password);
                statement.executeUpdate();
                logger.info("Student updated");

            }
    }

    public List<Student> readStudents()
    {
        getConnection();
        String query = "select * from studentsrecords";
        List<Student> students=new ArrayList<>();
        try(PreparedStatement statement=connection.prepareStatement(query))
        {
            ResultSet rs =statement.executeQuery();
            while(rs.next())
            {
                int roll=rs.getInt("roll");
                String name=rs.getString("name");
                byte[] image = rs.getBytes("image");
                students.add(new Student(roll,name,image));
            }
        }
        catch (SQLException e)
        {
            logger.info(e.toString());
        }
        return students;
    }
}
