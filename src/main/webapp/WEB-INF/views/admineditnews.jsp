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
<script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script>
<%
    Users user = (Users) request.getSession().getAttribute("currentUser");
    News news = (News) request.getAttribute("singleNewsForEdit");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">
    <h2>Add news</h2>
        <form action="/saveNews" method="post">
        <table>
            <tr>
                Title of news:
                <input type="text" name="title" style="width: 900px; margin-bottom: 30px;" class="input" value="<%=news.getTitle()%>"/>
            </tr>
            <tr>
                <textarea name="content" style="height: 300px; "><%=news.getContent()%></textarea>
            </tr>
        </table>
        <input type="hidden" name="id" value="<%=news.getId()%>"/>
        <input type="submit" value="Save" class="inp_subm"/>
    </form>
</div>


</body>
</html>