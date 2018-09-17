<%@ page import="kz.iitu.dao.SuperAdmins" %><%--
  Created by IntelliJ IDEA.
  User: zhakhanyergali
  Date: 11.11.17
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SuperAdmins superAdmin = (SuperAdmins) request.getSession().getAttribute("superadmin");
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
        <a href="/superadmin/superadminindex">
            <div class="wrap1">
                <div class="places">

                </div>
                <p>Супер админы</p>
            </div>
        </a>
        <a href="/superadmin/superadminaddsuperadmin">
            <div class="wrap">
                <div class="places">

                </div>
                <p> супер админа</p>
            </div>
        </a>

        <a href="/superadmin/superadmincompanies">
            <div class="wrap1">
                <div class="news">
                    <p>Компания</p>
                </div>
            </div>
        </a>
        <a href="/superadmin/superadminaddcabinet">
            <div class="wrap">
                <div class="news">
                    <p> компанию</p>
                </div>
            </div>
        </a>

        <a href="/superadmin/superadminadmins">
            <div class="wrap1">
                <div class="news">
                    <p>Админ</p>
                </div>
            </div>
        </a>
        <a href="/superadmin/addadmin">
            <div class="wrap">
                <div class="news">
                    <p> админа</p>
                </div>
            </div>
        </a>
    </div>

    <div class="dropdown">
        <button onclick="myFunction()" class="dropbtn"><%=superAdmin.getLogin()%></button>
        <div id="myDropdown" class="dropdown-content">
            <a href="/superadmin/editownprofile">Изменить профиль</a>
            <a href="/superadmin/logout">Выйти</a>
        </div>
    </div>

</div>