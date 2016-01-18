function showLocation(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;
    var latitudeForm = document.getElementById('latitudeForm');
    var longitudeForm = document.getElementById('longitudeForm');
    latitudeForm.value = latitude;
    longitudeForm.value = longitude;
    latitudeForm.readOnly = true;
    longitudeForm.readOnly = true;
}

function errorHandler(err) {
    if (err.code == 1) {
        alert("Error: Access is denied!");
    } else if (err.code == 2) {
        alert("Error: Position is unavailable!");
    }
}

function getLocation() {
    if (navigator.geolocation) {
        var options = {
            timeout: 60000 // 60000 ms (60 sec) timeout
        };
        navigator.geolocation.getCurrentPosition(showLocation, errorHandler, options);
    } else {
        alert("Sorry, browser does not support geolocation!");
    }
}

function checkWeather() {
    var errorStr = "The following input is FUCKING INCORRECT:<br>";

    var fail = false;
    var latitudeStr = this.latitudeForm.value;
    var lat = parseFloat(latitudeStr);
    if (!latitudeStr || isNaN(latitudeStr) || lat > 90.0 || lat < -90.0) {
        errorStr += "* Latitude<br>";
        fail = true;
    }
    var longitudeStr = this.longitudeForm.value;
    var lon = parseFloat(longitudeStr);
    if (!longitudeStr || isNaN(longitudeStr) || lon > 180.0 || lon < -180.0) {
        errorStr += "* Longitude<br>";
        fail = true;
    }
    var weatherStr = this.weatherForm.value;
    var w = parseFloat(weatherStr);
    if (!weatherStr || isNaN(weatherStr) || w > 100.0 || w < -100.0) {
        errorStr += "* Temperature<br>";
        fail = true;
    }
    if (fail) {
        document.getElementById('errorString').innerHTML = "Sorry Mario!<br>" + errorStr;
        return false;

    } else {
        document.getElementById('errorString').innerHTML = "Yeeep! It's aaaaaall right!";
        return true;
    }
}

window.onload = getLocation;    // Get coordinates on page load
