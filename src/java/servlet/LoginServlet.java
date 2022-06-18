package servlet;

import java.io.IOException;
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

        //check if user is logging out
        if (logout != null) {
            session.invalidate();
            request.setAttribute("loggedOutMessage", "You have successfully logged Out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        //check if user is returning to login page while logged in
        if (session.getAttribute("username") != null) {
            response.sendRedirect("home"); //redirect user to home page
            return; //don't execute any more lines
        }
        //load login jsp
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); //retrieve and sotre the session 

        //retrieve and store the entered username and password
        String userName = request.getParameter("user_name");
        String passWord = request.getParameter("pass_word");

        //check if username or password fields are null or empty
        if (userName == null || userName.equals("") || passWord == null || passWord.equals("")) {
            //send an error message and load the login jsp
            request.setAttribute("emptyFields", "You must enter a username and a password");
            request.setAttribute("preloadusername", userName);
            request.setAttribute("preloapassword", passWord);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        
        //if fields are not empty or null
        } else {

            AccountService service = new AccountService();

            User userObject = service.login(userName, passWord); //store return object from the login() method

            //check return object, whether null or a User object 
            if (userObject != null) {
                session.setAttribute("username", userName);
                response.sendRedirect("home");
                return;

                //if credentials are invalid
            } else {
                //Send user error message and load the login jsp with their previous info
                request.setAttribute("invalidLogin", "Credentials Invalid");
                request.setAttribute("preloadusername", userName);
                request.setAttribute("preloapassword", passWord);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }

        }

    }

}
