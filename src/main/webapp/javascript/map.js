
var map;
var heatmap;

function initMap() {
    map = L.map('map').setView([59.97305679999999, 30.302458400000003], 8);
    L.tileLayer('http://a.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Map tiles by CartoDB, under CC BY 3.0.',
        maxZoom: 13
    }).addTo(map);

    //custom size for this example, and autoresize because map style has a percentage width
    heatmap = new L.TileLayer.WebGLHeatMap({size: 100000});
    fillHeatMap();
    map.addLayer(heatmap);

}

function fillMap(dataPoints) {
    // dataPoints is an array of arrays: [[lat, lng, intensity]...]
    for (var i = 0, len = dataPoints.length; i < len; i++) {
        var point = dataPoints[i];
        heatmap.addDataPoint(point[0], point[1], point[2] + 15);
    }
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
