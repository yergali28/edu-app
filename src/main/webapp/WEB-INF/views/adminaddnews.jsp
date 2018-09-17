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
<script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script>
<%
    Users user = (Users) request.getSession().getAttribute("currentUser");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Добавить новость</h2>
        <form action="/insertNews" method="post">
        <table>
            <tr>
                Заголовок:
                <input type="text" name="title" style="width: 900px; margin-bottom: 30px;" class="input" />
            </tr>
            <tr>
                <textarea name="content" style="height: 300px; "></textarea>
            </tr>
        </table>
        <input type="submit" value="Сохранить" class="inp_subm"/>
    </form>
</div>


</body>
</html>