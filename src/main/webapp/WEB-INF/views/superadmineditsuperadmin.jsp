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
    SuperAdmins adminForEdit = (SuperAdmins) request.getAttribute("singleSAForEdit");
    if(adminForEdit!=null) {
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Edit super admin</h2>
        <form action="/superadmin/saveSuperAdmin" method="post">
        <table>
            <tr>
                <td>Login: </td>
                <td><input type="text" name="login"  class="input" value="<%out.print(adminForEdit.getLogin());%>"/></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password"  class="input"/></td>
            </tr>
        </table>
        <input type="hidden" value="<%out.print(adminForEdit.getId());%>" name="id"/>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>

<%
    } else {
      %>
<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Edit profile <%=superAdmin.getLogin()%></h2>
    <form action="/superadmin/saveSuperAdmin" method="post">
        <table>
            <tr>
                <td>Login: </td>
                <td><input type="text" name="login"  class="input" value="<%out.print(superAdmin.getLogin());%>"/></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password"  class="input"/></td>
            </tr>
        </table>
        <input type="hidden" value="<%out.print(superAdmin.getId());%>" name="id"/>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>
      <%
    }
%>

</body>
</html>