package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.text.Normalizer;
import model.*;
import java.util.*;

public class InsertStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String id, name, email;
        id = request.getParameter("id");
        id = id.trim();
        StudentDAO stD = new StudentDAO();
        MarkDAO mD = new MarkDAO();
        SubjectDAO sD = new SubjectDAO();
        String alert;
        if (id == null || id.trim().isEmpty()) {
            alert = "The id must not be empty!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertStudent_1.jsp").include(request, response);
            return;
        }
        Student check = stD.getStudent(id);
        if (check != null) {
            alert = "The id " + id + " already exists!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertStudent_1.jsp").forward(request, response);
            return;
        }
        name = request.getParameter("name");
        if (name == null || name.trim().length() == 0) {
            alert = "The name must not be empty!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertStudent_1.jsp").include(request, response);
            return;
        }
        email = request.getParameter("email");
        if (email == null || email.trim().length() == 0) {
            alert = "The email must not be empty!";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("insertStudent.jsp").include(request, response);
            return;
        }
        java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
        boolean gender = Boolean.valueOf(request.getParameter("gender"));
        Student x = new Student(id, name, email, dob, gender);
        stD.insert(x);
        List<String> sids = sD.getIds();
        for (String sid : sids) {
            mD.insert(sid, id);
        }
        UserDAO uD = new UserDAO();
        String username = "";
        String strWithoutDiacritics = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String[] names = strWithoutDiacritics.split(" ");
        username += names[names.length-1].toLowerCase();
        for (int i = 0; i < names.length-1; i++) {
            username += names[i].toLowerCase().charAt(0);
        }
        uD.insert(new User(id, username, "12", 3));
        request.getRequestDispatcher("listStudent_1.jsp").forward(request, response);
    }
}
