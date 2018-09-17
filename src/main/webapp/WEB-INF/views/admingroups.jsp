<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.*" %><%--
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
    List<Groups> groupsList = (List<Groups>) request.getAttribute("groupsList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Группы: </h2>

    <table>
        <%
            int n = 1;
            if(groupsList!=null) {
                for(Groups group: groupsList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>. <%out.print(group.getName()); %></h4><br>
            </td>
            <td>
                <center>
                    <form action="/editgroup" method="GET" style="display: inline;">
                        <input type="hidden" name="groupIdForEdit" value="<%=group.getId()%>">
                        <input type="submit" value="Изменить" style="width: 150px;">
                    </form>

                    <form action="/deleteGroup" method="post" style="display: inline;">
                        <input type="hidden" name="groupIdForDelete" value="<%=group.getId()%>">
                        <input type="submit" value="Удалить" style="width: 150px;">
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