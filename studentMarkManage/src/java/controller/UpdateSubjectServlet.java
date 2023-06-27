package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Date;
import java.util.List;
import model.*;

public class UpdateSubjectServlet extends HttpServlet {

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
        SubjectDAO sD = new SubjectDAO();
        TeacherDAO tD = new TeacherDAO();
        String id, tid, name;
        id = request.getParameter("id");
        tid = request.getParameter("tid");
        name = request.getParameter("name");
        boolean isOk = true;
        if(id==null || id.equals("")) {
           isOk = false;
        }
        
        if(tid==null || tid.equals("") || tD.getTeacher(tid)==null) {
           isOk = false;
        }
        
        if(name==null || name.trim().length()==0 || name.trim().equals("Invalid name!")) {
           name = "Invalid name!"; 
           isOk = false;
        }
        Subject x = new Subject(id, tD.getTeacher(tid), name);
        if(!isOk) {
          request.setAttribute("x", x);
          request.getRequestDispatcher("updateSubject.jsp").forward(request, response);
          return;
        }
        SubjectDAO u = new SubjectDAO();
        u.update(id,x);
        request.getRequestDispatcher("listSubject.jsp").forward(request, response);
  }
}
