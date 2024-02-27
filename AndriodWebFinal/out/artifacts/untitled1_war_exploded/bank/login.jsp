<%--
  Created by IntelliJ IDEA.
  User: 86188
  Date: 2023/11/17
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录界面</title>
</head>
<body>
    <form action="/login.do" method="post">
        <table>
            <tr>
                <td>邮箱：</td>
                <td><input type="email" name="email" size="15"></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="password" name="password" size="15"></td>
            </tr>
        </table>
        <input type="submit" name="提交" >
    </form>
</body>
</html>
