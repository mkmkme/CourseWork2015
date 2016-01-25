
function initMap() {
    var map = L.map('map').setView([55.755149, 37.617896], 5);
    L.tileLayer('http://a.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Map tiles by CartoDB, under CC BY 3.0.',
        maxZoom: 13
    }).addTo(map);
}

function testMap() {
    var lat = parseFloat(document.getElementById('lat').value);
    var lon = parseFloat(document.getElementById('lon').value);
    var radius = parseInt(document.getElementById('radius').value);
    var clr = document.getElementById('color').value;
    var fillClr = document.getElementById('fillColor').value;
    var circle = L.circle([lat, lon], radius, {
        color: clr,
        fillColor: fillClr, // #f03
        fillOpacity: 0.5
    }).addTo(map);
}
