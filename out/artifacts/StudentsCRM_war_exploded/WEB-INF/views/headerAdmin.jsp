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
        <a href="/adminindex">
            <div class="wrap1">
                <div class="places">

                </div>
                <p>Пользователи</p>
            </div>
        </a>
        <a href="/adminadduser">
            <div class="wrap">
                <div class="places">

                </div>
                <p> пользователя</p>
            </div>
        </a>
        <a href="/admincourses">
            <div class="wrap1">
                <div class="places">

                </div>
                <p>Предметы</p>
            </div>
        </a>
        <a href="/adminaddcourse">
            <div class="wrap">
                <div class="places">

                </div>
                <p> предмет</p>
            </div>
        </a>
        <a href="/adminlessons">
            <div class="wrap1">
                <div class="places">

                </div>
                <p>Уроки</p>
            </div>
        </a>
        <a href="/adminaddlesson">
            <div class="wrap">
                <div class="places">

                </div>
                <p> урок</p>
            </div>
        </a>
        <a href="/admingroups">
            <div class="wrap1">
                <div class="places">

                </div>
                <p>Группы</p>
            </div>
        </a>
        <a href="/adminaddgroup">
            <div class="wrap">
                <div class="places">

                </div>
                <p> группу</p>
            </div>
        </a>
        <a href="/adminnews">
            <div class="wrap1">
                <div class="places">

                </div>
                <p>Новости</p>
            </div>
        </a>
        <a href="/adminaddnews">
            <div class="wrap">
                <div class="places">

                </div>
                <p> новость</p>
            </div>
        </a>
        <a href="/groupsusers">
            <div class="wrap">
                <div class="places">

                </div>
                <p>Назначения</p>
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