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
    Users currentUser = (Users) request.getSession().getAttribute("currentUser");
    Users userForEdit = (Users) request.getAttribute("userForEdit");
    if(userForEdit!=null) {
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Отредактировать данные пользователя</h2>
    <form action="/saveUser" method="post">
        <table>
            <tr>
                <td>Логин: </td>
                <td><input type="text" name="login" class="input" value="<%=userForEdit.getLogin()%>"/></td>
            </tr>
            <tr>
                <td>Пароль: </td>
                <td><input type="password" name="password" class="input"/></td>
            </tr>
            <tr>
                <td>Имя: </td>
                <td><input type="text" name="name" class="input" value="<%=userForEdit.getName()%>"/></td>
            </tr>
            <tr>
                <td>Фамилия: </td>
                <td><input type="text" name="surname" class="input" value="<%=userForEdit.getSurname()%>"/></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%=userForEdit.getId()%>"/>
        <input type="submit" value="Сохранить" class="inp_subm"/>
    </form>
</div>
<%
    }else {
%>
<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Изменить собственный профиль</h2>
    <form action="/saveUser" method="post">
        <table>
            <tr>
                <td>Логин: </td>
                <td><input type="text" name="login" class="input" value="<%=currentUser.getLogin()%>"/></td>
            </tr>
            <tr>
                <td>Пароль: </td>
                <td><input type="password" name="password" class="input"/></td>
            </tr>
            <tr>
                <td>Имя: </td>
                <td><input type="text" name="name" class="input" value="<%=currentUser.getName()%>"/></td>
            </tr>
            <tr>
                <td>Фамилия: </td>
                <td><input type="text" name="surname" class="input" value="<%=currentUser.getSurname()%>"/></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%=currentUser.getId()%>"/>
        <input type="submit" value="Сохранить" class="inp_subm"/>
    </form>
</div>
<%
    }
%>
</body>
</html>