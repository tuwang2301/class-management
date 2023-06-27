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
        <title>Subject Edit Page</title>
        <link rel="stylesheet" href="style.css">
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
        SubjectDAO sD = new SubjectDAO();
        List<Subject> subjects = null;
        subjects = (List<Subject>) request.getAttribute("subjects");
        if(subjects==null){
            subjects = sD.getSubjects();
        }
        List<String> colNames = sD.getColNames("Subject");
    %>
    <body>
        <!-- My Profile -->
        <header>
            <div class="header-left">
                <%switch (x.getRole()){
                    case 1:%> 

                <span>Hello Admin</span>
                <div class="home" onclick='window.location.href = "listStudent.jsp"'>Home</div>
                <div class="my-profile" onclick='window.location.href = "proAdmin.jsp"'>Edit</div>

                <%break;
                    case 2:%>     

                <span>Hello <%=curTeacher.getName()%></span>
                <div class="home" onclick='window.location.href = "listStudent.jsp"'>Home</div>
                <div class="my-profile" onclick='window.location.href = "proTeacher.jsp"'>My Profile</div>

                <%break;
                    case 3:%> 

                <span>Hello <%=curStudent.getName()%></span>
                <div class="home" onclick='window.location.href = "listStudent.jsp"'>Home</div>
                <div class="my-profile" onclick='window.location.href = "proStudent.jsp"'>My Profile</div>

                <%break;}%>
            </div>
            <div class="header-right">
                <button class="btn-logout" onclick='window.location.href = "logout"'>Logout</button>
            </div>
        </header>
        <div class="header-placeholder"></div>
        <div class="whole-body">
            <div class="table-name">SUBJECT LIST - SE1724</div>
            <!-- Sort -->
            <form class='sort-option' action="sortSubject" method="post">
                <div> Sorting type: 
                    <input type="radio" name="sortType" value="ASC" checked/> Ascending
                    <input type="radio" name="sortType" value="DESC"/> Descending</div>
                <div>
                    By column: 
                        <select name="colName">
                            <%for (String s : colNames) {%>
                            <option value="<%=s%>"><%=s%></option>
                            <%}%>
                        </select>
                        <input type="submit" value="Sort">
                </div>
            </form>

        <!-- Table of Subjects -->
        <table border="1">
            <tr>
                <th>id</th>
                <th>tid</th>
                <th>name</th>
                <th colspan="2" class='table-option' onclick='window.location.href ="insertSubject.jsp?"'>Insert</th>
            </tr> 
            <%for (Subject s : subjects) {%>
            <tr>
                <td><%=s.getId()%></td>
                <%if(s.getTeacher() == null){%>
                <td>null</td><%}else{%>
                <td><%=s.getTeacher().getId()%></td><%}%>
                <td><%=s.getName()%></td>
                <td class='table-option' onclick="window.location.href ='updateSubject.jsp?id=<%= s.getId() %>'">Update</td>
                <td class='table-option' onclick="window.location.href ='deleteSubject.jsp?id=<%= s.getId() %>'">Delete</td>
            </tr>
            <%}%>
        </table>
        <button onclick='window.history.go(-1);'>Back to previous page</button>
        </div>
    </body>
</html>
