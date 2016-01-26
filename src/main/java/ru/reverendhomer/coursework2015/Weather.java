package ru.reverendhomer.coursework2015;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by Alex on 22.01.2016.
 */
public class Weather {

    private double sigma;

    Weather (double sigma) {
        this.sigma = sigma;
    }

    enum ClimaticZones {Tropical, Hot, Warm, Moderate, Cold, Severe, Polar}

    public double getTemperature(double latitude) {
        Random rand = new Random();
        double temper1 = getTemperature(getClimaticZones(latitude));
        double temper2 = rand.nextDouble() * 10 - 5;
        return temper1 + temper2 + rand.nextGaussian() * sigma;
    }

    private double getTemperature(ClimaticZones zone) {
        int month = (new GregorianCalendar()).get(Calendar.MONTH);
        switch(zone) {
            case Tropical: {
                return 26.0;
            }
            case Hot : {
                return ((month < 4) || (month > 10)) ? 20.0 : 30.0;
            }
            case Warm: {
                return ((month < 4) || (month > 10)) ? 12.0 : 35.0;
            }
            case Moderate: {
                return ((month < 4) || (month > 10)) ? 0.0 : 40.0;
            }
            case Cold: {
                return ((month < 4) || (month > 10)) ? -15.0 : 20.0;
            }
            case Severe: {
                return ((month < 4) || (month > 10)) ? -25.0 : 8.0;
            }
            case Polar: {
                return ((month < 4) || (month > 10)) ? -40.0 : 0.0;
            }
            default: return 0.0;
        }
    }

    private ClimaticZones getClimaticZones(double latitude) {
        latitude = Math.abs(latitude);
        if (latitude >= 0 && latitude < 13) {
            return ClimaticZones.Tropical;
        } else if (latitude < 26) {
            return ClimaticZones.Hot;
        } else if (latitude < 39) {
            return ClimaticZones.Warm;
        } else if (latitude < 52) {
            return ClimaticZones.Moderate;
        } else if (latitude < 65) {
            return ClimaticZones.Cold;
        } else if (latitude < 78) {
            return ClimaticZones.Severe;
        } else if (latitude <= 90) {
            return ClimaticZones.Polar;
        } else {
            return null;
        }
    }
}
