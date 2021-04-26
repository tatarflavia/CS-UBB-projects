using ExamASP.Models;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ExamASP.DataAbstractionLayer
{
    public class DAL
    {
        public List<Asset> GetYour(int id)
        {
            MySqlConnection conn;
            string myConnectionString;
            myConnectionString = "server=localhost;uid=root;pwd=;database=exam;";
            List<Asset> assetlist = new List<Asset>();

            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from assets where userid='" + id + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Asset asset = new Asset(myreader.GetInt32("id"), myreader.GetInt32("userid"), myreader.GetString("name"),
                        myreader.GetString("description"), myreader.GetInt32("value"));

                    assetlist.Add(asset);
                }
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return assetlist;
        }

        public List<Asset> GetFiltered(string filterInput)
        {
            MySqlConnection conn;
            string myConnectionString;

            
            myConnectionString = "server=localhost;uid=root;pwd=;database=exam;";
            List<Asset> assetlist = new List<Asset>();

            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "SELECT * FROM assets WHERE description LIKE '%" + filterInput + "%'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Asset asset = new Asset(myreader.GetInt32("id"), myreader.GetInt32("userid"), myreader.GetString("name"),
                                            myreader.GetString("description"), myreader.GetInt32("value"));

                    assetlist.Add(asset);
                }
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return assetlist;
        }

        public bool Add(int userid, string name, string description,int value)
        {
            MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=exam;";

            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "INSERT INTO assets(userid,name,description,value) VALUES('" + userid + "', '" + name + "','" + description + "','" + value + "');";
                int result = cmd.ExecuteNonQuery();
                conn.Close();
                if (result > 0)
                    return true;
                else return false;
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return false;
        }

        public bool Update(int Id, string Name, string Description, int Value)
        {
            MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=exam;";

            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "UPDATE assets set name='" + Name + "',description= '" + Description + "',value='" + Value + "' where id=" + Id + ";";
                int result = cmd.ExecuteNonQuery();
                conn.Close();
                if (result > 0)
                    return true;
                else return false;
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return false;
        }
    }
}