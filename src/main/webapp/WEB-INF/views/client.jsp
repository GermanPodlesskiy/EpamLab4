<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurant</title>
</head>

<body>
<div>
    <h1>Restaurant</h1>
</div>
<div>
    <div>
        <table border="1">
            <caption>Client info</caption>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Money</th>
                <th>Table Number</th>
            </tr>
            <c:forEach var="client" items="${clients}">
                <tr>
                    <td><c:out value="${client.getFirstName()}"/></td>
                    <td><c:out value="${client.getLastName()}"/></td>
                    <td><c:out value="${client.getMoney()}"/></td>
                    <c:choose>
                        <c:when test="${client.getTable() != null}">
                            <td><c:out value="${client.getTable().getNumber()}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>

                    <td>
                        <button onclick="location.href='${pageContext.servletContext.contextPath}/clients/edit?id=${client.getId()}'">
                            Edit
                        </button>
                        <form method="post" action='<c:url value="/clients/delete" />' style="display:inline;">
                            <input type="hidden" name="id" value="${client.getId()}">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>


<div>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/clients/add'">Add client</button>
</div>

<div>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/'">Log out</button>
</div>
</body>
</html>
