package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;


public class SortSubjectServlet extends HttpServlet {

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
        SubjectDAO sD = new SubjectDAO();
        sortType = request.getParameter("sortType");
        colName = request.getParameter("colName");
        List<Subject> subjects = sD.getSubjects(colName, sortType);
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("listSubject.jsp").forward(request, response);
    }
}

