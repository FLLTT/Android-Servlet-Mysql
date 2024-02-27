<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>所有用户信息</title>
</head>
<body>
<%
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Lty20040615");
    Statement sta = con.createStatement();
    ResultSet res = sta.executeQuery("select * from product");
    while(res.next()){
        int id = res.getInt("id");
        String title = res.getString("title");
        double price = res.getDouble("price");
        String stock = res.getString("stock");
        out.print("id："+id+" title: "+title+" price: "+price+" stock: "+stock+"<br>"); //就算报错也能跑
    }
    con.close();
    sta.close();
    res.close();
%>
</body>
</html>
