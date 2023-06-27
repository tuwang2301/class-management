package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import model.*;

public class DeleteTeacherServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String id = request.getParameter("id");
        SubjectDAO sD = new SubjectDAO();
        Subject found = sD.getSubjectByTeacher(id);
        if(found != null){
            request.getRequestDispatcher("deleteTeacher_1.jsp").forward(request, response);
        }
        TeacherDAO tD = new TeacherDAO();
        tD.delete(id);
        UserDAO uD = new UserDAO();
        if(uD.getUserByID(id)!=null){
            uD.deleteByID(id);
        }
        request.getRequestDispatcher("listTeacher.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
    }
}
