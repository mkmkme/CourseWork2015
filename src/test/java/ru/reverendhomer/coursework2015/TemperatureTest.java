package ru.reverendhomer.coursework2015;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Alex
 */

public class TemperatureTest {

    @Test
    public void testAverageTemperature() {
        Temperature temperature = new Temperature();
        Double result = temperature.averageTemperature(0.0, new ArrayList<>(Arrays.asList(10.0, 15.0, 20.0)));
        Double expected = 15.0;
        assertEquals("Expected average temperature: " + expected, expected, result);
    }

    @Test
    public void testEmptyListAverageTemperature() {
        Temperature temperature = new Temperature();
        Double prevTemperature = 15.0;
        assertEquals("Expected previous temperature", prevTemperature,
                (Double) temperature.averageTemperature(prevTemperature, new ArrayList<Double>()));
    }

}
