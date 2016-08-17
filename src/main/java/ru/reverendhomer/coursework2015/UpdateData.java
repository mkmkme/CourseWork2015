package ru.reverendhomer.coursework2015;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 27.01.2016.
 */
public class UpdateData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatastoreWork datastoreWork = new DatastoreWork();
        long time = System.currentTimeMillis();
        log("Start update data.");
        datastoreWork.updateTemperatureFromList();
        log("End update data. Time : " + (System.currentTimeMillis() - time) + " millis.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
