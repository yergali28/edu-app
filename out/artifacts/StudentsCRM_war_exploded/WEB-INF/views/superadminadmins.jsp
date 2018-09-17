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
    List<Users> adminsList = (List<Users>)request.getAttribute("adminsList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Admins</h2>

    <table>
        <%
            int n = 1;
            if(adminsList!=null) {
                for(Users a: adminsList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>.<%out.print(" " + a.getName() + " " + a.getSurname()); %></h4><br>
                <%--<h5><%out.print(a.getRole().getName()); %></h5><br>--%>
            </td>
            <td>
                <center>
                    <form action="/superadmin/editadmin" method="GET" style="display: inline;">
                        <input type="hidden" name="aIdForEdit" value="<%out.print(a.getId());%>">
                        <input type="submit" value="Edit" style="width: 150px;">
                    </form>

                    <form action="/superadmin/deleteAdmin" method="post" style="display: inline;">
                        <input type="hidden" name="aIdForDelete" value="<%out.print(a.getId());%>">
                        <input type="submit" value="Delete" style="width: 150px;">
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