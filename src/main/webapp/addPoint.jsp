<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>
	<div class="form-container">
		<p class="form-name">Your point was added to the database.
		    You can view the weather map.</p>
        <img src="images/schwarzAdd.jpg" class="form-img"></img>
		<form action="http://localhost:8080/map">
		    <p class="form-name">Come with me if you want to live.</p>
			<div class="form-raw form-name">
                <input class="button open-button" type="submit" value="Open map"/>
            </div>
		</form>
	</div>
</body>
</html>
