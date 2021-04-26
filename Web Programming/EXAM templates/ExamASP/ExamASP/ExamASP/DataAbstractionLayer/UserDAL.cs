using System;
using System.Collections.Generic;
using MySql.Data.MySqlClient;
using System.Linq;
using System.Web;
using ExamASP.Models;

namespace ExamASP.DataAbstractionLayer
{
    public class UserDAL
    {
        public User GetUser(string Username,string Password)
        {
            MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=exam;";
            User user = null;
            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from users where username='"+Username+"' and password='"+Password+"'";
                MySqlDataReader myreader = cmd.ExecuteReader();
                
                while (myreader.Read())
                {
                    user = new User(myreader.GetInt32("id"), myreader.GetString("username"), myreader.GetString("password"));
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