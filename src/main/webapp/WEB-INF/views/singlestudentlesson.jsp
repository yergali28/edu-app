<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.*" %>
<%--
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
    Lessons lesson = (Lessons) request.getAttribute("singleLesson");
    Marks singleMark = (Marks) request.getAttribute("singleMark");
    Attendances singleAttendance = (Attendances) request.getAttribute("singleAttendance");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">


    <div class="news_border" style="min-height: 30px;
                                    margin-top: 10px;
                                    margin-bottom: 10px;
                                    background: #f5f5f5;
                                    padding: 6px;
                                    border: 1px solid #e3e3e3;
                                    box-shadow: 0px 8px 16px 0px #bbb;
                                    border-radius: 4px;">

        <div class="news_content" style="padding: 20px;">
            <div class="heading" style="margin-bottom: 10px; margin-left: 20px;">

                <h2>Course: <%=lesson.getCourse().getName()%></h2>

                <h3>Lesson: <%=lesson.getTitle()%></h3>

                <p><b>Lesson description:</b> <%=lesson.getContent()%></p>
                <%
                    if(singleMark!=null) {
                        %>
                            <h4 style="margin-top: 20px;">Grade: <%=singleMark.getMark_value() + "%" %></h4><br>
                            <p><%="Notes: " + singleMark.getMark_notes()%></p>
                        <%
                    }else {
                        %>
                            <h4>Grade: not yet rated</h4>
                        <%
                    }

                    if(singleAttendance!=null) {
                            int attendace = singleAttendance.getAttendance_value();
                            if(attendace==1) attendace = 100;
                            else attendace = 0;
                        %>
                            <h4 style="margin-top: 20px;">Attendance: <%=attendace + "%"%></h4><br>
                            <p><%="Notes: " + singleAttendance.getAttendance_note()%></p>
                        <%
                    }else {
                        %>
                            <h4>Attendance: not yet rated</h4>
                        <%
                    }
                %>
            </div>

        </div>

    </div>

</div>

</body>
</html>