package com.zowiac.controller;

import com.zowiac.model.TextEntity;
import com.zowiac.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TextController {

    private final TextService textService;

    @Autowired
    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping(path = "/text", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<TextEntity> findAll() {
        return getTextService().findAll();
    }


    @GetMapping(path = "/text/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public TextEntity find(@PathVariable("id") String id) {
        return getTextService().find(id);
    }


    @RequestMapping(value = {"/text/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        getTextService().delete(id);
    }


    @PostMapping("/text")
    @ResponseBody
    public TextEntity save(@RequestBody TextEntity text) {
        getTextService().save(text);
        return text;
    }

    @PutMapping("/text/{id}")
    @ResponseBody
    public TextEntity create(@RequestBody TextEntity text) {
        getTextService().save(text);
        return text;
    }

    public TextService getTextService() {
        return textService;
    }
}
