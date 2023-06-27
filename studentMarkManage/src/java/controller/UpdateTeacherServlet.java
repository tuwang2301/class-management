package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Date;
import java.util.List;
import model.*;

public class UpdateTeacherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        TeacherDAO tD = new TeacherDAO();
        String id, name, email;
        id = request.getParameter("id");
        name = request.getParameter("name");
        email = request.getParameter("email");
        boolean isOk = true;
        if(id==null || id.equals("")) {
           isOk = false;
        }
        if(name==null || name.trim().length()==0 || name.trim().equals("Invalid name!")) {
           name = "Invalid name!"; 
           isOk = false;
        }
        if(email==null || email.trim().length()==0) {
           isOk = false;
        }
        Teacher x = new Teacher(id, name, email);
        if(!isOk) {
          request.setAttribute("x", x);
          request.getRequestDispatcher("updateTeacher.jsp").forward(request, response);
          return;
        }
        tD.update(id,x);
        request.getRequestDispatcher("listTeacher.jsp").forward(request, response);
  }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        TeacherDAO tD = new TeacherDAO();
        String id, name, email;
        id = request.getParameter("id");
        name = request.getParameter("name");
        email = request.getParameter("email");
        boolean isOk = true;
        if(id==null || id.equals("")) {
           isOk = false;
        }
        if(name==null || name.trim().length()==0 || name.trim().equals("Invalid name!")) {
           name = "Invalid name!"; 
           isOk = false;
        }
        if(email==null || email.trim().length()==0) {
           isOk = false;
        }
        Teacher x = new Teacher(id, name, email);
        if(!isOk) {
          request.setAttribute("x", x);
          request.getRequestDispatcher("updateTeacher.jsp").forward(request, response);
          return;
        }
        tD.update(id,x);
        request.getRequestDispatcher("proTeacher.jsp").forward(request, response);
  }
}
