package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;

public class InsertSubjectServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String id, tid, name;
        StudentDAO stD = new StudentDAO();
        TeacherDAO tD = new TeacherDAO();
        SubjectDAO sD = new SubjectDAO();
        MarkDAO mD = new MarkDAO();
        id = request.getParameter("id");
        tid = request.getParameter("tid");
        name = request.getParameter("name");
        String alert;
        if(id == null || id.trim().isEmpty()){
            alert = "The id must not be empty!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertSubject_1.jsp").forward(request, response);
            return;
        }
        Subject x = sD.getSubject(id);
        if (x != null) {
            alert = "The id " + id + " already exists!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertSubject_1.jsp").forward(request, response);
            return;
        }
        if (tid.trim().isEmpty()) {
            alert = "The teacher ID must not be empty!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertSubject_1.jsp").forward(request, response);
            return;
        }
        if (tD.getTeacher(tid) == null) {
            alert = "The teacher ID "+tid+" does not exist!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertSubject_1.jsp").forward(request, response);
            return;
        }
        if (name == null || name.trim().length() == 0) {
            alert = "The name must not be empty!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertSubject_1.jsp").forward(request, response);
            return;
        }
        x = new Subject(id, tD.getTeacher(tid), name);
        sD.insert(x);
        List<String> stIds = stD.getIds();
        for (String stid : stIds) {
            mD.insert(id, stid);
        }
        request.getRequestDispatcher("listSubject.jsp").forward(request, response);
    }
}
