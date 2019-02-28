<%--
  Created by IntelliJ IDEA.
  User: negar
  Date: 2/28/2019 AD
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>users</title>
</head>
<body>

<c:out value="${'<b> All Users Info </b>'}" escapeXml="false"/>
<br><br>

<table>
    <tbody>
    <c:forEach var="map" items="${content}">
        <c:forEach var="elem" items="${map.value}">
            <tr>
                        <td><span style="font-weight:bold; color:dodgerblue"> <c:out value="${elem.key}"/></span> </td>
                        <td><c:out value=" : "/></td>
                        <td><c:out value="${elem.value}"/></td>
            </tr>
        </c:forEach>
        </c:forEach>
    </tbody>
</table>


</body>
</html>
