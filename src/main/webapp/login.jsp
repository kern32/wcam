<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/login.css">
    <title>Login page</title>
</head>
<body>
<form action="login" method="post">
    <div class="form-group">
        <label for="username">username:</label>
        <input type="text" class="form-control" id="username" name="username">
    </div>
    <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" id="pwd" name="password">
    </div>
    <button type="submit" class="btn btn-primary btn-lg">Submit</button>
</form>
</body>
</html>
