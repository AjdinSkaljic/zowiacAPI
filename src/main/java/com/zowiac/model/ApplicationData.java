package com.zowiac.model;

import java.util.List;

public class ApplicationData {
    private List<EvidenceTypeEntity> evidenceTypes;
    private List<HideTypeEntity> hideTypes;
    private List<AnimalEntity> animals;
    private UserEntity user;

    public List<EvidenceTypeEntity> getEvidenceTypes() {
        return evidenceTypes;
    }

    public void setEvidenceTypes(List<EvidenceTypeEntity> evidenceTypes) {
        this.evidenceTypes = evidenceTypes;
    }

    public List<AnimalEntity> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalEntity> animals) {
        this.animals = animals;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<HideTypeEntity> getHideTypes() {
        return hideTypes;
    }

    public void setHideTypes(List<HideTypeEntity> hideTypes) {
        this.hideTypes = hideTypes;
    }
}
