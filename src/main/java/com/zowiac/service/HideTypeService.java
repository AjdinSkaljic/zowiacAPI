package com.zowiac.service;

import com.zowiac.model.HideTypeEntity;
import com.zowiac.respository.HideTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HideTypeService {

    private final HideTypeRepository hideTypeRepository;

    @Autowired
    public HideTypeService(HideTypeRepository hideTypeRepository) {
        this.hideTypeRepository = hideTypeRepository;
    }


    public List<HideTypeEntity> findAll() {
        return getHideTypeRepository().findAll();
    }

    public HideTypeEntity find(Long id) {
        return getHideTypeRepository().findById(id).orElseGet(null);
    }

    public void delete(Long id) {
        getHideTypeRepository().deleteById(id);
    }

    public HideTypeEntity save(HideTypeEntity hideType) {
        getHideTypeRepository().saveAndFlush(hideType);
        return hideType;
    }

    public HideTypeRepository getHideTypeRepository() {
        return hideTypeRepository;
    }
}
