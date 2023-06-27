package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Date;
import java.util.List;
import model.*;

public class UpdateUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String xRollno = request.getParameter("rollno");
        StudentDAO u = new StudentDAO();
        Student x = u.getStudent(xRollno);
        if(x==null) {
           pr.println("<h2> A student is not found</h2>");
           request.getRequestDispatcher("update.html").include(request, response);
        }
        else {
           request.setAttribute("x", x);
           request.getRequestDispatcher("update.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        UserDAO uD = new UserDAO();
        String id, username, password;
        int role;
        id = request.getParameter("id");
        username = request.getParameter("username");
        password = request.getParameter("password");
        boolean isOk = true;
        if(id==null || id.equals("")) {
           isOk = false;
        }
        if(username==null || username.equals("")) {
           isOk = false;
        }
        if(password==null || password.trim().length()==0) {
           isOk = false;
        }
        role = Integer.valueOf(request.getParameter("role"));
        User x = new User(id, username, password, role);
        if(!isOk) {
          request.setAttribute("x", x);
          request.getRequestDispatcher("updateUser.jsp").forward(request, response);
          return;
        }
        uD.update(id,x);
        request.getRequestDispatcher("listUser.jsp").forward(request, response);
  }
}
