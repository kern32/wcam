<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Screen History</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" media="screen" href="resources/css/main.css" />
</head>
<body>
<div class="gallery" align="center">
    <br/>
    <div class="thumbnails">
        <c:forEach items="${fileList}" var="item">
            <img onmouseover="preview.src=${item.name}.src" name="${item.name}" src="resources/images/${item.extendedName}" alt=""/>
        </c:forEach>
    </div>
    <br/>
    <div class="preview" >
        <img name="preview" align="right" src="resources/images/${lastItem.extendedName}" alt=""/>
        <p><input type="button"  style="margin:50px;" class="btn btn-primary btn-lg" value="Back to wcam" onclick="location.href = '/wcam';">
    </div>
</div>
</body>
</html>