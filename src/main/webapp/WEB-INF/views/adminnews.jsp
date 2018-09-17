<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.*" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
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
    List<News> newsList = (List<News>) request.getAttribute("newsList");

%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Новости: </h2>

    <%
        if(newsList!=null) {
            for(News news: newsList) {
    %>
    <div class="news_border" style="min-height: 30px;
                                    background: #f5f5f5;
                                    padding: 6px;
                                    border: 1px solid #e3e3e3;
                                    box-shadow: 0px 8px 16px 0px #bbb;
                                    border-radius: 4px;">

            <div class="news_content" style="padding: 20px;">
                <div class="heading" style="margin-bottom: 10px; margin-left: 20px;">
                    <h3><%=news.getTitle()%></h3>
                    <h4>Автор: <span style="color: #0aa0dc;"><%=news.getUser_id().getName() + " " + news.getUser_id().getSurname()%></span></h4>
                </div>

                <p><%=news.getContent()%></p>
                <p><span style="margin-right: 30px; float: right;"><%=news.getPost_date()%></span></p>
            </div>

    </div>

    <div class="someclass" style="margin-top: 5px; margin-bottom: 5px; ">
        <form action="/editNews" method="GET" style="display: inline; ">
            <input type="hidden" name="newsIdForEdit" value="<%=news.getId()%>">
            <input type="submit" value="Изменить" style="width: 150px;">
        </form>

        <form action="/deleteNews" method="post" style="display: inline; margin-right: 5px;">
            <input type="hidden" name="newsIdForDelete" value="<%=news.getId()%>">
            <input type="submit" value="Удалить" style="width: 150px;">
        </form>
    </div>
    <%
            }
        }
    %>

</div>

</body>
</html>