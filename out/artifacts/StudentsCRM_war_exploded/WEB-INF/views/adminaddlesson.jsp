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
    Users user = (Users) request.getSession().getAttribute("currentUser");
    List<Courses> coursesList = (List<Courses>) request.getAttribute("coursesList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Добавить урок</h2>
        <form action="/insertLesson" method="post">
        <table>
            <tr>
                <td>Предмет: </td>
                <td>
                    <select type="text" name="course" class="input">
                        <option value="" selected="selected"></option>
                        <%
                            for(Courses course: coursesList) {
                        %>
                        <option value="<%=course.getId()%>"><%=course.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Название урока: </td>
                <td><input type="text" name="title" placeholder="Заголовок..." class="input" /></td>
            </tr>
            <tr>
                <td>Описание урока: </td>
                <td><textarea style="height: 200px; width: 400px; " name="content" placeholder="Контент..." class="input"></textarea></td>
            </tr>
        </table>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>

</body>
</html>