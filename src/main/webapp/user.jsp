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
                <c:choose>
                    <c:when test="${userID == '1'}">
                    <form action="/delSkill" method="POST">
                        <input type="hidden" name="userID" value="${userID}"/>
                        <input type="hidden" name="name" value="${elem.key}"/>
                        <button>Delete</button>
                    </form>
                    </c:when>
                    <c:otherwise>
                        <form action="/endorse" method="POST">
                            <input type="hidden" name="userID" value="${userID}"/>
                            <input type="hidden" name="name" value="${elem.key}"/>
                            <button>Endorse</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

    </c:forEach>

    </tbody>
</table>

<c:if test="${userID == '1'}">
    <form action="/addSkill" method="POST">
        <input type="hidden" name="userID" value="${userID}"/>
        <label for="AddSkill">Add Skill: </label>
        <select name="selectedSkill">
            <c:forEach var="elem" items="${extraSkills}">
            <option value="${elem.key}">${elem.key}</option>
            </c:forEach>
        </select>
        <button>Add</button>
    </form>
</c:if>





</body>
</html>