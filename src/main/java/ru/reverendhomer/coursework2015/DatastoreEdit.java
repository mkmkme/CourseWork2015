package ru.reverendhomer.coursework2015;

import com.google.appengine.api.datastore.*;
import java.io.PrintWriter;

/**
 * Created by Alex on 19.01.2016.
 */
public class DatastoreEdit {

    //http://localhost:8080/_ah/admin - datastore viewer as administrator

    private static final double radius = 60000; // radius value in meters

    //initialization datastore by innitial values and separation the map on the sectors
    public void initializeDatastore() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        double weather = 0;
        for(float lat = -90.0f; lat <= 90.0f; lat += 0.5f) {
            for(float lon = -180.0f; lon <= 180.0f; ) {

                Entity ent = new Entity("Weather");
                ent.setProperty("location", new GeoPt(lat, lon));
                //ent.setProperty("latitude", String.valueOf(lat));
                //ent.setProperty("longitude", String.valueOf(lon));
                ent.setProperty("weather", weather);
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
    }

    public void getPointInArea(PrintWriter out, float latitude, float longitude, double radiusIn) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        GeoPt center = new GeoPt(latitude, longitude);
        Query.Filter circe = new Query.StContainsFilter("location", new Query.GeoRegion.Circle(center, radiusIn));
        Query query = new Query("Weather").setFilter(circe);

        out.println("---Radius : " + radiusIn + "---");
        PreparedQuery pq = datastore.prepare(query);
        for(Entity results : pq.asIterable()) {
            String location = results.getProperty("location").toString();
            String weather = results.getProperty("weather").toString();
            out.println("Location: " + location + "; Weather: " + weather + ";\n");
        }
        out.println("--------------------------------");
    }

    public void getPointInArea(PrintWriter out, float latitude, float longitude) {
        getPointInArea(out, latitude, longitude, radius);
    }


    //Create and put in datastore new entity "Weather" with latitude, longitude and weather
    public void createEntity(float latitude, float longitude, double weather) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity entity = new Entity("Weather");
        entity.setProperty("location", new GeoPt(latitude, longitude));
        //entity.setProperty("latitude", latitude);
        //entity.setProperty("longitude", longitude);
        entity.setProperty("weather", weather);
        datastore.put(entity);
    }

    public int getDatastoreSize() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        return datastore.prepare(new Query()).asList(FetchOptions.Builder.withDefaults()).size();
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
