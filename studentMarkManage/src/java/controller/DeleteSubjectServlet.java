package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import model.*;

public class DeleteSubjectServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String id = request.getParameter("id");
        MarkDAO mD = new MarkDAO();
        mD.deleteSid(id);
        SubjectDAO sD = new SubjectDAO();
        sD.delete(id);
        request.getRequestDispatcher("listSubject.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
    }
}
