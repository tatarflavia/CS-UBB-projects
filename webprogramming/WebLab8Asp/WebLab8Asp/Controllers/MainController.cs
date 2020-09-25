using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebLab8Asp.DataAbstractionLayer;
using WebLab8Asp.Models;

namespace WebLab8Asp.Controllers
{
    public class MainController : Controller
    {
        // GET: Main
        public ActionResult HomePage()
        {
            if (Session["UserName"] != null)
                return View();
            return RedirectToAction("Login", "Login");
        }



        public string ShowAllLogs()
        {
            if (Session["UserName"] != null) {
                int pageNumber = int.Parse(Request.Params["pageNumb"]);
                DAL dal = new DAL();
                List<Log> loglist = dal.GetAllLogs(pageNumber);
               

                string result = "These are all the logs:<br><table><thead><th>LogID</th><th>Type</th><th>Severity</th><th>LogDate</th><th>UserName</th><th>LogMessage</th></thead>";

                foreach (Log log in loglist)
                {
                    result += "<tr><td>" + log.LogID + "</td><td>" + log.Type + "</td><td>" + log.Severity + "</td><td>" + log.LogDate+ "</td><td>" + log.UserName + "</td><td>" + log.LogMessage+ "</td></tr>";
                }

                result += "</table>";
                return result;
                }
            return "";
        }

        public string ShowYourLogs()
        {
            if (Session["UserName"] != null) {
                int pageNumber = int.Parse(Request.Params["pageNumb"]);
                DAL dal = new DAL();
                List<Log> loglist = dal.GetYourLogs(Session["UserName"].ToString(),pageNumber);
                ViewData["logList"] = loglist;

                string result = "<thead><th>LogID</th><th>Type</th><th>Severity</th><th>LogDate</th><th>UserName</th><th>LogMessage</th></thead>";

                foreach (Log log in loglist)
                {
                    result += "<tr class='rows'><td>" + log.LogID + "</td><td>" + log.Type + "</td><td>" + log.Severity + "</td><td>" + log.LogDate + "</td><td>" + log.UserName + "</td><td>" + log.LogMessage + "</td></tr>";
                }
                
                return result;
            }
            return "";
           
        }

        public string ShowFilter()
        {
            if (Session["UserName"] != null)
            {
                int pageNumber = int.Parse(Request.Params["pageNumb"]);
                DAL dal = new DAL();
                string filterInput= Request.Params["input"];
                List<Log> loglist = dal.GetFilteredLogs(filterInput,pageNumber);
                if (loglist.Count == 0) {
                    return "";
                }
                string result = "<br><table><thead><th>LogID</th><th>Type</th><th>Severity</th><th>LogDate</th><th>UserName</th><th>LogMessage</th></thead>";

                foreach (Log log in loglist)
                {
                    result += "<tr><td>" + log.LogID + "</td><td>" + log.Type + "</td><td>" + log.Severity + "</td><td>" + log.LogDate + "</td><td>" + log.UserName + "</td><td>" + log.LogMessage + "</td></tr>";
                }

                result += "</table>";
                return result;
            }
            return "";

        }

        public ActionResult AddALog()
        {
            if (Session["UserName"] != null)
                return View();
            return RedirectToAction("Login", "Login");
        }

        [HttpPost]
        public ActionResult AddANewLog(Log log) {
            if (ModelState.IsValid) {
                if (Session["UserName"] != null) {
                    DAL dal = new DAL();
                    if (dal.addLog(log.Type, log.Severity, log.LogDate, Session["UserName"].ToString(), log.LogMessage))
                    {
                        return RedirectToAction("HomePage","Main");
                    }
            }
            }
            return View("AddALog");
        }


        public JsonResult getLogByID(int LogID) {
            DAL dal = new DAL();
            Log log = dal.getByID(LogID);
           
            return Json(log, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SeeLogDetails(string LogID,string Type,string Severity,string LogDate,string UserName,string LogMessage) {
            Log log = new Log(int.Parse(LogID), Type, Severity, LogDate, UserName, LogMessage);
            return View("SeeLogDetails", log);
        }


        public ActionResult UpdateLog(Log log) {
            if (ModelState.IsValid)
            {
                if (Session["UserName"] != null)
                {
                    DAL dal = new DAL();
                    if (dal.UpdateLog(log))
                    {
                        return View("SeeLogDetails", log);
                    }
                }
            }
            return View("SeeLogDetails", log);
        }

        public bool DeleteLog() {
            if (Session["UserName"] != null)
            {
                int id = int.Parse(Request.Params["input"]);
                DAL dal = new DAL();
                if (dal.DeleteLog(id))
                {
                    return true;
                }
            }
            return false;
        }

    }
}