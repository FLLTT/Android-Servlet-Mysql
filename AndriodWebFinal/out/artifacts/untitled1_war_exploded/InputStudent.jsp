<%--
  Created by IntelliJ IDEA.
  User: 86188
  Date: 2023/11/17
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息输入页面</title>
</head>
<body>
    <form action="/StudentServlet" method = "post">
        <table>
            <tr>
                <td>学号:</td>
                <td><input type="text" name="stuid" size="15"></td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="name" size="15"></td>
            </tr>
            <tr>
                <td>专业:</td>
                <td><input type="text" name="major" size="15"></td>
            </tr>
        </table>
        <input type="submit" value="提交">
    </form>
</body>
</html>
