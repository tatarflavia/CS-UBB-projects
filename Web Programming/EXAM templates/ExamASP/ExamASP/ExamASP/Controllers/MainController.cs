using ExamASP.DataAbstractionLayer;
using ExamASP.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ExamASP.Controllers
{
    public class MainController : Controller
    {
        
        public ActionResult HomePage()
        {
            if (Session["UserName"] != null)
                return View();
            return RedirectToAction("Login", "Login");
        }

        public string ShowYour()
        {
            if (Session["username"] != null)
            {
                
                DAL dal = new DAL();
                List<Asset> assetlist = dal.GetYour(int.Parse(Session["id"].ToString()));
                
                string result = "<thead><th>Name</th><th>Description</th><th>Value</th></thead>";

                foreach (Asset asset in assetlist)
                {
                    result += "<tr class='rows'><td>" + asset.Name + "</td><td>" + asset.Description + "</td><td>" + asset.Value + "</td></tr>";
                }

                return result;
            }
            return "";

        }

        public string ShowFilter()
        {
            if (Session["username"] != null)
            {
                
                DAL dal = new DAL();
                string filterInput = Request.Params["input"];
                List<Asset> assetlist = dal.GetFiltered(filterInput);
                if (assetlist.Count == 0)
                {
                    return "";
                }
                string result = "<thead><th>Name</th><th>Description</th><th>Value</th></thead>";

                foreach (Asset asset in assetlist)
                {
                    result += "<tr class='rows'><td>" + asset.Name + "</td><td>" + asset.Description + "</td><td>" + asset.Value + "</td></tr>";
                }

                result += "</table>";
                return result;
            }
            return "";

        }

        public string Add() {

    
            if (Session["username"] != null)
            {
                DAL dal = new DAL();
                string name = Request.Params["name"];
                string description = Request.Params["description"];
                string value = Request.Params["value"];

                if (!(name=="" && description=="" && value==""))
                {
                    int intVal = 0;
                    if (!(value == ""))
                        intVal = int.Parse(value);
  
                    
                    bool result = dal.Add(int.Parse(Session["id"].ToString()), name, description, intVal);
                    if (result)
                    {
                        return "New Asset was added!";
                    }
                    else
                    {
                        return "No asset was added!";
                    }
                }
                else
                {
                    return "No information was added!";
                }
                
                
            }
            return "";
        }



        public string Update()
        {
            if (Session["username"] != null)
            {
                DAL dal = new DAL();
                string id = Request.Params["id"];
                string name = Request.Params["name"];
                string description = Request.Params["description"];
                string value = Request.Params["value"];

                if (id == "")
                {
                    return "Update not possible without id!";
                }
                else {
                    int intVal = 0;
                    if (!(value == ""))
                        intVal = int.Parse(value);
                    int intID = int.Parse(id);
                    bool result = dal.Update(intID, name, description, intVal);
                    if (result)
                    {
                        return "Update successfull!";
                    }
                    else
                    {
                        return "No asset was updated!";
                    }

                }
            }
            return "";
        }


    }
}