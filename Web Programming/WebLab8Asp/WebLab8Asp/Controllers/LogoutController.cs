using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace WebLab8Asp.Controllers
{
    public class LogoutController : Controller
    {
        
        public ActionResult LogOut()
        {
            Session.Abandon();
            return RedirectToAction("Login", "Login");
        }


    }
}