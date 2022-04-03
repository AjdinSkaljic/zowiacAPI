package com.zowiac.controller;


import com.zowiac.model.AnimalEntity;
import com.zowiac.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(path = "/animals", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AnimalEntity> findAll() {
        return getAnimalService().findAll();
    }


    @GetMapping(path = "/animals/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AnimalEntity find(@PathVariable("id") Long id) {
        return getAnimalService().find(id);
    }


    @RequestMapping(value = {"/animals/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        getAnimalService().delete(id);
    }



    @PostMapping("/animals")
    @ResponseBody
    public AnimalEntity save(@RequestBody AnimalEntity animal) {
        getAnimalService().save(animal);
        return animal;
    }

    @PutMapping("/animals/{id}")
    @ResponseBody
    public AnimalEntity create(@RequestBody AnimalEntity animal) {
        getAnimalService().save(animal);
        return animal;
    }


    public AnimalService getAnimalService() {
        return animalService;
    }
}
