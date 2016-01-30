package ru.reverendhomer.coursework2015;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

/**
 * Created by ecko on 29.01.16.
 */
public class JavaScriptServlet extends HttpServlet {

    private double celciumToFahrenheit(double tc) {
        return (9 / 5) * tc + 32;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/javascript");
        PrintWriter out = resp.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("var points = [\n");
        List<WeatherPoint> wpl = DatastoreWork.getWeatherPointList();
        for (WeatherPoint wp : wpl) {
            sb.append(String.format(Locale.US, "[%.6f, %.6f, %.2f],\n", wp.getLatitude(), wp.getLongitude(), celciumToFahrenheit(wp.getWeather())));
        }
        sb.append("];\n");
        sb.append("function fillHeatMap() {\n");
        sb.append("fillMap(points);\n");
        sb.append("}\n");
        out.write(sb.toString());
    }
}
