package com.zowiac.service;

import com.zowiac.model.HideEntity;
import com.zowiac.respository.HideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
public class HideService {

    private final HideRepository hideRepository;

    @Autowired
    public HideService(HideRepository hideRepository) {
        this.hideRepository = hideRepository;
    }

    public List<HideEntity> findAll() {
        return getHideRepository().findAll();
    }

    public List<HideEntity> findByUser(String user) {
        return getHideRepository().findByUser(user);
    }

    public void delete(Long id) {
        getHideRepository().deleteById(id);
    }

    public HideEntity save(HideEntity hide) {
        java.util.Date now = new java.util.Date();
        hide.setCreateDate(new Date(now.getTime()));
        hide.setCreateTime(new Time(now.getTime()));
        getHideRepository().saveAndFlush(hide);
        return hide;
    }


    public HideRepository getHideRepository() {
        return hideRepository;
    }
}
