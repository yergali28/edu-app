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
<jsp:include page="headerUser.jsp"/>
<%
    Users currentUser = (Users) request.getSession().getAttribute("currentUser");
    Lessons lesson = (Lessons) request.getAttribute("singleLesson");
    List<GroupUsers> usersList = (List<GroupUsers>) request.getAttribute("usersList");
    List<GroupCourses> groupCoursesList = (List<GroupCourses>) request.getAttribute("groupCoursesList");
%>
<script>
    tinymce.init({ selector:'textarea' });

    function change(obj) {


        var selectBox = obj;
        var selected = selectBox.options[selectBox.selectedIndex].value;
        var select = document.getElementById("studentsOfGroup");

        if(selected === '0'){
            select.style.display = "none";
        }
        else{
            select.style.display = "inline-block";
        }
    }

    function action(obj) {


        var selectBox = obj;
        var selected = selectBox.options[selectBox.selectedIndex].value;
        var select = document.getElementById("action");

        if(selected === '0'){
            document.getElementById("at_user_id").value = null;
            document.getElementById("mark_user_id").value = null;
            select.style.display = "none";
        }
        else{
            var e =  document.getElementById("student");
            document.getElementById("at_user_id").value = e.options[e.selectedIndex].value;
            document.getElementById("mark_user_id").value = e.options[e.selectedIndex].value;
            select.style.display = "inline-block";
        }
    }

    function variant(obj) {


        var selectBox = obj;
        var selected = selectBox.options[selectBox.selectedIndex].value;

        if(selected === '0'){
            document.getElementById("").style.display = "none";
            document.getElementById("").style.display = "none";
        }else if(selected ==='1') {
            document.getElementById("attendance").style.display = "inline-block";
            document.getElementById("mark").style.display = "none";
        }else if(selected ==='2') {
            document.getElementById("mark").style.display = "inline-block";
            document.getElementById("attendance").style.display = "none";
        }
    }
</script>
<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Lesson: <%=lesson.getTitle()%></h2>

    <%
        int n =1;
        if(groupCoursesList!=null) {
        for(GroupCourses groupCourses: groupCoursesList) {
    %>

    <div class="news_border" style="min-height: 30px;
                                    margin-bottom: 10px;
                                    background: #f5f5f5;
                                    padding: 6px;
                                    border: 1px solid #e3e3e3;
                                    box-shadow: 0px 8px 16px 0px #bbb;
                                    border-radius: 4px;">

        <div class="news_content" style="padding: 20px;">
            <div class="heading" style="margin-bottom: 10px; margin-left: 20px; display: inline-block;">
                <h3>Groups: <select id="group" name="group" onchange="change(this)">
                                <option value="0" selected="selected"></option>
                                <option value="<%=groupCourses.getGroup_id().getId()%>"><%=n + ". " + groupCourses.getGroup_id().getName()%></option>
                            </select>
                </h3>
            </div>

            <div id="studentsOfGroup" style="display: none; margin-left: 30px;">
                <h3> Students:
                    <select id="student" onchange="action(this)">
                        <option value="0"></option>
                    <%
                        int m=1;
                        for(GroupUsers student: usersList) {
                            if(student.getGroup_id().getId()==groupCourses.getGroup_id().getId() && student.getUser_id().getRole_id().getId()==Long.valueOf(3)) {
                    %>
                            <option value="<%=student.getUser_id().getId()%>"><%=student.getUser_id().getName() + " " + student.getUser_id().getSurname()%></option>
                    <%
                            }
                        }
                    %>
                    </select>
                </h3>
            </div>

            <div id="action" style="display: none; margin-left: 30px;">
                <h3> Action:
                    <select onchange="variant(this)">
                        <option value="0" selected="selected"></option>
                        <option value="1">Attendance for lesson</option>
                        <option value="2">Mark for lesson</option>
                    </select>
                </h3>
            </div>

            <div id="attendance" style="margin-left: 20px; margin-bottom: 20px; display: none;">
                <form action="/insertAttendance" method="post">
                    <h3>Attendance for lesson:
                        <select name="attendanceState">
                            <option value="1" selected="selected">Is present</option>
                            <option value="0">Absent</option>
                        </select>
                    </h3>
                    <h3>Attendance note:</h3>
                        <textarea name="attendanceNote" style="height: 300px; width: 900px;"></textarea>

                    <input id="at_user_id" type="hidden" name="user_id"/>
                    <input type="hidden" name="lesson_id" value="<%=lesson.getId()%>"/>
                    <input type="submit" style="float: right; margin-top: 10px; margin-left:10px;" value="Save" class="inp_subm">
                </form>
            </div>

            <div id="mark" style="margin-left: 20px; margin-bottom: 20px; display: none;">
                <form action="/insertMark" method="post">
                    <h3>Mark for lesson:
                        <input type="text" name="grade"/>
                    </h3>
                    <h3>Mark note:</h3>
                        <textarea name="markNote" style="height: 300px; width: 900px;"></textarea>
                    <input id="mark_user_id" type="hidden" name="user_id" value="<%=lesson.getId()%>"/>
                    <input type="hidden" name="lesson_id" value="<%=lesson.getId()%>"/>
                    <input type="submit" style="float: right; margin-top: 10px; margin-left:10px;" value="Save" class="inp_subm">
                </form>
            </div>

        </div>

    </div>

    <%
            n++;
        }
    }
    %>

</div>

<script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script>
</body>
</html>