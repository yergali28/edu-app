<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.Cabinets" %><%--
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
    List<Cabinets> cabinetsList = (List<Cabinets>) request.getAttribute("cabinetsList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Add new admin</h2>
        <form action="/superadmin/insertAdmin" method="post">
        <table>
            <tr>
                <td>Login: </td>
                <td><input type="text" name="login"  class="input" /></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password"  class="input" /></td>
            </tr>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name"  class="input" /></td>
            </tr>
            <tr>
                <td>Surname: </td>
                <td><input type="text" name="surname"  class="input" /></td>
            </tr>
            <tr>
                <td>Company: </td>
                <td>
                    <select type="text" name="company" class="input">
                        <option value="" selected="selected"></option>
                        <%
                            for(Cabinets cabinet: cabinetsList) {
                        %>
                        <option value="<%=cabinet.getId()%>"><%=cabinet.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
        </table>
        <input type="hidden" name="id" value="1"/>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>

</body>
</html>