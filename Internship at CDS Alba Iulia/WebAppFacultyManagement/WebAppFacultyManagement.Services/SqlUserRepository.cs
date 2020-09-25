using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public class SqlUserRepository : IUserRepository
    {
        public AppDbContext Context { get; }

        public SqlUserRepository(AppDbContext context)
        {
            Context = context;
        }


        public User Add(User newUser)
        {
            if (newUser == null)
            {
                return newUser;
            }
            var Username = new SqlParameter("@Username", newUser.Username);
            var Password = new SqlParameter("@Password", newUser.Password);
            if (newUser.HasAdminRights)
            {
                 var HasAdminRights = new SqlParameter("@HasAdminRights", 1);
                Context.Database.ExecuteSqlCommand("INSERT INTO Users (Username, Password, HasAdminRights) VALUES(@Username,@Password,@HasAdminRights)", Username, Password, HasAdminRights);
            }
            else {
                Context.Database.ExecuteSqlCommand("INSERT INTO Users (Username, Password, HasAdminRights) VALUES(@Username,@Password,0)", Username, Password);
            }
            return newUser;
        }

        public User GetUser(int UserID)
        {
            return Context.Users.Find(UserID);
        }

        public User GetUserByCredentials(string Username, string Password)
        {
            foreach (var user in Context.Users) {
                if (user.Username == Username && user.Password == Password) {
                    return user;
                }
            }
            return null;
        }

        public bool IsUsernameUsedAlready(string Username)
        {
            foreach (var user in Context.Users) {
                if (user.Username == Username)
                    return true;
            }
            return false;
        }
    }
}
