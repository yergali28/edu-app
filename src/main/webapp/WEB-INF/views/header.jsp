<%@ page import="kz.iitu.dao.SuperAdmins" %>
<%@ page import="kz.iitu.dao.Users" %><%--
  Created by IntelliJ IDEA.
  User: zhakhanyergali
  Date: 11.11.17
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="../resources/main.css">
</head>
<body>

<div class="top">
    <p>Administration panel of StudentsCRM.kz</p>
</div>

<div class="header">
    <div class="header_center">
        <a href="/">
            <div class="wrap1">
                <div class="places">

                </div>
                <p>StudentsCRM</p>
            </div>
        </a>

    </div>

    <div class="dropdown">
        <form method="GET" action="/login">
            <button type="submit" class="dropbtn">Войти</button>
        </form>
    </div>

</div>