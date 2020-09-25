package Controller;

import Domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController extends HttpServlet {
    public LogoutController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        RequestDispatcher rd = null;
        if(user!=null)
        {
            session.removeAttribute("user");
            rd=request.getRequestDispatcher("/index.html");
            rd.forward(request,response);
            //response.sendRedirect("index.html");
        }
    }
}
