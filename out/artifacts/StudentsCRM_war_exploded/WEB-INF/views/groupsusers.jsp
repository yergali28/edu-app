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
<%
    Users currentUser = (Users) request.getSession().getAttribute("currentUser");
    List<Users> usersList = (List<Users>) request.getAttribute("usersList");
    List<Groups> groupsList = (List<Groups>) request.getAttribute("groupsList");
    List<Users> teachersList = (List<Users>) request.getAttribute("teachersList");
    List<Courses> coursesList = (List<Courses>) request.getAttribute("coursesList");
%>

<div class="main_wrap" style="background-color: #FFFFFF;">

    <h2>Назначить Группу-УчащегосяGroupStudents: </h2>
    <form action="/insertGroupUsers" method="post">
        <table>
            <tr>
                <td>
                    <select type="text" name="group" class="input">
                        <option value="" selected="selected"></option>
                        <%
                            for(Groups group: groupsList) {
                        %>
                        <option value="<%=group.getId()%>"><%=group.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
                <td>
                    <select type="text" name="user" class="input">
                        <option value="" selected="selected"></option>
                        <%
                            for(Users user: usersList) {
                        %>
                        <option value="<%=user.getId()%>"><%=user.getName() + user.getSurname()%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Сохранить" class="inp_subm"/>
    </form>
    <br>
    <br>

    <h2>Назначить Группу-Учителя: </h2>
    <form action="/insertGroupUsers" method="post">
        <table>
            <tr>
                <td>
                    <select type="text" name="group" class="input">
                        <option value="" selected="selected"></option>
                        <%
                            for(Groups group: groupsList) {
                        %>
                        <option value="<%=group.getId()%>"><%=group.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
                <td>
                    <select type="text" name="user" class="input">
                        <option value="" selected="selected"></option>
                        <%
                            for(Users user: teachersList) {
                        %>
                        <option value="<%=user.getId()%>"><%=user.getName() + user.getSurname()%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Сохранить" class="inp_subm"/>
    </form>
    <br>
    <br>

    <h2>Назначить Группу-Предметы: </h2>
    <form action="/insertGroupCourses" method="post">
        <table>
            <tr>
                <td>
                    <select type="text" name="group" class="input">
                        <option value="" selected="selected"></option>
                        <%
                            for(Groups group: groupsList) {
                        %>
                        <option value="<%=group.getId()%>"><%=group.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
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
        </table>
        <input type="submit" value="Сохранить" class="inp_subm"/>
    </form>
    <br>
    <br>

    <h2>Группы и члены группы: </h2>

    <table>
        <%
            int n = 1;
            if(groupsList!=null) {
                for(Groups group: groupsList) {
        %>
        <tr>
            <td>
                <h4><%out.print(n); %>. Name: <%out.print(group.getName()); %></h4><br>
            </td>
            <td>
                <center>
                    <form action="/groupsteachers" method="GET" style="display: inline;">
                        <input type="hidden" name="groupId" value="<%=group.getId()%>">
                        <input type="submit" value="Учителя" style="width: 150px;">
                    </form>

                    <form action="/groupstudents" method="GET" style="display: inline;">
                        <input type="hidden" name="groupId" value="<%=group.getId()%>">
                        <input type="submit" value="Студент" style="width: 150px;">
                    </form>

                    <form action="/groupscourses" method="GET" style="display: inline;">
                        <input type="hidden" name="groupId" value="<%=group.getId()%>">
                        <input type="submit" value="Предметы" style="width: 150px;">
                    </form>
                </center>
            </td>
        </tr>
        <%
                    n++;
                }
            }
        %>
    </table>

    <div class="save_button">

    </div>
</div>

</body>
</html>