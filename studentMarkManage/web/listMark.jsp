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
        <title>Mark Page</title>
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
        MarkDAO mD = new MarkDAO();
        List<Mark> marks = null;
        marks = (List<Mark>) request.getAttribute("marks");
        if(marks==null){
            marks = mD.getMarks();
        }
        List<String> colNames = mD.getColNames("SubjectMark");
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
        <!-- Sort -->
        <div class="whole-body">
            <div class="table-name">MARK LIST - SE1724</div>

            <!-- Sort -->
            <form class="sort-option" action="sortMark" method="post">
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

            <!-- Group -->
            
            <p><div class="sort-option">
                <form action="groupMark" method="post">
                    Group: 
                    <select name="colName">
                        <option value="<%=colNames.get(0)%>"><%=colNames.get(0)%></option>
                        <option value="<%=colNames.get(1)%>"><%=colNames.get(1)%></option>
                    </select>
                    <input type="text" name="id">
                    <input type="submit" value="Group">
                </form>

                <!-- Show All -->
                <form action="groupMark" method="get">
                    <input type="submit" value="Show All">
                </form>
            </div>
            <!-- Table of Mark -->
            <table border="1">
                <tr>
                    <th>SubjectID</th>
                    <th>StudentID</th>
                    <th>GPA</th>
                    <th></th>
                </tr> 
                <%for (Mark m : marks) {%>
                <tr>
                    <td><%=m.getSubject().getId()%></td>
                    <td><%=m.getStudent().getId()%></td>
                    <td><%=m.getGpa()%></td>
                    <td class='table-option'
                    onclick="window.location.href='updateMark.jsp?sid=<%=m.getSubject().getId()%>&stid=<%=m.getStudent().getId()%>'">Edit</td>
                </tr>
                <%}%>
            </table>
            <button onclick='window.history.go(-1);'>Back to previous page</button>
        </div>
    </body>
</html>
