package com.zowiac.controller;

import com.zowiac.model.AnimalEntity;
import com.zowiac.model.EvidenceTypeEntity;
import com.zowiac.service.EvidenceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvidenceTypeController {

    private final EvidenceTypeService evidenceTypeService;


    @Autowired
    public EvidenceTypeController(EvidenceTypeService evidenceTypeService) {
        this.evidenceTypeService = evidenceTypeService;
    }

    @GetMapping(path = "/evidenceTypes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<EvidenceTypeEntity> findAll() {
        return getEvidenceTypeService().findAll();
    }


    @GetMapping(path = "/evidenceTypes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public EvidenceTypeEntity find(@PathVariable("id") Long id) {
        return getEvidenceTypeService().find(id);
    }


    @RequestMapping(value = {"/evidenceTypes/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        getEvidenceTypeService().delete(id);
    }


    @PostMapping("/evidenceTypes")
    @ResponseBody
    public EvidenceTypeEntity save(@RequestBody EvidenceTypeEntity evidenceType) {
        getEvidenceTypeService().save(evidenceType);
        return evidenceType;
    }

    @PutMapping("/evidenceTypes/{id}")
    @ResponseBody
    public EvidenceTypeEntity create(@RequestBody EvidenceTypeEntity evidenceType) {
        getEvidenceTypeService().save(evidenceType);
        return evidenceType;
    }


    public EvidenceTypeService getEvidenceTypeService() {
        return evidenceTypeService;
    }
}
