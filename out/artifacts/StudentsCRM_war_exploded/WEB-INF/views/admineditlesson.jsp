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
    Lessons singleLesson = (Lessons) request.getAttribute("singleLessonForEdit");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Edit course <%=singleLesson.getTitle()%></h2>
    <form action="/saveLesson" method="post">
        <table>
            <tr>
                <td>Title: </td>
                <td><input type="text" name="title" placeholder="Name" class="input" value="<%=singleLesson.getTitle()%>"/></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><textarea style="height: 200px; width: 400px; " name="content" placeholder="Description..." class="input"><%=singleLesson.getContent()%></textarea></td>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%=singleLesson.getId()%>"/>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>

</body>
</html>