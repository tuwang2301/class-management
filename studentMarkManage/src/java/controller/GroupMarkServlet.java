package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;

public class GroupMarkServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        MarkDAO mD = new MarkDAO();
        List<Mark> marks = mD.getMarks();
        request.setAttribute("marks", marks);
        request.getRequestDispatcher("listMark.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id,colName;
        id = request.getParameter("id");
        colName = request.getParameter("colName");
        MarkDAO mD = new MarkDAO();        
        List<Mark> marks = null;
        if(colName.trim().equalsIgnoreCase("sid")){
            marks = mD.getMarksBySID(id);
        }
        if(colName.trim().equalsIgnoreCase("stid")){
            marks = mD.getMarksByStID(id);
        }
        request.setAttribute("marks", marks);
        request.getRequestDispatcher("listMark.jsp").forward(request, response);
    }
}

