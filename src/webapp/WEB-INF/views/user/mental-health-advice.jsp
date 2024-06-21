<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Mental Health Advice</title>
</head>
<body>
    <h1>Mental Health Advice</h1>
    <c:if test="${not empty advice}">
        <p>${advice}</p>
    </c:if>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form action="updateMentalHealthAdvice" method="post">
        <label for="mentalHealthAdvice">Mental Health Advice:</label>
        <textarea id="mentalHealthAdvice" name="mentalHealthAdvice" rows="5">${user.mentalHealthAdvice}</textarea><br>
        <input type="submit" value="Update Advice">
    </form>
</body>
</html>