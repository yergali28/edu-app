<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.Cabinets" %>
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
    Users user = (Users) request.getSession().getAttribute("currentUser");
    List<Courses> coursesList = (List<Courses>) request.getAttribute("coursesList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Добавить группу:</h2>
        <form action="/insertGroup" method="post">
        <table>
            <tr>
                <td>Название группы: </td>
                <td><input type="text" name="name" placeholder="Введите название группы..." class="input" /></td>
            </tr>
        </table>
        <input type="submit" value="Сохранить" class="inp_subm"/>
    </form>
</div>

</body>
</html>