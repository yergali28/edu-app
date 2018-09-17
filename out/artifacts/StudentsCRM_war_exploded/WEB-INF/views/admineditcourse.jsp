<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.Cabinets" %>
<%@ page import="kz.iitu.dao.Users" %>
<%@ page import="kz.iitu.dao.Courses" %><%--
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
    Courses courseForEdit = (Courses) request.getAttribute("singleCourseForEdit");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Edit course <%=courseForEdit.getName()%></h2>
    <form action="/saveCourse" method="post">
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" placeholder="Name" class="input" value="<%=courseForEdit.getName()%>"/></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><textarea style="height: 200px; width: 400px; " name="description" placeholder="Description..." class="input"><%=courseForEdit.getDescription()%></textarea></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%=courseForEdit.getId()%>"/>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>

</body>
</html>