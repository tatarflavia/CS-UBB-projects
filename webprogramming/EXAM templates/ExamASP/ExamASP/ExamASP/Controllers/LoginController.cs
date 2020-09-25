using ExamASP.DataAbstractionLayer;
using ExamASP.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ExamASP.Controllers
{
    public class LoginController : Controller
    {
        [HttpGet]
        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Login(User objUser)
        {
            if (ModelState.IsValid)
            {
                string Username = Request.Params["Username"];
                string Password = Request.Params["Password"];
                var userDAL = new UserDAL();

                var user = userDAL.GetUser(Username, Password);
                if (user != null)
                {
                    Session["username"] = user.Username.ToString();
                    Session["id"] = user.Id.ToString();
                    return RedirectToAction("HomePage", "Main");
                }
            }
            return View("Login");
        }
    }
}