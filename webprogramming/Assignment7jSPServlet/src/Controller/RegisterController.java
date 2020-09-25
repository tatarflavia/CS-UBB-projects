package Controller;

import Domain.User;
import Database.DBManager;
import Domain.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterController  extends HttpServlet {

    public RegisterController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String email = request.getParameter("email");
        String picture = request.getParameter("picture");
        int age=Integer.parseInt(request.getParameter("age"));
        String homeTown = request.getParameter("homeTown");
        String password = request.getParameter("password2");
        RequestDispatcher rd = null;

        DBManager dbmanager = new DBManager();
        if(dbmanager.checkIfUniqueEmail(email) && UserValidator.validateInformation(name,email,picture,age,homeTown,password)){
            User user = dbmanager.addUser(name,email,picture,age,homeTown,password);
            if (user != null) {
                rd = request.getRequestDispatcher("/succes.jsp");
                // Here we should set the "user" attribute on the session like this:
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
            } else {
                rd = request.getRequestDispatcher("/error.jsp");
            }
            rd.forward(request, response);
        }
        else{
            rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }

    }

}
