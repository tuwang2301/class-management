package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Date;
import java.util.List;
import model.*;

public class UpdateStudentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        StudentDAO sD = new StudentDAO();
        String id, name, email;
        Date dob; boolean gender;
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
        if(email==null || email.trim().length()==0 || email.trim().equals("Invalid name!")) {
           email = "Invalid email!"; 
           isOk = false;
        }
        dob = Date.valueOf(request.getParameter("dob"));
        if(dob.getYear()>2003){
            isOk = false;
        }
        gender = Boolean.valueOf(request.getParameter("gender"));
        Student x = new Student(id, name, email, dob, gender);
        if(!isOk) {
          request.setAttribute("x", x);
          request.getRequestDispatcher("updateStudent.jsp").forward(request, response);
          return;
        }
        StudentDAO u = new StudentDAO();
        u.update(id,x);
        request.getRequestDispatcher("listStudent_1.jsp").forward(request, response);
  }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        StudentDAO sD = new StudentDAO();
        String id, name, email;
        Date dob; boolean gender;
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
        if(email==null || email.trim().length()==0 || email.trim().equals("Invalid name!")) {
           email = "Invalid email!"; 
           isOk = false;
        }
        dob = Date.valueOf(request.getParameter("dob"));
        if(dob.getYear()>2003){
            isOk = false;
        }
        gender = Boolean.valueOf(request.getParameter("gender"));
        Student x = new Student(id, name, email, dob, gender);
        if(!isOk) {
          request.setAttribute("x", x);
          request.getRequestDispatcher("updateStudent.jsp").forward(request, response);
          return;
        }
        StudentDAO u = new StudentDAO();
        u.update(id,x);
        request.getRequestDispatcher("proStudent.jsp").forward(request, response);
  }
}
