package ru.reverendhomer.coursework2015;

import com.google.appengine.api.datastore.*;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Alex on 19.01.2016.
 */
public class DatastoreWork {

    //http://localhost:8080/_ah/admin - datastore viewer as administrator

    private static final double radius = 60000; // radius value in meters
    private static int size;
    private static final double DOUBLE_NOVALUE = -9999.9999;

    DatastoreWork() {
        size = getDatastoreSize();
    }

    //initialization datastore by innitial values and separation the map on the sectors
    public void initializeDatastore() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        //double weather = 0;
        Temperature weather = new Temperature(2.0);
        for(float lat = -90.0f; lat <= 90.0f; lat += 0.5f) {
            for(float lon = -180.0f; lon <= 180.0f; ) {

                Entity ent = new Entity("Weather");
                ent.setProperty("location", new GeoPt(lat, lon));
//                ent.setProperty("previousTemperature", weather.getTemperature(lat));
                ent.setProperty("previousTemperature", DOUBLE_NOVALUE);
                ent.setProperty("temperaturesList", new ArrayList<Double>());
                datastore.put(ent);

                if (lat <= -70.0f || lat >= 70.0f) {
                    lon += 3.0f;
                } else if (lat <= -50.0f || lat >= 50.0f) {
                    lon += 1.0f;
                } else if (lat <= -30.0f || lat >= 30.0f) {
                    lon += 0.6f;
                } else {
                    lon += 0.5f;
                }
            }
        }
        size = getDatastoreSize();
    }

    public Iterable<Entity> getPointInArea(float latitude, float longitude, double radiusIn) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        GeoPt center = new GeoPt(latitude, longitude);
        Query.Filter circle = new Query.StContainsFilter("location", new Query.GeoRegion.Circle(center, radiusIn));
        Query query = new Query("Weather").setFilter(circle);

        /*
        out.println("---Radius : " + radiusIn + "---");
        PreparedQuery pq = datastore.prepare(query);
        for(Entity results : pq.asIterable()) {
            String location = results.getProperty("location").toString();
            String weather = results.getProperty("weather").toString();
            out.println("Location: " + location + "; Weather: " + weather + ";\n");
        }
        out.println("--------------------------------");
        */
        return datastore.prepare(query).asIterable();
    }

    public Iterable<Entity> getPointInArea(float latitude, float longitude) {
        return getPointInArea(latitude, longitude, radius);
    }


    //Put in datastore new temperature in closest entity from "Weather" datastore with help latitude, longitude.
    // weather - add temperature
    public void createEntity(float latitude, float longitude, double weather) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Iterable<Entity> pointsInArea = getPointInArea(latitude, longitude);
        Entity point = pointsInArea.iterator().next(); // Entity from "Weather" datastore
        ArrayList<Double> weatherList = (ArrayList<Double>) point.getProperty("temperaturesList");
        if (weatherList == null) {
            weatherList = new ArrayList<Double>();
        }
        double prev = (double) point.getProperty("previousTemperature");
        if (prev == DOUBLE_NOVALUE || Math.abs(weather - prev) <= 7.0) {
            weatherList.add(weather);
        }
        point.setProperty("temperaturesList", weatherList);
        datastore.put(point);

        /*Queue task = QueueFactory.getDefaultQueue();
        TaskOptions taskOptions = TaskOptions.Builder.withPayload(new CreateTask(latitude, longitude, weather));
        task.add(taskOptions);*/
    }

    public void updateTemperatureFromList() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("Weather");
        PreparedQuery pq = datastore.prepare(query);
        Temperature temperature = new Temperature();
        for (Entity result : pq.asList(FetchOptions.Builder.withChunkSize(500))) {
            ArrayList<Double> weatherList = (ArrayList<Double>) result.getProperty("temperaturesList");
            if(weatherList == null) {
                weatherList = new ArrayList<Double>();
            }
            double newTemperature = temperature.averageTemperature((double)result.getProperty("previousTemperature"), weatherList);
            result.setProperty("previousTemperature", newTemperature);
            result.setProperty("temperaturesList", new ArrayList<Double>());
            datastore.put(result);
        }
    }

    public static ArrayList<WeatherPoint> getWeatherPointList() {
        ArrayList<WeatherPoint> weatherPointsList = new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("Weather");
        PreparedQuery pq = datastore.prepare(query);
        for (Entity result: pq.asList(FetchOptions.Builder.withChunkSize(500))) {
            GeoPt point = (GeoPt) result.getProperty("location");
            double weather = (double) result.getProperty("previousTemperature");
            weatherPointsList.add(new WeatherPoint(point.getLatitude(), point.getLongitude(), weather));
        }
        return weatherPointsList;
    }

    public static ArrayList<WeatherPoint> getActiveWeatherPointList() {
        ArrayList<WeatherPoint> weatherPoints = new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query.Filter filter = new Query.FilterPredicate(
                "previousTemperature",
                Query.FilterOperator.NOT_EQUAL,
                DOUBLE_NOVALUE);
        Query query = new Query("Weather").setFilter(filter);
        PreparedQuery pq = datastore.prepare(query);
        for (Entity result : pq.asList(FetchOptions.Builder.withChunkSize(500))) {
            GeoPt point = (GeoPt) result.getProperty("location");
            double prevTemp = (double) result.getProperty("previousTemperature");
            weatherPoints.add(new WeatherPoint(point.getLatitude(), point.getLongitude(), prevTemp));
        }
        return weatherPoints;
    }

    public static ArrayList<WeatherPoint> getPointListInArea(float latitude, float longitude, double radiusIn) {
        ArrayList<WeatherPoint> wpl = new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        GeoPt center = new GeoPt(latitude, longitude);
        Query.Filter circle = new Query.StContainsFilter("location", new Query.GeoRegion.Circle(center, radiusIn));
        Query.Filter activeFilter = new Query.FilterPredicate("previousTemperature", Query.FilterOperator.NOT_EQUAL, DOUBLE_NOVALUE);
        Query query = new Query("Weather").setFilter(activeFilter).setFilter(circle);
        PreparedQuery pq = datastore.prepare(query);
        for (Entity result : pq.asList(FetchOptions.Builder.withChunkSize(500))) {
            GeoPt point = (GeoPt) result.getProperty("location");
            double prevTemp = (double) result.getProperty("previousTemperature");
            if(point.getLatitude() <= 85.0) {
                wpl.add(new WeatherPoint(point.getLatitude(), point.getLongitude(), prevTemp));
            }
        }
        return wpl;
    }

    private int getDatastoreSize() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        return datastore.prepare(new Query()).asList(FetchOptions.Builder.withChunkSize(500)).size();
    }

    public int getSize() {
        return size;
    }

    //Print all entity from datastore by dint of PrintWriter
    private void datastorePrint(PrintWriter out) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query();
        PreparedQuery p = datastore.prepare(q);
        java.util.List<Entity> list = p.asList(FetchOptions.Builder.withDefaults());
        out.println("\n--------------------\n" + "List size: " + list.size() + "\n");
        for (Entity result : list) {
            java.util.Map map = result.getProperties();
            out.println(result.getKind() + "\n");
            for(Object key : map.keySet()) {
                String value = map.get(key).toString();
                out.println(key.toString() + "\n" + value + "\n");
            }
            out.println("--------------------\n");
        }
        out.println("Datastore end." + "\n--------------------\n");
    }

}
