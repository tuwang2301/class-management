package controller;

import model.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     response.setContentType("text/html;charset=UTF-8");
     PrintWriter pr = response.getWriter();
     String xName = request.getParameter("username");
     String xPass = request.getParameter("password");
     User x = null;
     UserDAO u = new UserDAO();
     x = u.getUser(xName,xPass);
     if(x==null) { 
          request.getRequestDispatcher("loginFail.html").include(request, response);  
       }
       else {
          request.getSession().setAttribute("currUser",x);
          request.getRequestDispatcher("listStudent.jsp").forward(request, response);  
         }
  }
}
