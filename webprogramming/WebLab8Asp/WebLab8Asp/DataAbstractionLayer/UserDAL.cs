using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WebLab8Asp.Models;

namespace WebLab8Asp.DataAbstractionLayer
{
    public class UserDAL
    {
        public User GetUser(string UserName,string Password)
        {
            MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=loggingreport;";
            User user = null;
            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from users where UserName='"+UserName+"' and Password='"+Password+"'";
                MySqlDataReader myreader = cmd.ExecuteReader();
                
                while (myreader.Read())
                {
                    user = new User(myreader.GetInt32("UserID"), myreader.GetString("UserName"), myreader.GetString("Password"));
                    myreader.Close();
                    return user;
                }
               
                
                
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return user;
        }
    }
}