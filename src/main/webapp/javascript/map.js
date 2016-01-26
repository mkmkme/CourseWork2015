
var map;
var heatmap;

function initMap() {
    map = L.map('map').setView([55.755149, 37.617896], 8);
    L.tileLayer('http://a.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Map tiles by CartoDB, under CC BY 3.0.',
        maxZoom: 13
    }).addTo(map);

    //custom size for this example, and autoresize because map style has a percentage width
    heatmap = new L.TileLayer.WebGLHeatMap({size: 40000});

    // dataPoints is an array of arrays: [[lat, lng, intensity]...]
    var dataPoints = [
        [55.555, 39.999, 32],
        [55.555, 39.499, 34],
        [55.555, 38.899, 41],
        [55.555, 38.499, 67],
        [55.555, 37.999, 64],
        [55.555, 37.499, 40],
        [55.555, 36.999, 45]
    ];
    for (var i = 0, len = dataPoints.length; i < len; i++) {
        var point = dataPoints[i];
        heatmap.addDataPoint(point[0],
             point[1],
             point[2],
             point[3]);
    }
    // alternatively, you can skip the for loop and add the whole dataset with heatmap.setData(dataPoints)

    map.addLayer(heatmap);

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
