package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;


public class SortTeacherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sortType, colName;
        TeacherDAO tD = new TeacherDAO();
        sortType = request.getParameter("sortType");
        colName = request.getParameter("colName");
        List<Teacher> teachers = tD.getTeachers(colName, sortType);
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("listTeacher.jsp").forward(request, response);
    }
}

