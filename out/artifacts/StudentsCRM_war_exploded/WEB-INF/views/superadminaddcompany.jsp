<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="java.util.List" %><%--
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
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Add new super admin</h2>
        <form action="/superadmin/insertCabinet" method="post">
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" placeholder="Name" class="input" /></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><textarea style="height: 200px; width: 400px; " name="description" placeholder="Description..." class="input"></textarea></td>
            </tr>
        </table>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>

</body>
</html>