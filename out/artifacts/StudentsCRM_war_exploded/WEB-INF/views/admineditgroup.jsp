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
    Groups singleGroup = (Groups) request.getAttribute("singleGroupForEdit");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Edit group <%=singleGroup.getName()%></h2>
    <form action="/saveGroup" method="post">
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" placeholder="Name" class="input" value="<%=singleGroup.getName()%>"/></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%=singleGroup.getId()%>"/>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>

</body>
</html>