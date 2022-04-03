package com.zowiac.service;

import com.zowiac.model.FeedbackEntity;
import com.zowiac.respository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

import java.sql.Time;
import java.util.List;

@Component
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }


    public void feedbackDone(Long id) {
        FeedbackEntity feedbackEntity = getFeedbackRepository().findById(id).get();
        feedbackEntity.setStatus(FeedbackEntity.STATUS_DONE);
        getFeedbackRepository().saveAndFlush(feedbackEntity);
    }

    public List<FeedbackEntity> findAll() {
        return getFeedbackRepository().findAll();
    }


    public void saveFeedback(String text, String userName) {
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setText(text);
        feedback.setUserName(userName);
        feedback.setStatus(FeedbackEntity.STATUS_NEW);
        java.util.Date now = new java.util.Date();
        feedback.setCreateDate(new Date(now.getTime()));
        feedback.setCreateTime(new Time(now.getTime()));
        getFeedbackRepository().saveAndFlush(feedback);
    }

    public void delete(Long id) {
        getFeedbackRepository().deleteById(id);
    }

    public FeedbackRepository getFeedbackRepository() {
        return feedbackRepository;
    }
}
