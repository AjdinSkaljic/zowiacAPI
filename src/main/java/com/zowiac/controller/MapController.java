package com.zowiac.controller;

import com.zowiac.model.AddressPointData;
import com.zowiac.model.AnimalEntity;
import com.zowiac.model.ChartPointData;
import com.zowiac.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping(path = "/public/map/animals", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AnimalEntity> listAnimals() throws Exception {
        return getMapService().listAnimals();
    }


    @GetMapping(path = "/public/map/addressPoints/{animalId}/{from}/{to}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AddressPointData> listAddressPoints(@PathVariable("animalId") Long animalId, @PathVariable("from") @DateTimeFormat(pattern = "yyyyMMdd") Date from, @PathVariable("to") @DateTimeFormat(pattern = "yyyyMMdd") Date to) {
        return getMapService().listAddressPoints(animalId, from, to);
    }

    @GetMapping(path = "/public/map/chartPoints/{animalId}/{from}/{to}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ChartPointData> listChartPoints(@PathVariable("animalId") Long animalId, @PathVariable("from") @DateTimeFormat(pattern = "yyyyMMdd") Date from, @PathVariable("to") @DateTimeFormat(pattern = "yyyyMMdd") Date to) {
        return getMapService().listChartPoints(animalId, from, to);
    }


    public MapService getMapService() {
        return mapService;
    }
}
