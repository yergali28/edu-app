<%--
  Created by IntelliJ IDEA.
  User: zhakhanyergali
  Date: 02.11.17
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="resources/style.css">
</head>
<body>

    <div class="login-page">
        <div class="form">
            <form class="login-form" action="/getuser" method="post">
                <input type="text" placeholder="Login" name="login"/>
                <input type="password" placeholder="Password" name="password"/>
                <button>Войти</button>
            </form>
        </div>
    </div>

</body>
</html>
