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
<p>THIS IS WEATHER SERVLET lel</p>

<form onsubmit="return checkWeather();" action="/weatherSubmit" method="get">
    <div>
        Latitude:
        <input type="text" id="latitudeForm" name="latitude" autocomplete="off"/>
    </div>
    <div>
        Longitude:
        <input type="text" id="longitudeForm" name="longitude" autocomplete="off"/>
    </div>
    <div>
        Temperature:
        <input type="text" id="weatherForm" name="weather" autocomplete="off"/>
    </div>
    <div>
        <input type="submit" value="Send"/>
    </div>
</form>
<p id="errorString"></p>

</body>
</html>
