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

        </tr>

    </c:forEach>

    <c:forEach var="elem" items="${skills}">
        <tr>
            <td><span style="font-weight:bold; color:dodgerblue"> <c:out value="${elem.key}"/></span>
            </td>
            <td><c:out value=" : "/></td>
            <td><c:out value="${elem.value}"/></td>
            <td>
                <c:if test="${userID == '1'}">
                <form action="/delSkill" method="">
                    <input type="hidden" name="userID" value="${userID}"/>
                    <input type="hidden" name="name" value="${elem.key}"/>
                    <button>Delete</button>
                </form>
                </c:if>
            </td>
        </tr>

    </c:forEach>

    </tbody>
</table>





</body>
</html>