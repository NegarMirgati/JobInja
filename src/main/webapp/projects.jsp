<%--
  Created by IntelliJ IDEA.
  User: negar
  Date: 3/1/2019 AD
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Projects</title>
    <style>
        table {
            text-align: center;
            margin: 0 auto;
        }
        td {
            min-width: 300px;
            margin: 5px 5px 5px 5px;
            text-align: center;
        }
    </style>
</head>
<body>

<c:out value="${'<b> All Projects Info </b>'}" escapeXml="false"/>
<br><br>


<table>
        <tr>
            <th> <span style="font-weight:bold; color:dodgerblue"> imageUrl </span> </th>
            <th> <span style="font-weight:bold; color:dodgerblue"> id </span> </th>
            <th> <span style="font-weight:bold; color:dodgerblue"> title </span> </th>
            <th> <span style="font-weight:bold; color:dodgerblue"> Budget </span> </th>
        </tr>

    <c:forEach var="map" items="${content}">
        <tr>
            <c:forEach var="elem" items="${map.value}">
                <td><c:out value="${elem.value}"/></td>
            </c:forEach>
        </tr>
    </c:forEach>

</table>


</body>
</html>
