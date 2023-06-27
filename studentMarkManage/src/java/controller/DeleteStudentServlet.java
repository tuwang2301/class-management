package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import model.*;

public class DeleteStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String id = request.getParameter("id");
        StudentDAO u = new StudentDAO();
        MarkDAO mD = new MarkDAO();
        UserDAO uD = new UserDAO();
        mD.deleteStid(id);
        if(uD.getUserByID(id)!=null) uD.deleteByID(id);
        u.delete(id);
        List<Student> students = u.getStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("listStudent_1.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String id = request.getParameter("id");
        StudentDAO u = new StudentDAO();
        Student x = u.getStudent(id);
        if (x == null) {
            pr.println("<h2> A student is not found </h2>");
            request.getRequestDispatcher("listStudent_1.jsp").forward(request, response);
        } else {
            request.setAttribute("x", x);
            request.getRequestDispatcher("deleteStudent.jsp").forward(request, response);
        }

    }
}
