using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace WebAppFacultyManagement.Pages
{
    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public class ErrorModel : PageModel
    {
        public string RequestId { get; set; }

        public bool ShowRequestId => !string.IsNullOrEmpty(RequestId);

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
            RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier;
        }
    }
}
