package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Date;
import java.util.List;
import model.*;

public class UpdateMarkServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        MarkDAO mD = new MarkDAO();
        SubjectDAO sD = new SubjectDAO();
        StudentDAO stD = new StudentDAO();
        String sid, stid, xGpa;
        float gpa = 0;
        sid = request.getParameter("sid");
        stid = request.getParameter("stid");
        xGpa = request.getParameter("gpa");
        boolean isOk = true;
        if (xGpa == null || xGpa.trim().isEmpty()) {
            gpa = 0;
        } else {
            gpa = Float.valueOf(xGpa);
            if (gpa < 0 || gpa > 10) {
                gpa = 0;
            }
        }
        Mark x = new Mark(sD.getSubject(sid), stD.getStudent(stid), gpa);
        if (!isOk) {
            request.setAttribute("x", x);
            request.getRequestDispatcher("updateMark.jsp").forward(request, response);
            return;
        }
        mD.update(sid, stid, gpa);
        request.getRequestDispatcher("listMark.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        MarkDAO mD = new MarkDAO();
        SubjectDAO sD = new SubjectDAO();
        StudentDAO stD = new StudentDAO();
        String sid, stid, xGpa;
        float gpa = 0;
        sid = request.getParameter("sid");
        stid = request.getParameter("stid");
        xGpa = request.getParameter("gpa");
        boolean isOk = true;
        if (xGpa == null || xGpa.trim().isEmpty()) {
            gpa = 0;
        } else {
            try {
                gpa = Float.valueOf(xGpa);
                if (gpa < 0 || gpa > 10) {
                    gpa = 0;
                }
            } catch (NumberFormatException e) {
                request.getRequestDispatcher("updateMark.jsp").forward(request, response);
                return;
            }
        }
        Mark x = new Mark(sD.getSubject(sid), stD.getStudent(stid), gpa);
        if (!isOk) {
            request.setAttribute("x", x);
            request.getRequestDispatcher("updateMark.jsp").forward(request, response);
            return;
        }
        mD.update(sid, stid, gpa);
        request.getRequestDispatcher("proTeacher.jsp").forward(request, response);
    }
}
