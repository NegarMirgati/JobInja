<%--
  Created by IntelliJ IDEA.
  User: negar
  Date: 3/1/2019 AD
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% response.setStatus(404); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>404 error</title>
</head>
<body>
<p>< c:out value="${exception.message}"/> </p>
</body>
</html>
