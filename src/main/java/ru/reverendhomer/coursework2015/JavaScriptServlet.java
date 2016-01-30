package ru.reverendhomer.coursework2015;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ecko on 29.01.16.
 */
public class JavaScriptServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/javascript");
        PrintWriter out = resp.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("function fillHeatMap() {");
        sb.append("var points = [");
        List<WeatherPoint> wpl = DatastoreWork.getWeatherPointList();
        for (WeatherPoint wp : wpl) {
            sb.append(String.format("[%f, %f, %f],", wp.getLatitude(), wp.getLongitude(), wp.getWeather()));
        }
        sb.append("];");
        sb.append("fillMap(points);");
        sb.append("}");
        out.write(sb.toString());
    }
}
