<%@ page import="com.ltyJavaWeb.Student" %><%--
  Created by IntelliJ IDEA.
  User: 86188
  Date: 2023/11/17
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
</head>
<body>
    <%
        Student student = (Student) session.getAttribute("student");
    %>
  <h4>学号：<%=student.getStuid()%></h4>
  <h4>姓名：<%=student.getName()%></h4>
  <h4>专业：<%=student.getMajor()%></h4>
</body>
</html>
