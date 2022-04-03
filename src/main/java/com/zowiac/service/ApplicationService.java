package com.zowiac.service;

import com.zowiac.model.ApplicationData;
import com.zowiac.respository.AnimalRepository;
import com.zowiac.respository.EvidenceTypeRepository;
import com.zowiac.respository.HideTypeRepository;
import com.zowiac.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationService {

    private final AnimalRepository animalRepository;
    private final EvidenceTypeRepository evidenceTypeRepository;
    private final HideTypeRepository hideTypeRepository;
    private final UserRepository userRepository;

    @Autowired
    public ApplicationService(AnimalRepository animalRepository, EvidenceTypeRepository evidenceTypeRepository, HideTypeRepository hideTypeRepository, UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.evidenceTypeRepository = evidenceTypeRepository;
        this.hideTypeRepository = hideTypeRepository;
        this.userRepository = userRepository;
    }

    public ApplicationData loadApplicationData(String userName) {
        ApplicationData applicationData = new ApplicationData();
        applicationData.setEvidenceTypes(getEvidenceTypeRepository().findAll());
        applicationData.setAnimals(getAnimalRepository().findOnlyForReporting());
        applicationData.setHideTypes(getHideTypeRepository().findAll());
        applicationData.setUser(getUserRepository().findById(userName).get());
        return applicationData;

    }

    public AnimalRepository getAnimalRepository() {
        return animalRepository;
    }

    public EvidenceTypeRepository getEvidenceTypeRepository() {
        return evidenceTypeRepository;
    }

    public HideTypeRepository getHideTypeRepository() {
        return hideTypeRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
