package ru.reverendhomer.coursework2015;

import com.google.appengine.api.datastore.*;
import java.io.PrintWriter;

/**
 * Created by Alex on 19.01.2016.
 */
public class DatastoreEdit {

    //http://localhost:8080/_ah/admin - datastore viewer as administrator

    //initialization datastore by innitial values and separation the map on the sectors
    private void initializeDatastore() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        int weather = 0;
        for(double lat = -90.0; lat <= 90.0; lat += 0.5) {
            for(double lon = -180.0; lon <= 180.0; ) {

                Entity ent = new Entity("Weather");
                ent.setProperty("latitude", String.valueOf(lat));
                ent.setProperty("longitude", String.valueOf(lon));
                ent.setProperty("weather", String.valueOf(weather));
                datastore.put(ent);

                if (lat <= -70.0 || lat >= 70.0) {
                    lon += 3.0;
                } else if (lat <= -50.0 || lat >= 50.0) {
                    lon += 1.0;
                } else if (lat <= -30.0 || lat >= 30.0) {
                    lon += 0.6;
                } else {
                    lon += 0.5;
                }
            }
        }
    }

    //Create and put in datastore new entity "Weather" with latitude, longitude and weather
    public void createEntity(String latitude, String longitude, String weather) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity entity = new Entity("Weather");
        entity.setProperty("latitude", latitude);
        entity.setProperty("longitude", longitude);
        entity.setProperty("weather", weather);
        datastore.put(entity);
    }

    public int getDatastoreSize() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        return datastore.prepare(new Query()).asList(FetchOptions.Builder.withDefaults()).size();
    }

    //Print all entity from datastore by dint of PrintWriter
    private void datastorePrint(PrintWriter out, String lat, String lon, String w) {
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
