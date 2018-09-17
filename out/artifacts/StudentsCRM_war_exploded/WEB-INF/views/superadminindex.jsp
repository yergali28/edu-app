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
    List<SuperAdmins> superAdminsList = (List<SuperAdmins>)request.getAttribute("superAdminsList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Super Admins</h2>

    <table>
        <%
            int n = 1;
            for(SuperAdmins s: superAdminsList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>.<%out.print(" " + s.getLogin()); %></h4><br>
            </td>
            <td>
                <center>
                    <form action="/superadmin/editsuperadmin" method="GET" style="display: inline;">
                        <input type="hidden" name="saIdForEdit" value="<%out.print(s.getId());%>">
                        <input type="submit" value="Edit" style="width: 150px;">
                    </form>

                    <form action="/superadmin/deleteSuperAdmin" method="post" style="display: inline;">
                        <input type="hidden" name="saIdForDelete" value="<%out.print(s.getId());%>">
                        <input type="submit" value="Delete" style="width: 150px;">
                    </form>
                </center>
            </td>
        </tr>
        <%
                n++;
            }
        %>
    </table>

    <?php
			}
		?>

    <div class="save_button">

    </div>
</div>

</body>
</html>