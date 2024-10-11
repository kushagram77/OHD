<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
</head>
<body>
    <h2>User Profile</h2>
    <p><strong>Name:</strong> ${user.name}</p>
    <p><strong>Email:</strong> ${user.email}</p>
    <p><strong>Address:</strong> ${user.address}</p>
    <p><strong>Date of Birth:</strong> <fmt:formatDate value="${user.dateOfBirth}" pattern="yyyy-MM-dd"/></p>
    <p><strong>Role:</strong> ${user.role}</p>
    <a href="${pageContext.request.contextPath}/user/logout">Logout</a>
</body>
</html>
