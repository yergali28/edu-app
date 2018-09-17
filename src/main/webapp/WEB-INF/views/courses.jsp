<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.*" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: zhakhanyergali
  Date: 11.11.17
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="headerUser.jsp"/>
<%
    Users currentUser = (Users) request.getSession().getAttribute("currentUser");
    List<GroupCourses> coursesList = (List<GroupCourses>) request.getAttribute("groupCoursesList");
    HashMap<String, Average> map = (HashMap<String, Average>) request.getAttribute("map");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Courses of <%=currentUser.getName() + " " + currentUser.getSurname()%>: </h2>

    <%
        int n =1;
        if(coursesList!=null) {
        for(GroupCourses courses: coursesList) {
    %>

    <div class="news_border" style="min-height: 30px;
                                    margin-bottom: 10px;
                                    background: #f5f5f5;
                                    padding: 6px;
                                    border: 1px solid #e3e3e3;
                                    box-shadow: 0px 8px 16px 0px #bbb;
                                    border-radius: 4px;">

        <div class="news_content" style="padding: 20px;">
            <div class="heading" style="display: inline-block; margin-bottom: 10px; margin-left: 20px;">
                <h3><%=n + ". " + courses.getCourse_id().getName()%></h3>
            </div>
            <p><%=courses.getCourse_id().getDescription()%></p>
            <%
                if(currentUser.getRole_id().getId()==Long.valueOf(3)) {
            %>
            <h3 style="display: inline-block; color: #0aa0dc; float: right; margin-left: 10px;">
                Average of marks: <%=map.get("" + courses.getCourse_id().getId()).getMark()%>%
            </h3>
            <h3 style="display: inline-block; color: #0aa0dc; float: right;">
                Average of attendance: <%=map.get("" + courses.getCourse_id().getId()).getAttendance()%>%
            </h3>
            <%
                }
            %>
        </div>

    </div>

    <%
            n++;
        }
    }
    %>

</div>

</body>
</html>