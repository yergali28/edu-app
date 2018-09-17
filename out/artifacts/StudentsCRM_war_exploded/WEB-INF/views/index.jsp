<%@ page import="kz.iitu.dao.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="headerUser.jsp"/>
<%
    Users currentUser = (Users) request.getSession().getAttribute("currentUser");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Hello Wolrd</h2>

</div>

</body>
</html>