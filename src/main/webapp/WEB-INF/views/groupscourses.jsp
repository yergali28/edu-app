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
    List<GroupCourses> groupCoursesList = (List<GroupCourses>) request.getAttribute("groupCoursesList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Courses of group <%=groupCoursesList.get(0).getGroup_id().getName()%></h2>

    <table>
        <%
            int n = 1;
            if(groupCoursesList!=null) {
                for(GroupCourses groupCourses: groupCoursesList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>. Name: <%out.print(groupCourses.getCourse_id().getName());%></h4><br>
            </td>
            <td>
                <h4><%out.print(n); %>. Description: <%out.print(groupCourses.getCourse_id().getDescription());%></h4><br>
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