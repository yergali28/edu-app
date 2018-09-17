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
    List<Cabinets> cabinetsList = (List<Cabinets>)request.getAttribute("cabinetsList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Companies</h2>

    <table>
        <%
            int n = 1;
            if(cabinetsList!=null) {
                for(Cabinets c: cabinetsList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>.<%out.print(" " + c.getName()); %></h4><br>
                <h5><%out.print(c.getDescription()); %></h5><br>
            </td>
            <td>
                <center>
                    <form action="/superadmin/editcabinet" method="GET" style="display: inline;">
                        <input type="hidden" name="cIdForEdit" value="<%out.print(c.getId());%>">
                        <input type="submit" value="Edit" style="width: 150px;">
                    </form>

                    <form action="/superadmin/deleteCabinet" method="post" style="display: inline;">
                        <input type="hidden" name="cIdForDelete" value="<%out.print(c.getId());%>">
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