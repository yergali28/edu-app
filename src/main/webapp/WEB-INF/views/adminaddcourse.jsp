<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.Cabinets" %>
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
    Users user = (Users) request.getSession().getAttribute("currentUser");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Добавить новый предмет</h2>
        <form action="/insertCourse" method="post">
        <table>
            <tr>
                <td>Название предмета: </td>
                <td><input type="text" name="name"  class="input" /></td>
            </tr>
            <tr>
                <td>Описание предмета: </td>
                <td><textarea style="height: 200px; width: 400px; " name="description" placeholder="Описание..." class="input"></textarea></td>
            </tr>
        </table>
        <input type="submit" value="Сохранить" class="inp_subm"/>
    </form>
</div>

</body>
</html>