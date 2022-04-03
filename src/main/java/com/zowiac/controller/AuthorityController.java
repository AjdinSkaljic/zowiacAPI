package com.zowiac.controller;

import com.zowiac.model.AuthorityEntity;
import com.zowiac.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorityController {
    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }


    @GetMapping(path = "/authorities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AuthorityEntity> findAll() {
        return getAuthorityService().findAll();
    }

    @RequestMapping(value = {"/authorities/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        getAuthorityService().delete(id);
    }


    @PostMapping("/authorities")
    @ResponseBody
    public AuthorityEntity save(@RequestBody AuthorityEntity authority) {
        getAuthorityService().save(authority);
        return authority;
    }

    @PutMapping("/authorities/{id}")
    @ResponseBody
    public AuthorityEntity create(@RequestBody AuthorityEntity authority) {
        getAuthorityService().save(authority);
        return authority;
    }


    public AuthorityService getAuthorityService() {
        return authorityService;
    }
}
