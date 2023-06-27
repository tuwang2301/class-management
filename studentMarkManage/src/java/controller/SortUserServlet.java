package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;


public class SortUserServlet extends HttpServlet {

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
        UserDAO uD = new UserDAO();
        sortType = request.getParameter("sortType");
        colName = request.getParameter("colName");
        List<User> users = uD.getUsers(colName, sortType);
        request.setAttribute("users", users);
        request.getRequestDispatcher("listUser.jsp").forward(request, response);
    }
}

