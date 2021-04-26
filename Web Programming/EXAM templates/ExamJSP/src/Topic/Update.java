package Topic;

import Database.DBManager;
import Domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Update extends HttpServlet {
    public Update() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user!=null){
            //preparing  the update fields

            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String description=request.getParameter("description");
            String value=request.getParameter("value");

            if(id.equals(""))
                response.getWriter().print("No info can be changed without the id!");
            if(!(id.equals("")&&name.equals("")&&description.equals("")&&value.equals("")))
            {
                int intValue=0,intID=0;
                if(!(value.equals("")))
                    intValue=Integer.parseInt(value);
                intID=Integer.parseInt(id);

                DBManager dbManager=new DBManager();
                boolean result=dbManager.updateAsset(intID,name,description,intValue);
                if (result)
                {
                    response.getWriter().print("Update was successful!");
                }
                else {
                    response.getWriter().print("No information was updated!");
                }
            }
            else{
                response.getWriter().print("No information was updated!");
            }
        }
    }
}
