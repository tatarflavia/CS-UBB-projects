package Database;

import Domain.User;
import org.postgresql.core.SqlCommand;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private Statement statement;
    private Connection connection;

    public DBManager() {

    }

    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/dbLab7WPUserProfileManagement","postgres","1Ppassword2");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error at connection:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            connection.close();
        }
        catch (SQLException e)
        {System.out.println(e.getMessage());}
    }

    public User authenticateUser(String email,String password){
        connect();
        User user=null;
        try{
            ResultSet resultSet=statement.executeQuery("select * from Users where email='"+email+"' and password='"+password+"'");
            if(resultSet.next())
                user=new User(resultSet.getInt("userID"),resultSet.getString("name"),resultSet.getString("email"),
                        resultSet.getString("picture"),resultSet.getInt("age"),resultSet.getString("homeTown"),resultSet.getString("password"));
            resultSet.close();
        }

        catch (SQLException e)
        {
            System.out.println("Error at authentication:" + e.getMessage());
        }
        disconnect();
        return user;
    }

    public User addUser(String name,String email,String picture,int age,String homeTown,String password)
    {
        connect();
        User user=null;
        try{
            statement.executeUpdate("INSERT INTO users(" +
                    "name, email, picture, age, hometown, password)" +
                    "VALUES ('"+name+"', '"+email+"', '"+picture+"', "+age+",'"+homeTown+"' , '"+password+"');");
            ResultSet resultSet=statement.executeQuery("SELECT * FROM users ORDER BY userID DESC LIMIT 1");
            if(resultSet.next())
                user=new User(resultSet.getInt("userID"),resultSet.getString("name"),resultSet.getString("email"),
                        resultSet.getString("picture"),resultSet.getInt("age"),resultSet.getString("homeTown"),resultSet.getString("password"));
            resultSet.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error at registration:" + e.getMessage());
        }
        disconnect();
        return user;
    }

    public ArrayList<User> getAllUsers(){
        connect();
        ArrayList<User> users=new ArrayList<>();
        try{
            ResultSet resultSet=statement.executeQuery("select * from users");
            while(resultSet.next())
            {
                User user=new User(resultSet.getInt("userID"),resultSet.getString("name"),resultSet.getString("email"),
                        resultSet.getString("picture"),resultSet.getInt("age"),resultSet.getString("homeTown"),resultSet.getString("password"));
                users.add(user);
            }
            resultSet.close();
        }
        catch(SQLException e){
            System.out.println("Error at fetchingAll:" + e.getMessage());
        }

        disconnect();
        return users;
    }

    public ArrayList<User> getFilteredUsers(String input){
        connect();
        ArrayList<User> filteredUsers=new ArrayList<>();
        try{
            ResultSet resultSet=statement.executeQuery("select * from users WHERE name::text LIKE '"+input+"%%' OR email::text LIKE '"+input+"%%' OR age::text LIKE '"+input+"%%' OR homeTown::text LIKE '"+input+"%%' ");
            while(resultSet.next())
            {
                User user=new User(resultSet.getInt("userID"),resultSet.getString("name"),resultSet.getString("email"),
                        resultSet.getString("picture"),resultSet.getInt("age"),resultSet.getString("homeTown"),resultSet.getString("password"));
                filteredUsers.add(user);
            }
            resultSet.close();
        }
        catch(SQLException e){
            System.out.println("Error at fetchingFiltered:" + e.getMessage());
        }
        disconnect();
        return filteredUsers;
    }


    public Boolean updateUser(int userID, String name, String email, String picture, int age, String homeTown,String password){
        //it gets directly what is needs to be changed
        connect();
        int result=0;
        try{
            result= statement.executeUpdate(String.format("UPDATE users SET name='%s', email='%s', picture='%s', age=%s, homeTown='%s', password='%s' WHERE userID=%s;",name,email,picture,age,homeTown,password,userID));
            statement.close();
        }
        catch (SQLException e){
            System.out.println("Error at updatingProfile:" + e.getMessage());
            return false;
        }
        disconnect();
        return result > 0;
    }

    public Boolean checkIfUniqueEmail(String email){
        connect();
        int result=0;
        try{
            ResultSet resultSet=statement.executeQuery("SELECT COUNT(*) AS total FROM users WHERE email='"+email+"'");
            if(resultSet.next())
                result=resultSet.getInt("total");
            resultSet.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error at counting in sql:"+e.getMessage());
            return false;
        }
        disconnect();
        return result <= 0;
    }



}
