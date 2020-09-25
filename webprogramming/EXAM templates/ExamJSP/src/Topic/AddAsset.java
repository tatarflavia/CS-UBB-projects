package Topic;

import Database.DBManager;
import Domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddAsset extends HttpServlet {
    public AddAsset() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user!=null){
            int userId=user.getId();

            String name=request.getParameter("name");
            String description=request.getParameter("description");
            String value=request.getParameter("value");

            if(!(name.equals("")&&description.equals("")&&value.equals("")))
            {
                int intVal=0;
                if(!(value.equals("")))
                    intVal=Integer.parseInt(value);

                DBManager dbManager=new DBManager();
                boolean result=dbManager.addAsset(userId,name,description,intVal);
                if (result)
                {
                    response.getWriter().print("New Asset was added!");
                }
                else {
                    response.getWriter().print("No asset was added!");
                }
            }
            else{
                response.getWriter().print("No information was added!");
            }
        }
    }
}
