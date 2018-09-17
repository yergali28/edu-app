<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.Users" %><%--
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
    List<Users> studentsList = (List<Users>)request.getAttribute("studentsList");
    List<Users> teachersList = (List<Users>)request.getAttribute("teachersList");
    List<Users> adminsList = (List<Users>)request.getAttribute("adminsList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Админы</h2>

    <table>
        <%
            int n = 1;
            if(adminsList!=null) {
            for(Users s: adminsList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>.<%out.print(" " + s.getName() + " " + s.getSurname()); %></h4><br>
            </td>
            <td>
                <center>
                    <form action="/edituser" method="GET" style="display: inline;">
                        <input type="hidden" name="uIdForEdit" value="<%out.print(s.getId());%>">
                        <input type="submit" value="Изменить" style="width: 150px;">
                    </form>

                    <form action="/deleteUser" method="post" style="display: inline;">
                        <input type="hidden" name="uIdForDelete" value="<%out.print(s.getId());%>">
                        <input type="submit" value="Удалить" style="width: 150px;">
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

    <h2>Учителя</h2>

    <table>
        <%
            int m = 1;
            if(teachersList!=null) {
            for(Users s: teachersList) {
        %>
        <tr>
            <td>
                <h4><%out.print(m); %>.<%out.print(" " + s.getName() + " " + s.getSurname()); %></h4><br>
            </td>
            <td>
                <center>
                    <form action="/edituser" method="GET" style="display: inline;">
                        <input type="hidden" name="uIdForEdit" value="<%out.print(s.getId());%>">
                        <input type="submit" value="Изменить" style="width: 150px;">
                    </form>

                    <form action="/deleteUser" method="post" style="display: inline;">
                        <input type="hidden" name="uIdForDelete" value="<%out.print(s.getId());%>">
                        <input type="submit" value="Удалить" style="width: 150px;">
                    </form>
                </center>
            </td>
        </tr>
        <%
                    m++;
                }
            }
        %>
    </table>

    <h2>Учащиеся</h2>

    <table>
        <%
            int v = 1;
            if(studentsList!=null) {
            for(Users s: studentsList) {
        %>
        <tr>
            <td>
                <h4><%out.print(v); %>.<%out.print(" " + s.getName() + " " + s.getSurname()); %></h4><br>
            </td>
            <td>
                <center>
                    <form action="/edituser" method="GET" style="display: inline;">
                        <input type="hidden" name="uIdForEdit" value="<%out.print(s.getId());%>">
                        <input type="submit" value="Изменить" style="width: 150px;">
                    </form>

                    <form action="/deleteUser" method="post" style="display: inline;">
                        <input type="hidden" name="uIdForDelete" value="<%out.print(s.getId());%>">
                        <input type="submit" value="Удалить" style="width: 150px;">
                    </form>
                </center>
            </td>
        </tr>
        <%
                    v++;
                }
            }
        %>
    </table>

</div>

</body>
</html>