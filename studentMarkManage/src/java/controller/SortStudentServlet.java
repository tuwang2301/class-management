package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;

public class SortStudentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sortType, colName;
        StudentDAO sD = new StudentDAO();
        sortType = request.getParameter("sortType");
        colName = request.getParameter("colName");
        List<Student> students = sD.getStudents(colName, sortType);
        request.setAttribute("students", students);
        request.getRequestDispatcher("listStudent_1.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sortType, colName;
        StudentDAO sD = new StudentDAO();
        sortType = request.getParameter("sortType");
        colName = request.getParameter("colName");
        List<Student> students = sD.getStudents(colName, sortType);
        request.setAttribute("students", students);
        request.getRequestDispatcher("listStudent.jsp").forward(request, response);
    }
}
