using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using WebAppFacultyManagement.Models;
using WebAppFacultyManagement.Services;

namespace WebAppFacultyManagement.Pages
{
    public class IndexModel : PageModel
    {
        private readonly IUserRepository UserRepository;

        public IndexModel(IUserRepository UserRepository)
        {
            this.UserRepository = UserRepository;
        }

        //for log in
        [BindProperty]
        public User UserForLogIn { get; set; }

        //for register
        [BindProperty]
        public string Username { get; set; }
        [BindProperty]
        public string Password { get; set; }
        [BindProperty]
        public bool HasAdminRights { get; set; }

        public string Message { get; set; }

        public string MessageForRegister { get; set; }

        public void OnGet()
        {
            UserForLogIn = new User();
        }

        public IActionResult OnPost()
        { 

            if (ModelState.IsValid)
            {
                UserForLogIn = UserRepository.GetUserByCredentials(UserForLogIn.Username, UserForLogIn.Password);
                if (UserForLogIn == null)
                {
                    Message = "Invalid username or password.";
                    return Page();
                }
                else {
                    HttpContext.Session.SetString("Username", UserForLogIn.Username);
                    if (UserForLogIn.HasAdminRights)
                    {
                        HttpContext.Session.SetString("HasAdminRights", "yes");
                    }
                    else {
                        HttpContext.Session.SetString("HasAdminRights", "no");
                    }
                    return RedirectToPage("/Welcome");
                }
            }
            Message = "Invalid username or password.";
            return Page();
        }

        public IActionResult OnPostRegister()
        {
            UserForLogIn = new User();
            //first check uniqueness for username
            if (UserRepository.IsUsernameUsedAlready(Username))
                {
                    MessageForRegister = "Invalid username. Already taken.";
                    return Page();
                }
            else {
                if (Username == null || Password == null) {
                    MessageForRegister = "Can't have empty username or password.";
                    return Page();
                }
                User UserForRegister = new User
                {
                    Username = Username,
                    Password = Password,
                    HasAdminRights = HasAdminRights
                };
                UserForRegister = UserRepository.Add(UserForRegister);
                HttpContext.Session.SetString("Username", Username);
                if (HasAdminRights)
                {
                    HttpContext.Session.SetString("HasAdminRights", "yes");
                }
                else
                {
                    HttpContext.Session.SetString("HasAdminRights", "no");
                }
                return RedirectToPage("/Welcome");
            }
        }
    }
}