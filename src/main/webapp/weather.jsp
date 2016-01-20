<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
    <script type="text/javascript" src="/javascript/geoip.js"></script>
</head>

<body>
	<div class="form-container">
		<p class="form-name">WEATHER SERVLET lel</p>

		<form onsubmit="return checkWeather();" action="/weatherSubmit" method="get">
			<div class="form-raw">
				<label for="latitudeForm">Latitude: </label>
				<input class="right-float" type="text" id="latitudeForm" name="latitude" autocomplete="off"/>
			</div>
			<div class="form-raw">
				<label for="longitudeForm">Longitude: </label>
				<input class="right-float" type="text" id="longitudeForm" name="longitude" autocomplete="off"/>
			</div>
			<div class="form-raw">
				<label for="weatherForm">Temperature: </label>
				<input class="right-float" type="text" id="weatherForm" name="weather" autocomplete="off"/>
			</div>
			<div class="form-raw">
				<input class="submit-button" type="submit" value="SEND"/>
			</div>
		</form>
		<p id="errorString"></p>
	</div>
</body>
</html>
