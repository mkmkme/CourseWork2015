package ru.reverendhomer.coursework2015;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by ecko on 17.01.16.
 */
public class WeatherInputServlet extends HttpServlet {

    private static final DatastoreWork data = new DatastoreWork();

    private void printParams(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("There is all params:");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            out.write(paramName);
            out.write("\n");
            String[] paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                out.write("\t" + paramValue);
                out.write("\n");
            }
        }
        out.println("----");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        /*
        resp.setContentType("text/plain");
        resp.getWriter().println("Hi, welcome to submit GET!");
        //printParams(req, resp);
        */
        resp.sendRedirect("/addPoint.jsp");
        String lat = req.getParameter("latitude");
        String lon = req.getParameter("longitude");
        String weather = req.getParameter("weather");

        //resp.getWriter().println("Your parameters:\nLat: " + lat + "\nLon: " + lon + "\nWeather: " + weather);
        //RequestDispatcher view = req.getRequestDispatcher("http://localhost:8080/addPoint");
        //view.forward(req, resp);

        //DatastoreWork data = new DatastoreWork();
        if (data.getSize() == 0) {
            log("Start initialize Datastore.");
            data.initializeDatastore();
        }
        data.createEntity(Float.parseFloat(lat), Float.parseFloat(lon), Float.parseFloat(weather));
        //data.createEntity(Float.parseFloat(lat), Float.parseFloat(lon), Double.parseDouble(w));
        //data.getPointInArea(resp.getWriter(), Float.parseFloat(lat), Float.parseFloat(lon));
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("Hi, welcome to submit POST!");

        printParams(req, resp);

        String lat = req.getParameter("latitude");
        String lon = req.getParameter("longitude");
        String w = req.getParameter("weather");
        out.println("Lat: " + lat + "\nLon: " + lon + "\nWeather: " + w);
    }
}
