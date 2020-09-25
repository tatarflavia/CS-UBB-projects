using System;
using System.Collections.Generic;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public interface IUserRepository
    {
        User Add(User newUser);
        User GetUser(int UserID);
        User GetUserByCredentials(string Username, string Password);
        bool IsUsernameUsedAlready(string Username);
    }
}
