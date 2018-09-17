<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.dao.*" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="kz.iitu.beans.CourseBean" %><%--
  Created by IntelliJ IDEA.
  User: zhakhanyergali
  Date: 11.11.17
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="headerUser.jsp"/>
<%
    CourseBean courseBean = new CourseBean();
    Users currentUser = (Users) request.getSession().getAttribute("currentUser");
    List<GroupCourses> groupCoursesList = (List<GroupCourses>) request.getAttribute("groupCoursesList");
    List<GroupUsers> usersList = (List<GroupUsers>) request.getAttribute("usersList");
%>

<script>

    function myStudents() {
        document.getElementById("students").classList.toggle("show");
    }

    window.onclick = function(event) {
        if (!event.target.matches('.studbtn')) {

            var dropdowns = document.getElementsByClassName("students-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
</script>
<style>
    .studbtn {
        background-color: #4CAF50;
        color: white;
        padding: 16px;
        font-size: 16px;
        border: none;
        cursor: pointer;
    }

    .studdown {
        position: relative;
        display: inline-block;
    }

    .students-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .students-content h4 {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .studdown:hover .students-content {
        display: block;
    }

    .studdown:hover .studbtn {
        background-color: #3e8e41;
    }

    .teachbtn {
        background-color: #4CAF50;
        color: white;
        padding: 16px;
        font-size: 16px;
        border: none;
        cursor: pointer;
    }

    .teachdown {
        position: relative;
        display: inline-block;
    }

    .heading {
        display: inline-block;
    }

    .teachers-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .teachers-content h4 {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .teachdown:hover .teachers-content {
        display: block;
    }

    .teachdown:hover .teachbtn {
        background-color: #3e8e41;
    }



    .teachers-content h4:hover {background-color: #f1f1f1}

    .students-content h4:hover {background-color: #f1f1f1}

    .courses-content h4:hover {background-color: #f1f1f1}

    .coursebtn {
        background-color: #4CAF50;
        color: white;
        padding: 16px;
        font-size: 16px;
        border: none;
        cursor: pointer;
    }

    .coursedown {
        position: relative;
        display: inline-block;
    }

    .courses-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .courses-content h4 {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .coursedown:hover .courses-content {
        display: block;
    }

    .coursedown:hover .coursebtn {
        background-color: #3e8e41;
    }

    .list {
        float: right;
    }
</style>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Groups of <%=currentUser.getName() + " " + currentUser.getSurname()%>: </h2>
    <%
        int n =1;
        if(groupCoursesList!=null) {
        for(GroupCourses group: groupCoursesList) {
    %>

    <div class="news_border" style="min-height: 30px;
                                    margin-bottom: 10px;
                                    background: #f5f5f5;
                                    padding: 6px;
                                    border: 1px solid #e3e3e3;
                                    box-shadow: 0px 8px 16px 0px #bbb;
                                    border-radius: 4px;">

        <div class="news_content" style="padding: 20px;">
            <div class="heading" style="margin-bottom: 10px; margin-left: 20px;">
                <h3><%=n + ". " + group.getGroup_id().getName()%></h3>
            </div>

            <div class="list">
                <div class="coursedown">
                    <button onclick="myStudents()" class="coursebtn">Courses &#9660;</button>
                    <div id="courses" class="courses-content">
                        <h4><a href="/userlessons?course_id=<%=group.getCourse_id().getId()%>"><%=group.getCourse_id().getName()%></a></h4>
                    </div>
                </div>

                <div class="studdown">
                    <button onclick="myStudents()" class="studbtn">Students &#9660;</button>
                    <div id="students" class="students-content">
                        <%
                            int m=1;
                            for(GroupUsers student: usersList) {
                                if(student.getGroup_id().getId()==group.getGroup_id().getId() && student.getUser_id().getRole_id().getId()==Long.valueOf(3)) {
                            %>
                                <h4><%=m + ". " + student.getUser_id().getName() + " " + student.getUser_id().getSurname() %></h4>
                            <%
                                    m++;
                                }
                            }
                        %>
                    </div>
                </div>

                <div class="teachdown">
                    <button onclick="myStudents()" class="teachbtn">Teachers &#9660;</button>
                    <div id="teachers" class="teachers-content">
                        <%
                            int c=1;
                            for(GroupUsers student: usersList) {
                                if(student.getGroup_id().getId()==group.getGroup_id().getId() && student.getUser_id().getRole_id().getId()==Long.valueOf(2)) {
                        %>
                        <h4><%=c + ". " + student.getUser_id().getName() + " " + student.getUser_id().getSurname() %></h4>
                        <%
                                    c++;
                                }
                            }
                        %>
                    </div>
                </div>
            </div>

        </div>

    </div>

    <%
            n++;
        }
    }else {
    %>
        <h4>List is empty</h4>
    <%
        }
    %>

</div>

</body>
</html>