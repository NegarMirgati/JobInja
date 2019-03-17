<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>project</title>
</head>
<body>

    <c:out value="${'<b> Project Info </b>'}" escapeXml="false"/>
    <br><br>


<table>
    <tbody>
    <c:forEach var="elem" items="${content}">
        <tr>
            <c:choose>
                <c:when test="${elem.key=='imageURL'}">
                    <img src="<c:url value="${elem.value}"/>" width = "160" height = "160"/>
                    <br />
                </c:when>
                <c:otherwise>
                    <td><span style="font-weight:bold; color:dodgerblue"> <c:out value="${elem.key}"/></span> </td>
                    <td><c:out value=" : "/></td>
                    <td><c:out value="${elem.value}"/></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <c:if test="${hasBadeForThisProject == 'false'}">
    <form action="/Bid" method="POST">
        <label for="bidAmount">Bid Amount:</label>
        <input type="hidden" name="projectID" value="${projectID}"/>
        <input type="number" name="bidAmount">
        <button>Submit</button>
    </form>
    </c:if>

</body>
</html>