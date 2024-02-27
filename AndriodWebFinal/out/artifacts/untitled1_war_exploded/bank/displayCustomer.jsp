<%@ page import="bank.com.model.Customer" %><%--
  Created by IntelliJ IDEA.
  User: 86188
  Date: 2023/11/17
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示客户信息</title>
</head>
<body>
    <%
        Customer customer = (Customer) session.getAttribute("customer");
    %>
    <p>邮箱： <%=customer.getEmail()%></p>
    <p>密码： <%=customer.getPassword()%></p>
    <p>用户名： <%=customer.getCustName()%></p>
    <p>手机号： <%=customer.getPhone()%></p>

</body>
</html>
