package Topic;


import Database.DBManager;
import Domain.User;
import org.json.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


public class PopulateTable extends HttpServlet {
    public PopulateTable() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user!=null){
            DBManager dbManager=new DBManager();
            ArrayList<User> users=new ArrayList<>();
            users=dbManager.getAllUsers();
            JSONArray jsonArray=null;
            try{
                jsonArray=convertEntriesToJsonArray(users);
            }
            catch (JSONException j){
                System.out.println("Error at converting into jsonArray"+j.getMessage());
            }
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        }

    }

    private JSONArray convertEntriesToJsonArray(ArrayList<User> entries) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (User  u: entries) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userID", u.getUserID());
            jsonObject.put("name", u.getName());
            jsonObject.put("email", u.getEmail());
            jsonObject.put("picture", u.getPicture());
            jsonObject.put("age", u.getAge());
            jsonObject.put("homeTown", u.getHomeTown());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }


}
