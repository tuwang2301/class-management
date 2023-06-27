package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;


public class SortMarkServlet extends HttpServlet {

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
        MarkDAO mD = new MarkDAO();
        sortType = request.getParameter("sortType");
        colName = request.getParameter("colName");
        List<Mark> marks = mD.getMarks(colName, sortType);
        request.setAttribute("marks", marks);
        request.getRequestDispatcher("listMark.jsp").forward(request, response);
    }
}

