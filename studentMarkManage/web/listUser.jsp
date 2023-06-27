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
        <title>User Edit Page</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <%  
        User x = (User) request.getSession().getAttribute("currUser");
        if(x == null){
            response.sendRedirect("index.html");
            return;
        }
        StudentDAO sD = new StudentDAO();
        TeacherDAO tD = new TeacherDAO();
        Student curStudent = sD.getStudent(x.getId());
        Teacher curTeacher = tD.getTeacher(x.getId());
        UserDAO uD = new UserDAO();
        List<User> users = null;
        users = (List<User>) request.getAttribute("users");
        if(users==null){
            users = uD.getUsers();
        }
        List<String> colNames = uD.getColNames("Users");
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
            <div class="table-name">USER LIST - SE1724</div>
            <!-- Sort -->
            <form class='sort-option' action="sortUser" method="post">
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

            <!-- Table of Users -->
            <table border="1">
                <tr>
                    <th>id</th>
                    <th>username</th>
                    <th>password</th>
                    <th>role</th>
                    <th colspan="2" class='table-option' onclick='window.location.href = "insertUser.jsp?"'>Insert</th>

                </tr> 
                <%for (User u : users) {%>
                <tr>
                    <td><%=u.getId()%></td>
                    <td><%=u.getUsername()%></td>
                    <td><%=u.getPassword()%></td>
                    <td><%=u.getRole()%></td>
                    <td class='table-option' onclick="window.location.href = 'updateUser.jsp?id=<%= u.getId() %>'">Update</td>
                    <td class='table-option' onclick="window.location.href = 'deleteUser.jsp?id=<%= u.getId() %>'">Delete</td>
                </tr>
                <%}%>
            </table>
            <button onclick='window.history.go(-1);'>Back to previous page</button>
    </body>
</html>
