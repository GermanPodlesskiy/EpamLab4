<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div>
            <h2>Add client</h2>
        </div>

        <form method="post">
            <div>
                <label for="firstName">First Name:</label>
                <input id="firstName" type="text" name="firstName">
            </div>

            <div>
                <label for="lastName">Last Name:</label>
                <input id="lastName" type="text" name="lastName">
            </div>

            <div>
                <label for="money">Money:</label>
                <input id="money" type="number"  min="0" name="money">
            </div>
            <div>
                <label>Select table number</label>
            </div>
            <select name="table">
                <option value="0">None</option>
                <c:forEach var="table" items="${tables}">
                    <option value="${table.getId()}">${table.getNumber()}</option>
                </c:forEach>
            </select>

            <div>
                <button type="submit">Submit</button>
            </div>
        </form>
    </div>
</div>

<div>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/clients'">Cancel</button>
</div>
</body>
</html>
