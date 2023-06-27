package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;

public class InsertUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String id, username, password;
        int role;
        StudentDAO sD = new StudentDAO();
        TeacherDAO tD = new TeacherDAO();
        UserDAO uD = new UserDAO();
        id = request.getParameter("id").trim();
        String alert;
        if(id==null || id.trim().isEmpty()){
              alert = "The id must not be empty!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertUser_1.jsp").forward(request, response);
            return;
        }
        if (uD.getUserByID(id) != null) {
            alert = "The id " + id + " already exists!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertUser_1.jsp").forward(request, response);
            return;
        }
        username = request.getParameter("username").trim();
        if (uD.getUserByUsername(username) != null) {
            alert = "The username " + username + " already exists!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertUser_1.jsp").forward(request, response);
            return;
        }
        role = Integer.valueOf(request.getParameter("role"));
        if (role == 2) {
            if (tD.getTeacher(id) == null) {
                alert = "The teacher with id " + id + " does not exists!";
                request.setAttribute("alert", alert);
                request.getRequestDispatcher("insertUser_1").forward(request, response);
                return;
            }
        }
        if (role == 3) {
            if (sD.getStudent(id) == null) {
                alert = "The student with id " + id + " does not exists!";
                request.setAttribute("alert", alert);
                request.getRequestDispatcher("insertUser_1.jsp").forward(request, response);
                return;
            }
        }
        if (username == null || username.trim().length() == 0) {
            alert = "The username must not be empty!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertUser_1.jsp").forward(request, response);
            return;
        }

        password = request.getParameter("password");
        if (password == null || password.trim().length() == 0) {
            alert = "The password must not be empty!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertUser_1.jsp").forward(request, response);
            return;
        }
        User u = new User(id, username, password, role);
        uD.insert(u);
        request.getRequestDispatcher("listUser.jsp").forward(request, response);
    }
}
