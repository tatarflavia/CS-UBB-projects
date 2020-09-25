using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace WebAppFacultyManagement.Pages
{
    public class ContactModel : PageModel
    {
        public string Message { get; set; }
        public bool HasAdminRights { get; private set; }

        public void OnGet()
        {
            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }
            Message = "Your contact page.";
        }
    }
}
