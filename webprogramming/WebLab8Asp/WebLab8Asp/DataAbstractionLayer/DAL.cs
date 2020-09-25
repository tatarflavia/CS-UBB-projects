using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WebLab8Asp.Models;

namespace WebLab8Asp.DataAbstractionLayer
{
    public class DAL
    {
        public int numberOfRecordsPerPage=4;
        public List<Log> GetAllLogs(int pageNumber)
        {
            MySqlConnection conn;
            string myConnectionString;
            int offset = (pageNumber - 1) * numberOfRecordsPerPage;

            myConnectionString = "server=localhost;uid=root;pwd=;database=loggingreport;";
            List<Log> loglist = new List<Log>();

            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from logs LIMIT "+offset+", "+numberOfRecordsPerPage;
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Log log = new Log(myreader.GetInt32("LogID"),myreader.GetString("Type"), myreader.GetString("Severity"),
                        Convert.ToDateTime(myreader["LogDate"]).ToString("yyyy-MM-dd"), myreader.GetString("UserName"), myreader.GetString("LogMessage"));
 
                    loglist.Add(log);
                }
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return loglist;
        }

        public List<Log> GetYourLogs(string UserName,int pageNumber)
        {
            MySqlConnection conn;
            string myConnectionString;
            int offset = (pageNumber - 1) * numberOfRecordsPerPage;
            myConnectionString = "server=localhost;uid=root;pwd=;database=loggingreport;";
            List<Log> loglist = new List<Log>();

            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from logs where UserName='"+UserName+ "'  LIMIT " + offset + ", " + numberOfRecordsPerPage;
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Log log = new Log(myreader.GetInt32("LogID"), myreader.GetString("Type"), myreader.GetString("Severity"),
                        Convert.ToDateTime(myreader["LogDate"]).ToString("yyyy-MM-dd"), myreader.GetString("UserName"), myreader.GetString("LogMessage"));

                    loglist.Add(log);
                }
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return loglist;
        }


        public List<Log> GetFilteredLogs(string filterInput,int pageNumber)
        {
            MySqlConnection conn;
            string myConnectionString;

            int offset = (pageNumber - 1) * numberOfRecordsPerPage;
            myConnectionString = "server=localhost;uid=root;pwd=;database=loggingreport;";
            List<Log> loglist = new List<Log>();

            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "SELECT * FROM logs WHERE Type LIKE '"+filterInput+"%' OR Severity LIKE '"+filterInput+ "%'   LIMIT " + offset + ", " + numberOfRecordsPerPage;
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Log log = new Log(myreader.GetInt32("LogID"), myreader.GetString("Type"), myreader.GetString("Severity"),
                        Convert.ToDateTime(myreader["LogDate"]).ToString("yyyy-MM-dd"), myreader.GetString("UserName"), myreader.GetString("LogMessage"));

                    loglist.Add(log);
                }
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return loglist;
        }

        public bool addLog(string Type,string Severity,string LogDate,string UserName,string LogMessage)
        {
            MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=loggingreport;";
            
            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "INSERT INTO logs(Type,Severity,LogDate,UserName,LogMessage) VALUES('" + Type + "', '" + Severity + "','" + LogDate + "','" + UserName + "','" + LogMessage + "');";
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


        public bool UpdateLog(Log log)
        {
            MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=loggingreport;";

            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "UPDATE logs set Type='" + log.Type + "',Severity= '" + log.Severity + "',LogDate='" + log.LogDate + "',LogMessage='" + log.LogMessage + "' where LogID="+log.LogID+";";
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


        public bool DeleteLog(int LogID)
        {
            MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=loggingreport;";

            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "DELETE from logs Where LogID='" +LogID + "';";
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


        public Log getByID(int LogID) {
            MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=loggingreport;";
            Log log = null;
            try
            {
                conn = new MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "SELECT * FROM logs WHERE LogID='" +LogID + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    log = new Log(myreader.GetInt32("LogID"), myreader.GetString("Type"), myreader.GetString("Severity"),
                        Convert.ToDateTime(myreader["LogDate"]).ToString("yyyy-MM-dd"), myreader.GetString("UserName"), myreader.GetString("LogMessage"));
                    return log;
                }
                myreader.Close();
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return log;
        }


    }
}