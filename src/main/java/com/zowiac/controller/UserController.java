package com.zowiac.controller;

import com.zowiac.model.UserEntity;
import com.zowiac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<UserEntity> findAll() {
        return getUserService().findAll();
    }

    @PostMapping("/users")
    @ResponseBody
    public UserEntity save(@RequestBody UserEntity userEntity) throws Exception {
        getUserService().save(userEntity);
        return userEntity;
    }


    @RequestMapping(value = {"/users/{username}/"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("username") String username) {
        getUserService().delete(username);
    }


    @PostMapping(path = "/userLock/{user}/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void lockUser(@PathVariable("user") String user) {
        getUserService().lockUser(user);
    }

    @PostMapping(path = "/userUnlock/{user}/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void unlockUser(@PathVariable("user") String user) {
        getUserService().unlockUser(user);
    }


    public UserService getUserService() {
        return userService;
    }
}
