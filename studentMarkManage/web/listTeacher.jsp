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
        <title>Teacher Edit Page</title>
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
        List<Teacher> teachers = null;
        teachers = (List<Teacher>) request.getAttribute("teachers");
        if(teachers==null){
            teachers = tD.getTeachers();
        }
        List<String> colNames = tD.getColNames("Teacher");
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
        <div class="table-name">TEACHER LIST - SE1724</div>
            <!-- Sort -->
            <form class='sort-option' action="sortTeacher" method="post">
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

        <!-- Table of Students -->
        <table border="1">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>email</th>
                <th colspan="2" class='table-option' onclick='window.location.href ="insertTeacher.jsp?"'>Insert</th>
            </tr> 
            <%for (Teacher t : teachers) {%>
            <tr>
                <td><%=t.getId()%></td>
                <td><%=t.getName()%></td>
                <td><%=t.getEmail()%></td>
                <td class='table-option' onclick="window.location.href ='updateTeacher.jsp?id=<%= t.getId() %>'">Update</td>
                <td class='table-option' onclick="window.location.href ='deleteTeacher.jsp?id=<%= t.getId() %>'">Delete</td>
            </tr>
            <%}%>
        </table>
        <button onclick='window.history.go(-1);'>Back to previous page</button>
        </div>
    </body>
</html>
