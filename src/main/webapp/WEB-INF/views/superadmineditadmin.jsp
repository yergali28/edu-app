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
<jsp:include page="headerSuperAdmin.jsp"/>
<%
    SuperAdmins superAdmin = (SuperAdmins) request.getSession().getAttribute("superadmin");
    Users adminForEdit = (Users) request.getAttribute("adminForEdit");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Edit admin</h2>
        <form action="/superadmin/saveAdmin" method="post">
        <table>
            <tr>
                <td>Login: </td>
                <td><input type="text" name="login" class="input" value="<%=adminForEdit.getLogin()%>"/></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password"  class="input"/></td>
            </tr>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name"  class="input" value="<%=adminForEdit.getName()%>"/></td>
            </tr>
            <tr>
                <td>Surname: </td>
                <td><input type="text" name="surname"  class="input" value="<%=adminForEdit.getSurname()%>"/></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%=adminForEdit.getId()%>"/>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>

</body>
</html>