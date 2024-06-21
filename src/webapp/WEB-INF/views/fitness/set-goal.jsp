<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Set Fitness Goal</title>
</head>
<body>
    <h1>Set Fitness Goal</h1>
    <form action="set-goal" method="post">
        <label for="goalType">Goal Type:</label>
        <select id="goalType" name="goalType" required>
            <option value="weight-loss">Weight Loss</option>
            <option value="muscle-gain">Muscle Gain</option>
            <option value="distance">Distance</option>
        </select>
        <br>
        <label for="goalValue">Goal Value:</label>
        <input type="number" id="goalValue" name="goalValue" step="0.01" required>
        <br>
        <button type="submit">Set Goal</button>
    </form>
</body>
</html>




























<!-- <%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Set Fitness Goal</title>
</head>
<body>
    <h1>Set Fitness Goal</h1>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form action="setGoal" method="post">
        <label for="goalType">Goal Type:</label>
        <select id="goalType" name="goalType" required>
            <option value="">Select Goal Type</option>
            <option value="weightLoss">Weight Loss</option>
            <option value="muscleGain">Muscle Gain</option>
            <option value="runningDistance">Running Distance</option>
            <!-- Add more goal types as needed
        </select><br>
        <label for="goalValue">Goal Value:</label>
        <input type="number" id="goalValue" name="goalValue" required><br>
        <label for="targetDate">Target Date:</label>
        <input type="date" id="targetDate" name="targetDate" required><br>
        <input type="submit" value="Set Goal">
    </form>
</body>
</html> -->