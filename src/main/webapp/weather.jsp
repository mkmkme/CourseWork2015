<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>WEATHERMINATOR!</title>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
    <link type="text/css" rel="stylesheet" href="/stylesheets/leaflet.css"/>
    <script type="text/javascript" src="/javascript/leaflet.js"></script>
    <script type="text/javascript" src="/javascript/geomap.js"></script>
    <script type="text/javascript" src="/javascript/geoip.js"></script>
</head>

<body>
	<div class="form-container">
		<p class="form-name">I need your latitude, your longitude and your temperature</p>
        <img src="images/schwarz.jpg" class="form-img"></img>
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
	</div>
	<p id="statusString"></p>
	<input class="submit-button" type="button" value="Show/Hide map" onclick="onMapBtnClicked()"/>
	<div id="mainMap"></div>

</body>
</html>
