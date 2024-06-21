<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Track Habits</title>
</head>
<body>
    <h1>Track Habits</h1>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form action="trackHabits" method="post">
        <c:forEach var="habit" items="${habits}">
            <div>
                <label for="habit_${habit.id}">${habit.name}</label>
                <input type="checkbox" id="habit_${habit.id}" name="completedHabits" value="${habit.id}">
            </div>
        </c:forEach>
        <input type="submit" value="Track Habits">
    </form>
</body>
</html>