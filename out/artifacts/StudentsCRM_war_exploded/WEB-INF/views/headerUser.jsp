<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="kz.iitu.dao.Users" %><%--
  Created by IntelliJ IDEA.
  User: zhakhanyergali
  Date: 11.11.17
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Users user = (Users) request.getSession().getAttribute("currentUser");
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="../resources/main.css">
</head>
<body>
<script>

    function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");
    }

    window.onclick = function(event) {
        if (!event.target.matches('.dropbtn')) {

            var dropdowns = document.getElementsByClassName("dropdown-content");
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

<div class="top">
    <p>Administration panel of StudentsCRM.kz</p>
</div>

<div class="header">
    <div class="header_center">
        <a href="/usercourses">
            <div class="wrap1">
                <div class="places">

                </div>
                <p>Мои курсы</p>
            </div>
        </a>
        <a href="/usergroups">
            <div class="wrap1">
                <div class="places">

                </div>
                <p>Мои группы</p>
            </div>
        </a>

    </div>

    <div class="dropdown">
        <button onclick="myFunction()" class="dropbtn"><%=user.getName() + " " + user.getSurname()%></button>
        <div id="myDropdown" class="dropdown-content">
            <a href="/editownprofile">Изменить профиль</a>
            <a href="/logout">Выйти</a>
        </div>
    </div>

</div>