package Topic;

import Database.DBManager;
import Domain.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class FilterTable extends HttpServlet {
    public FilterTable() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user!=null){
            String input=request.getParameter("input");
            DBManager dbManager=new DBManager();
            ArrayList<User> filteredUsers=new ArrayList<>();
            filteredUsers=dbManager.getFilteredUsers(input);
            JSONArray jsonArray=null;
            if(filteredUsers.size()!=0){
                try{
                    jsonArray=convertEntriesToJsonArray(filteredUsers);
                }
                catch (JSONException j){
                    System.out.println("Error at converting into jsonArray for filtering:"+j.getMessage());
                }
                response.setContentType("application/json");
                response.getWriter().print(jsonArray);
            }
            else{
                response.setContentType("application/json");
                response.getWriter().print(jsonArray);
            }
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
