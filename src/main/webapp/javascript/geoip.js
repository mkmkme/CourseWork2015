function showLocation(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;
    document.getElementById('latitude_p').innerHTML = 'Latitude: ' + latitude;
    document.getElementById('longitude_p').innerHTML = 'Longitude: ' + longitude;
}

function errorHandler(err) {
    if (err.code == 1) {
        alert("Error: Access is denied!");
    } else if(err.code == 2) {
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

window.onload = getLocation;    // Get coordinates on page load
