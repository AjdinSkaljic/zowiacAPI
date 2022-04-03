package com.zowiac.service;

import com.zowiac.model.TextEntity;
import com.zowiac.respository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TextService {

    private final TextRepository textRepository;

    @Autowired
    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }


    public List<TextEntity> findAll() {
        return getTextRepository().findAll();
    }

    public TextEntity find(String id) {
        return getTextRepository().findById(id).orElseGet(null);
    }

    public void delete(String id) {
        getTextRepository().deleteById(id);
    }

    public TextEntity save(TextEntity text) {
        /*if (getNewsRepository().existsById(news.getId())) {
            news.setChangeDate(new Date(System.currentTimeMillis()));
            news.setChangeTime(new Time(System.currentTimeMillis()));
        } else {
            news.setCreateDate(new Date(System.currentTimeMillis()));
            news.setCreateTime(new Time(System.currentTimeMillis()));
        }*/
        getTextRepository().saveAndFlush(text);
        return text;
    }


    public TextRepository getTextRepository() {
        return textRepository;
    }
}
