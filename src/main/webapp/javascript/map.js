function initMap() {
    var map = L.map('map').setView([55.755149, 37.617896], 5);
    L.tileLayer('http://a.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Map tiles by CartoDB, under CC BY 3.0.',
        maxZoom: 13
    }).addTo(map);
}
