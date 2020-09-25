package Topic;

import Database.DBManager;
import Domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateProfile extends HttpServlet {

    public UpdateProfile() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user!=null){
            //preparing  the update fields
            int userID=user.getUserID();
            String name=user.getName();
            String email=user.getEmail();
            String picture=user.getPicture();
            int age=user.getAge();
            String homeTown=user.getHomeTown();
            String password=user.getPassword();

            String newname=request.getParameter("name");
            String newemail=request.getParameter("email");
            String newpicture=request.getParameter("picture");
            String newage=request.getParameter("age");
            String newhomeTown=request.getParameter("homeTown");
            String newpassword=request.getParameter("password");

            if(!(newname.equals("")&&newemail.equals("")&&newhomeTown.equals("")&&newpicture.equals("")&&newage.equals("")&&newpassword.equals("")))
            {
                int ok=0;
                if(newname.equals(""))
                    newname=name;
                if(newemail.equals("")){
                    newemail=email;
                    ok=1;
                }
                if(newpicture.equals(""))
                    newpicture=picture;
                if(!(newage.equals("")))
                    age=Integer.parseInt(newage);
                if(newhomeTown.equals(""))
                    newhomeTown=homeTown;
                if(newpassword.equals(""))
                    newpassword=password;

                DBManager dbManager=new DBManager();
                if(dbManager.checkIfUniqueEmail(newemail) || ok==1){
                    boolean result=dbManager.updateUser(userID,newname,newemail,newpicture,age,newhomeTown,newpassword);
                    session.setAttribute("user",new User(userID,newname,newemail,newpicture,age,newhomeTown,newpassword));
                    if (result)
                    {
                        response.getWriter().print("Updating profile was successful!");
                    }
                    else {
                        response.getWriter().print("No information was updated!");
                    }
                }
                else{
                    response.getWriter().print("There is an email like that already!");
                }

            }
            else{
                response.getWriter().print("No information was updated!");
            }
        }
    }
}
