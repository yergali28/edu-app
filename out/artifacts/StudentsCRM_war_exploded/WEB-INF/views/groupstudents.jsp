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
    List<GroupUsers> groupUsersList = (List<GroupUsers>) request.getAttribute("groupUsersList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Students of group <%=groupUsersList.get(0).getGroup_id().getName()%></h2>

    <table>
        <%
            int n = 1;
            if(groupUsersList!=null) {
                for(GroupUsers groupUsers: groupUsersList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>. <%out.print(groupUsers.getUser_id().getName() + " " + groupUsers.getUser_id().getSurname()); %></h4><br>
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