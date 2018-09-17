<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.Users" %>
<%@ page import="kz.iitu.dao.Courses" %>
<%@ page import="kz.iitu.dao.Lessons" %><%--
  Created by IntelliJ IDEA.
  User: zhakhanyergali
  Date: 11.11.17
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="headerAdmin.jsp"/>
<%
    Users currentUser = (Users) request.getSession().getAttribute("currentUser");
    List<Lessons> lessonsList = (List<Lessons>) request.getAttribute("lessonsList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Lessons: </h2>

    <table>
        <%
            int n = 1;
            if(lessonsList!=null) {
                for(Lessons lesson: lessonsList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>. Title: <%out.print(lesson.getTitle()); %></h4><br>
                <h4>Course: <%out.print(lesson.getCourse().getName()); %></h4><br>
                <h5>Content: <%out.print(lesson.getContent()); %></h5><br>
            </td>
            <td>
                <center>
                    <form action="/editlesson" method="GET" style="display: inline;">
                        <input type="hidden" name="lessonIdForEdit" value="<%=lesson.getId()%>">
                        <input type="submit" value="Edit" style="width: 150px;">
                    </form>

                    <form action="/deleteLesson" method="post" style="display: inline;">
                        <input type="hidden" name="lessonIdForDelete" value="<%=lesson.getId()%>">
                        <input type="submit" value="Delete" style="width: 150px;">
                    </form>
                </center>
            </td>
        </tr>
        <%
                    n++;
                }
            }
        %>
    </table>

    <div class="save_button">

    </div>
</div>

</body>
</html>