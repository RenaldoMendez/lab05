
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import service.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String logout = request.getParameter("logout");
        
        if(logout != null){
            session.invalidate();
            request.setAttribute("loggedOutMessage", "You have successfully logged Out");
        }
        
        if(session.getAttribute("userName") != null ){
            response.sendRedirect("home");
            return;
        }
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
       String userName = request.getParameter("user_name");
       String passWord = request.getParameter("pass_word");
       
       //check if username or password is null
       if(userName != null || !userName.equals("") || passWord != null || !passWord.equals("")){
           AccountService service = new AccountService(); 
           
            User userObject = service.login(userName, passWord); //store return object from the login() method
            
            //check return object, whether null or a User object
           if(userObject != null){
               session.setAttribute("userName", userName);
               response.sendRedirect("home");
       
        //if credentials are invalid
       }else{
               //Send user error message and load the login jsp with their previous info
               request.setAttribute("errormessage", "Credentials Invalid");
               request.setAttribute("userName", userName);
               request.setAttribute("passWord", passWord);
               getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
               return;
           }
       }
       
    }


}
