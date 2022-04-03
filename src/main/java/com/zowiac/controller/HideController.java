package com.zowiac.controller;

import com.zowiac.model.HideEntity;
import com.zowiac.service.HideService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HideController {
    private final HideService hideService;

    public HideController(HideService hideService) {
        this.hideService = hideService;
    }

    @GetMapping(path = "/hides", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<HideEntity> findAll() {
        return getHideService().findAll();
    }


    @PostMapping("/hides")
    @ResponseBody
    public HideEntity save(@RequestBody HideEntity hide) {
        getHideService().save(hide);
        return hide;
    }

    public HideService getHideService() {
        return hideService;
    }
}
