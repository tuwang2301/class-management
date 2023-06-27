package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;

public class InsertTeacherServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String id, name, email;
        id = request.getParameter("id");
        TeacherDAO tD = new TeacherDAO();
        String alert;
        if(id==null || id.trim().isEmpty()){
            alert = "The id must not be empty";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertTeacher_1.jsp").forward(request, response);
            return;
        }
        if (tD.getTeacher(id) != null) {
            alert = "The id " + id + " already exists!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertTeacher_1.jsp").forward(request, response);
            return;
        }
        name = request.getParameter("name");
        if (name == null || name.trim().length() == 0) {
            alert = "The name must not be empty";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertTeacher_1.jsp").forward(request, response);
            return;
        }
        email = request.getParameter("email");
        if (email == null || email.trim().length() == 0) {
            alert = "The email must not be empty";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertTeacher_1.jsp").forward(request, response);
            return;
        }
        Teacher x = new Teacher(id, name, email);
        tD.insert(x);
        UserDAO uD = new UserDAO();
        uD.insert(new User(id, id, "123", 2));
        request.getRequestDispatcher("listTeacher.jsp").forward(request, response);
    }
}
