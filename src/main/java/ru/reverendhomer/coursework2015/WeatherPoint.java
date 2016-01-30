package ru.reverendhomer.coursework2015;

/**
 * Created by ecko on 29.01.16.
 */
public class WeatherPoint /* implements DeferredTask */{

    public float latitude;
    public float longitude;
    public double weather;

    WeatherPoint(float latitude, float longitude, double weather) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.weather = weather;
    }

    public double getWeather() {
        return weather;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
        /*
        @Override
        public void run() {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            Iterable<Entity> pointsInArea = getPointInArea(latitude, longitude);
            Entity point = pointsInArea.iterator().next(); // Entity from "Weather" datastore
            ArrayList<Double> weatherList = (ArrayList<Double>) point.getProperty("temperaturesList");
            if (weatherList == null) {
                weatherList = new ArrayList<Double>();
            }
            if(Math.abs(weather - ((double)point.getProperty("previousTemperature"))) <= 7.0) {
                weatherList.add(weather);
            }
            point.setProperty("temperaturesList", weatherList);
            datastore.put(point);
            System.out.println("New temperature add to list.");
        }
        */
}