<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage Habits</title>
</head>
<body>
    <h1>Manage Habits</h1>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <h2>Add New Habit</h2>
    <form action="addHabit" method="post">
        <label for="habitName">Habit Name:</label>
        <input type="text" id="habitName" name="habitName" required><br>
        <label for="habitFrequency">Frequency:</label>
        <select id="habitFrequency" name="habitFrequency" required>
            <option value="">Select Frequency</option>
            <option value="daily">Daily</option>
            <option value="weekly">Weekly</option>
            <option value="monthly">Monthly</option>
        </select><br>
        <label for="habitReminder">Reminder Time:</label>
        <input type="time" id="habitReminder" name="habitReminder"><br>
        <input type="submit" value="Add Habit">
    </form>
    <h2>Existing Habits</h2>
    <table>
        <tr>
            <th>Habit Name</th>
            <th>Frequency</th>
            <th>Reminder</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="habit" items="${habits}">
            <tr>
                <td>${habit.name}</td>
                <td>${habit.frequency}</td>
                <td>${habit.reminder}</td>
                <td>
                    <a href="editHabit?id=${habit.id}">Edit</a>
                    <a href="deleteHabit?id=${habit.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>