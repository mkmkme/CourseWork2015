function showLocation(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;
    var latitudeForm = document.getElementById('latitudeForm');
    var longitudeForm = document.getElementById('longitudeForm');
    latitudeForm.value = latitude;
    longitudeForm.value = longitude;
    latitudeForm.readOnly = true;
    longitudeForm.readOnly = true;
    document.getElementById('mainMap').style.visibility = 'hidden';
}

function errorHandler(err) {
    if (err.code == 1) {
        alert("Error: Access is denied!");
    } else if (err.code == 2) {
        alert("Error: Position is unavailable!");
    }
    document.getElementById('mainMap').style.visibility = 'visible';
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
    var invalidValuesList = "<ul>";

    var fail = false;
    var latitudeStr = this.latitudeForm.value;
    var lat = parseFloat(latitudeStr);
    if (!latitudeStr || isNaN(latitudeStr) || lat > 90.0 || lat < -90.0) {
        invalidValuesList += "<li>Latitude</li>";
        fail = true;
    }
    var longitudeStr = this.longitudeForm.value;
    var lon = parseFloat(longitudeStr);
    if (!longitudeStr || isNaN(longitudeStr) || lon > 180.0 || lon < -180.0) {
        invalidValuesList += "<li>Longitude</li>";
        fail = true;
    }
    var weatherStr = this.weatherForm.value;
    var w = parseFloat(weatherStr);
    if (!weatherStr || isNaN(weatherStr) || w > 100.0 || w < -100.0) {
        invalidValuesList += "<li>Temperature</li>";
        fail = true;
    }
    invalidValuesList += "</ul>";
    if (fail) {
        var errorMsg = "F**K YOU ASSHOLE!<br>";
        errorMsg += "GET BACK AND RETURN WITH THIS CORRECT VALUES:<br>";
        document.getElementById('statusString').innerHTML = errorMsg + invalidValuesList;
        return false;

    } else {
        document.getElementById('statusString').innerHTML = "Yeeep! It's aaaaaall right!";
        return true;
    }
}

function onMapBtnClicked() {
    var m = document.getElementById('mainMap');
    if (m.style.visibility === 'hidden') {
        m.style.visibility = 'visible';
    } else {
        m.style.visibility = 'hidden';
    }
}

// Map script
var map;

function initMap() {
    map = L.map('mainMap').setView([55.755149, 37.617896], 8);
        L.tileLayer('http://a.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {
            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Map tiles by CartoDB, under CC BY 3.0.',
            maxZoom: 13
        }).addTo(map);

    map.on('click', function(e) {
        document.getElementById('latitudeForm').value = e.latlng.lat;
        document.getElementById('longitudeForm').value = e.latlng.lng;
    });
}


window.onload = function() {
    getLocation();    // Get coordinates on page load
    initMap();
}
