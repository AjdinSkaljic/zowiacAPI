package com.zowiac.controller;

import com.zowiac.model.FeedbackEntity;
import com.zowiac.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedbackController {
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping(path = "/feedbackDone/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void feedbackDone(@PathVariable("id") Long id) {
        getFeedbackService().feedbackDone(id);
    }

    @GetMapping(path = "/feedback", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<FeedbackEntity> findAll() {
        return getFeedbackService().findAll();
    }


    @RequestMapping(value = {"/feedback/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        getFeedbackService().delete(id);
    }


    public FeedbackService getFeedbackService() {
        return feedbackService;
    }
}
