<%-- 
    Document   : StudentSite
    Created on : Feb 28, 2023, 11:50:02 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*"%>
<%@page import = "model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Home Page</title>
    </head>
    <%  
         User x = (User) request.getSession().getAttribute("currUser");
        if(x == null){
            response.sendRedirect("index.html");
            return;
        }
        StudentDAO u = new StudentDAO();
        TeacherDAO tD = new TeacherDAO();
        Student curStudent = u.getStudent(x.getId());
        Teacher curTeacher = tD.getTeacher(x.getId());
        List<Student> students = null;
        students = (List<Student>) request.getAttribute("students");
        if(students==null){
            students = u.getStudents();
        }
        List<String> colNames = u.getColNames("Student");
    %>
    <body>
        <%@include file="header.jsp" %>
        <div class="header-placeholder"></div>
        <div class="whole-body">
            <div class="table-name">STUDENT LIST - SE1724</div>

            <!-- Sort -->
            <form class="sort-option" action="sortStudent" method="post">
                <div>Sorting type 
                    <input type="radio" name="sortType" value="ASC" checked/> Ascending
                    <input type="radio" name="sortType" value="DESC"/> Descending</div>
                <div> By column 
                    <select name="colName">
                        <%for (String s : colNames) {%>
                        <option value="<%=s%>"><%=s%></option>
                        <%}%>
                    </select>
                    <input type="submit" value="Sort"></div>
            </form>

            <!-- Table of Students -->
            <table>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>email</th>
                    <th>dob</th>
                    <th>gender</th>
                </tr> 
                <%for (Student s : students) {%>
                <tr>
                    <td><%=s.getId()%></td>
                    <td><%=s.getName()%></td>
                    <td><%=s.getEmail()%></td>
                    <td><%=s.getDob()%></td>
                    <%if(s.getGender()){%>
                    <td>Male</td>
                    <%}else{%>
                    <td>Female</td>
                    <%}%>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
