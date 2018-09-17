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
    List<Attachment> attachmentList = (List<Attachment>) request.getAttribute("attachment");
%>
<script>
    function showhide(id) {
        var e = document.getElementById(id);
        e.style.display = (e.style.display == 'block') ? 'none' : 'block';
    }
</script>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Lessons of <%=attachmentList.get(0).getLesson().getCourse().getName()%>: </h2>

    <%
        int n =1;
        if(attachmentList!=null) {
        for(Attachment lessons: attachmentList) {
    %>

    <div class="news_border" style="min-height: 30px;
                                    margin-bottom: 10px;
                                    background: #f5f5f5;
                                    padding: 6px;
                                    border: 1px solid #e3e3e3;
                                    box-shadow: 0px 8px 16px 0px #bbb;
                                    border-radius: 4px;">

        <div class="news_content" style="margin: 20px; padding-bottom: 30px;">
            <div class="heading" style="margin-bottom: 10px; margin-left: 20px;">
                <%
                    if(currentUser.getRole_id().getId()==Long.valueOf(2)) {
                %>
                    <h3><a href="/singlelesson?course_id=<%=lessons.getLesson().getCourse().getId()%>&lesson_id=<%=lessons.getLesson().getId()%>"><%=n + ". " + lessons.getLesson().getTitle()%></a></h3>
                <%
                    }else if(currentUser.getRole_id().getId()==Long.valueOf(3)) {
                %>
                <h3><a href="/singlestudentlesson?lesson_id=<%=lessons.getLesson().getId()%>"><%=n + ". " + lessons.getLesson().getTitle()%></a></h3>
                <%
                    }
                %>
            </div>
            <p><%=lessons.getLesson().getContent()%></p>

            <%
                if(currentUser.getRole_id().getId()==Long.valueOf(2)) {
                    %>
                    <a style="float: right; color: red;" href="javascript:showhide('uniquename<%=n%>')">
                        Attach file
                    </a><br>

                    <div id="uniquename<%=n%>" style="display:none; float: right">
                        <form action="/attachfile" method="post" enctype="multipart/form-data">
                            <input type="file" name="file" style="border: none;" size="50" />
                            <input type="hidden" name="lesson_id" value="<%=lessons.getLesson().getId()%>" />
                            <input type="submit" value="Save" class="inp_subm"/>
                        </form>
                    </div>

                    <%
                }
            %>

            <%
                for(LessonAttachements lessonAtt: lessons.getAttachements()) {
            %>
            <div class="att-list" style="margin: 30px;">
                <a href="/download-attachment/<%=lessonAtt.getId()%>"><%=lessonAtt.getName()%></a>
                <div class="date" style="float: right;">
                    <p style="display: inline-block;">Uploaded at: <%=lessonAtt.getDownload_date().toString()%></p>
                    <%
                        if(currentUser.getRole_id().getId()==Long.valueOf(2)) {
                    %>
                    <form method="post" action="/deleteFile" style="display: inline-block;">
                        <input type="hidden" name="att_id" value="<%=lessonAtt.getId()%>"/>
                        <input type="submit" value="&#10005;" style="border: none; padding: 0; width: auto; background: none; color: red; ">
                    </form>
                    <%
                        }
                    %>
                </div>
            </div>
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