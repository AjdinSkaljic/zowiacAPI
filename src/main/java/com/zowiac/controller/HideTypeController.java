package com.zowiac.controller;

import com.zowiac.model.EvidenceTypeEntity;
import com.zowiac.model.HideTypeEntity;
import com.zowiac.service.FilesService;
import com.zowiac.service.HideTypeService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HideTypeController {
    private final HideTypeService hideTypeService;

    public HideTypeController(HideTypeService hideTypeService) {
        this.hideTypeService = hideTypeService;
    }

    @GetMapping(path = "/hideTypes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<HideTypeEntity> findAll() {
        return getHideTypeService().findAll();
    }


    @GetMapping(path = "/hideTypes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public HideTypeEntity find(@PathVariable("id") Long id) {
        return getHideTypeService().find(id);
    }


    @RequestMapping(value = {"/hideTypes/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        getHideTypeService().delete(id);
    }


    @PostMapping("/hideTypes")
    @ResponseBody
    public HideTypeEntity save(@RequestBody HideTypeEntity hideTypeEntity) {
        getHideTypeService().save(hideTypeEntity);
        return hideTypeEntity;
    }

    @PutMapping("/hideTypes/{id}")
    @ResponseBody
    public HideTypeEntity create(@RequestBody HideTypeEntity hideTypeEntity) {
        getHideTypeService().save(hideTypeEntity);
        return hideTypeEntity;
    }

    public HideTypeService getHideTypeService() {
        return hideTypeService;
    }
}
