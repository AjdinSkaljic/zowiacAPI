package com.zowiac.controller;

import com.zowiac.model.AnimalEntity;
import com.zowiac.model.ShootingSeasonEntity;
import com.zowiac.service.ShootingSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShootingSeasonController {
    private final ShootingSeasonService shootingSeasonService;

    @Autowired
    public ShootingSeasonController(ShootingSeasonService shootingSeasonService) {
        this.shootingSeasonService = shootingSeasonService;
    }

    @GetMapping(path = "/shootingSeasons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ShootingSeasonEntity> findAll() {
        List<ShootingSeasonEntity> list = getShootingSeasonService().findAll();
        return list;
    }

    @DeleteMapping(value = {"/shootingSeasons/{id}"})
    public void delete(@PathVariable("id") Long id) {
        getShootingSeasonService().delete(id);
    }


    @PutMapping(value = "/shootingSeasons/{id}")
    @ResponseBody
    public ShootingSeasonEntity insert(@PathVariable("id") Long id, @RequestBody ShootingSeasonEntity shootingSeason) {
        if (id == 0)
            shootingSeason.setId(null);
        getShootingSeasonService().save(shootingSeason);
        return shootingSeason;
    }

    @PostMapping("/shootingSeasons")
    @ResponseBody
    public ShootingSeasonEntity save(@RequestBody ShootingSeasonEntity shootingSeason) {
        getShootingSeasonService().save(shootingSeason);
        return shootingSeason;
    }

    public ShootingSeasonService getShootingSeasonService() {
        return shootingSeasonService;
    }
}
