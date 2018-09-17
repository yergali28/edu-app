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
    <h2>Добавить нового пользователя</h2>
        <form action="/insertUser" method="post">
        <table>
            <tr>
                <td>Логин: </td>
                <td><input type="text" name="login"  class="input" /></td>
            </tr>
            <tr>
                <td>Пароль: </td>
                <td><input type="password" name="password"  class="input" /></td>
            </tr>
            <tr>
                <td>Имя: </td>
                <td><input type="text" name="name"  class="input" /></td>
            </tr>
            <tr>
                <td>Фамилия: </td>
                <td><input type="text" name="surname"  class="input" /></td>
            </tr>
            <tr>
                <td>Роль: </td>
                <td>
                    <select type="text" name="role" class="input">
                        <option value="1" selected="selected">Админ</option>
                        <option value="2">Учитель</option>
                        <option value="3">Студент</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Сохранить" class="inp_subm"/>
    </form>
</div>

</body>
</html>