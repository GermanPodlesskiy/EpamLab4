<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Restaurant</title>
</head>
<body>

<div>
  <h1>Restaurant</h1>
</div>
<div>
  <div>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/login'">Sign in</button>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/register'">Sign up</button>
  </div>
</div>
</body>
</html>