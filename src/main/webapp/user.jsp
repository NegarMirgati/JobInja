<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>user</title>
</head>
<body>


<table>
    <tbody>
    <c:forEach var="elem" items="${content}">
        <tr>
                    <td><span style="font-weight:bold; color:dodgerblue"> <c:out value="${elem.key}"/></span> </td>
                    <td><c:out value=" : "/></td>
                    <td><c:out value="${elem.value}"/></td>
            <c:if test="id"><button>Delete</button></c:if>

        </tr>

    </c:forEach>
    </tbody>
</table>


</body>
</html>