<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log Fitness Activity</title>
</head>
<body>
    <h1>Log Fitness Activity</h1>
    <form action="log-activity" method="post">
        <label for="activityType">Activity Type:</label>
        <select id="activityType" name="activityType" required>
            <option value="running">Running</option>
            <option value="walking">Walking</option>
            <option value="cycling">Cycling</option>
            <option value="workout">Workout</option>
        </select>
        <br>
        <label for="duration">Duration (minutes):</label>
        <input type="number" id="duration" name="duration" required>
        <br>
        <label for="distance">Distance (km):</label>
        <input type="number" id="distance" name="distance" step="0.01" required>
        <br>
        <label for="intensity">Intensity:</label>
        <select id="intensity" name="intensity" required>
            <option value="low">Low</option>
            <option value="moderate">Moderate</option>
            <option value="high">High</option>
        </select>
        <br>
        <label for="caloriesBurned">Calories Burned:</label>
        <input type="number" id="caloriesBurned" name="caloriesBurned" required>
        <br>
        <button type="submit">Log Activity</button>
    </form>
</body>
</html>

























<!-- <%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Log Fitness Activity</title>
</head>
<body>
    <h1>Log Fitness Activity</h1>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form action="logActivity" method="post">
        <label for="activityType">Activity Type:</label>
        <select id="activityType" name="activityType" required>
            <option value="">Select Activity Type</option>
            <option value="workout">Workout</option>
            <option value="run">Run</option>
            <option value="walk">Walk</option>
            <!-- Add more activity types as needed -->
        </select><br>
        <label for="duration">Duration (minutes):</label>
        <input type="number" id="duration" name="duration" required><br>
        <label for="distance">Distance (km):</label>
        <input type="number" id="distance" name="distance"><br>
        <label for="intensity">Intensity:</label>
        <select id="intensity" name="intensity" required>
            <option value="">Select Intensity</option>
            <option value="low">Low</option>
            <option value="moderate">Moderate</option>
            <option value="high">High</option>
        </select><br>
        <input type="submit" value="Log Activity">
    </form> -->