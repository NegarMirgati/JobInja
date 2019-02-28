<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>JSTL!</title>
</head>
<body>

<c:if test="${1 < 2}">
    <c:out value="${'<b> Project Info </b>'}" escapeXml="false"/>
    <br><br>
</c:if>

<table>
    <tbody>
    <c:forEach var="elem" items="${content}">
        <tr>
            <td><span style="font-weight:bold; color:dodgerblue"> <c:out value="${elem.key}"/></span> </td>
            <td><c:out value=" : "/></td>
            <td><c:out value="${elem.value}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>