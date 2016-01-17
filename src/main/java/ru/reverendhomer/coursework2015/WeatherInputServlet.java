package ru.reverendhomer.coursework2015;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ecko on 17.01.16.
 */
public class WeatherInputServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

    }

    private boolean isWeatherCorrect(String weatherStr) {
        if (weatherStr.isEmpty()) {
            return false;
        }
        char first = weatherStr.charAt(0);
        if (!Character.isDigit(first) && first != '+' && first != '-') {
            return false;
        }
        boolean wasDot = false;
        for (int i = 1; i < weatherStr.length(); i++) {
            char c = weatherStr.charAt(i);
            if (c == '.') {
                if (wasDot) {
                    return false;
                } else {
                    wasDot = true;
                }
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
