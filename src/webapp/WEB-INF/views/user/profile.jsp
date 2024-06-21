<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h1>User Profile</h1>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form action="updateProfile" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${user.name}" required><br>
        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="${user.age}" required><br>
        <label for="weight">Weight (kg):</label>
        <input type="number" id="weight" name="weight" value="${user.weight}" required><br>
        <label for="height">Height (cm):</label>
        <input type="number" id="height" name="height" value="${user.height}" required><br>
        <label for="fitnessGoal">Fitness Goal:</label>
        <input type="text" id="fitnessGoal" name="fitnessGoal" value="${user.fitnessGoal}" required><br>
        <input type="submit" value="Update Profile">
    </form>
</body>
</html>