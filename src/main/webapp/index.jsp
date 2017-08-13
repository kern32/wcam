<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Webcam</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2><a href="http://${ip}:3456">${ip}</a></h2>
    <br/><br/><br/>
    <p><input type="button" class="btn btn-primary btn-lg" value="Screen" onclick="location.href = 'screen.overview';">
    <br/>
    <h2>Last Access</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <c:forEach items="${columns}" var="item">
                <th>${item}</th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ipList}" var="log">
            <tr>
                <td><c:out value="${log.time}"/></td>
                <td><c:out value="${log.country}"/></td>
                <td><c:out value="${log.city}"/></td>
                <td><c:out value="${log.ip}"/></td>
                <td><c:out value="${log.browser}"/></td>
                <td><c:out value="${log.os}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
