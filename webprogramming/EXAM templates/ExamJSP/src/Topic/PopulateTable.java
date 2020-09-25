package Topic;

import Database.DBManager;
import Domain.Asset;
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

public class PopulateTable extends HttpServlet {
    public PopulateTable() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user!=null){
            DBManager dbManager=new DBManager();
            ArrayList<Asset> assets=new ArrayList<>();
            assets=dbManager.getAllAssets(user.getId());
            JSONArray jsonArray=null;
            try{
                jsonArray=convertEntriesToJsonArray(assets);
            }
            catch (JSONException j){
                System.out.println("Error at converting into jsonArray"+j.getMessage());
            }
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        }

    }

    private JSONArray convertEntriesToJsonArray(ArrayList<Asset> entries) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Asset  a: entries) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", a.getId());
            jsonObject.put("name", a.getName());
            jsonObject.put("description", a.getDescription());
            jsonObject.put("userid", a.getUserId());
            jsonObject.put("value", a.getValue());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
