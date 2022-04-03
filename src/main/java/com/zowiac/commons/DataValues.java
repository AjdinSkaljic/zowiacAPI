package com.zowiac.commons;

import java.util.HashMap;
import java.util.Map;

public class DataValues {

    private static Map<String, String> populationMap, forecastPopulationMap;

    public static Map<String, String> getPopulationMap() {
        if (populationMap == null) {
            populationMap = new HashMap<>();
            populationMap.put("30", "sehr häufig");
            populationMap.put("25", "häufig");
            populationMap.put("20", "mäßig häufig");
            populationMap.put("15", "selten");
            populationMap.put("10", "sehr selten");
            populationMap.put("5", "extrem selten");
            populationMap.put("0", "ausgestorben oder verschollen");
        }
        return populationMap;
    }

    public static Map<String, String> getForecastPopulationMap() {
        if (forecastPopulationMap == null) {
            forecastPopulationMap = new HashMap<>();
            forecastPopulationMap.put("30", "Rückgang, im Ausmaß unbekannt");
            forecastPopulationMap.put("25", "sehr starker Rückgang");
            forecastPopulationMap.put("20", "starker Rückgang");
            forecastPopulationMap.put("15", "mäßiger Rückgang");
            forecastPopulationMap.put("10", "stabil");
            forecastPopulationMap.put("5", "deutliche Zunahme");
            forecastPopulationMap.put("0", "Daten ungenügend");
        }
        return forecastPopulationMap;
    }


}
