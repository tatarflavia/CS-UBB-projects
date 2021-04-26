package Database;

import Domain.Asset;
import Domain.User;

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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/exam","postgres","1Ppassword2");
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

    public User authenticateUser(String username, String password){
        connect();
        User user=null;
        try{
            ResultSet resultSet=statement.executeQuery("select * from users where username='"+username+"' and password='"+password+"'");
            if(resultSet.next())
                user=new User(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getString("password"));
            resultSet.close();
        }

        catch (SQLException e)
        {
            System.out.println("Error at authentication:" + e.getMessage());
        }
        disconnect();
        return user;
    }

    public ArrayList<Asset> getAllAssets(int userId) {
        connect();
        ArrayList<Asset> assets=new ArrayList<>();
        try{

            ResultSet resultSet=statement.executeQuery("select * from assets where userid='"+userId+"'");
            while(resultSet.next())
            {
                Asset asset=new Asset(resultSet.getInt("id"),userId,resultSet.getString("name"),resultSet.getString("description"),resultSet.getInt("value"));
                assets.add(asset);
            }
            resultSet.close();
        }
        catch(SQLException e){
            System.out.println("Error at fetchingAll:" + e.getMessage());
        }

        disconnect();
        return assets;
    }

    public Boolean addAsset(int userId, String name, String description, int value)
    {
        connect();
        Asset asset=null;
        try{
            statement.executeUpdate("INSERT INTO assets(" +
                    "userid, name, description, value)" +
                    "VALUES ('"+userId+"', '"+name+"', '"+description+"', '"+value+"');");
            ResultSet resultSet=statement.executeQuery("SELECT * FROM assets ORDER BY id DESC LIMIT 1");
            if(resultSet.next())
                asset=new Asset(resultSet.getInt("id"),userId,resultSet.getString("name"),resultSet.getString("description"),resultSet.getInt("value")) ;
            resultSet.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error at adding asset:" + e.getMessage());
            return false;
        }
        disconnect();
        return true;
    }

    public Boolean updateAsset(int id, String name, String description, int value){
        //it gets directly what is needs to be changed
        connect();
        int result=0;
        try{
            result= statement.executeUpdate(String.format("UPDATE assets SET name='%s', description='%s',value=%s WHERE id=%s;",name,description,value,id));
            statement.close();
        }
        catch (SQLException e){
            System.out.println("Error at updating:" + e.getMessage());
            return false;
        }
        disconnect();
        return result > 0;
    }


    public ArrayList<Asset> getFilteredAssets(String input){
        connect();
        ArrayList<Asset> filteredAssets=new ArrayList<>();
        try{
            ResultSet resultSet=statement.executeQuery("select * from assets WHERE description::text LIKE '%%"+input+"%%'");
            while(resultSet.next())
            {
                Asset asset=new Asset(resultSet.getInt("id"),resultSet.getInt("userid"),resultSet.getString("name"),resultSet.getString("description"),resultSet.getInt("value"));
                filteredAssets.add(asset);
            }
            resultSet.close();
        }
        catch(SQLException e){
            System.out.println("Error at fetchingFilteredAssets:" + e.getMessage());
        }
        disconnect();
        return filteredAssets;
    }
//
//    public ArrayList<User> getAllUsers(){
//        connect();
//        ArrayList<User> users=new ArrayList<>();
//        try{
//            ResultSet resultSet=statement.executeQuery("select * from users");
//            while(resultSet.next())
//            {
//                User user=new User(resultSet.getInt("userID"),resultSet.getString("name"),resultSet.getString("email"),
//                        resultSet.getString("picture"),resultSet.getInt("age"),resultSet.getString("homeTown"),resultSet.getString("password"));
//                users.add(user);
//            }
//            resultSet.close();
//        }
//        catch(SQLException e){
//            System.out.println("Error at fetchingAll:" + e.getMessage());
//        }
//
//        disconnect();
//        return users;
//    }


}
