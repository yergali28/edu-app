<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.Users" %>
<%@ page import="kz.iitu.dao.Courses" %><%--
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
    List<Courses> coursesList = (List<Courses>)request.getAttribute("coursesList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Предметы: </h2>

    <table>
        <%
            int n = 1;
            if(coursesList!=null) {
                for(Courses c: coursesList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>.<%out.print(" " + c.getName()); %></h4><br>
                <h5><%out.print(c.getDescription()); %></h5><br>
            </td>
            <td>
                <center>
                    <form action="/editcourse" method="GET" style="display: inline;">
                        <input type="hidden" name="courseIdForEdit" value="<%=c.getId()%>">
                        <input type="submit" value="Изменить" style="width: 150px;">
                    </form>

                    <form action="/deleteCourse" method="post" style="display: inline;">
                        <input type="hidden" name="courseIdForDelete" value="<%=c.getId()%>">
                        <input type="submit" value="Удалить" style="width: 150px;">
                    </form>

                    <form action="/courseLessons" method="get" style="display: inline;">
                        <input type="hidden" name="courseId" value="<%=c.getId()%>">
                        <input type="submit" value="Уроки" style="width: 150px;">
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