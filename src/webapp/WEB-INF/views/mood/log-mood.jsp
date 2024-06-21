<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Log Mood</title>
</head>
<body>
    <h1>Log Mood</h1>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form action="logMood" method="post">
        <label for="mood">Mood:</label>
        <select id="mood" name="mood" required>
            <option value="">Select Mood</option>
            <option value="happy">Happy</option>
            <option value="sad">Sad</option>
            <option value="stressed">Stressed</option>
            <option value="anxious">Anxious</option>
            <option value="calm">Calm</option>
            <!-- Add more mood options as needed -->
        </select><br>
        <label for="moodNotes">Notes:</label>
        <textarea id="moodNotes" name="moodNotes"></textarea><br>
        <input type="submit" value="Log Mood">
    </form>
</body>
</html>