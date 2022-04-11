package com.zowiac.service;

import com.zowiac.model.AnimalEntity;
import com.zowiac.respository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


    public List<AnimalEntity> findOnlyReporting(String reportType) {
        List<AnimalEntity> animalEntities;
        if (reportType == null)
            animalEntities = getAnimalRepository().findOnlyForReporting();
        else {
            animalEntities = getAnimalRepository().findByReportType(reportType);
            animalEntities = animalEntities.stream().filter(animal-> animal.getParentId() == null).collect(Collectors.toList());
        }
        setParentAniamls(animalEntities);

        animalEntities.sort(Comparator.comparing(AnimalEntity::getFullname));
        return animalEntities;
    }


    public List<AnimalEntity> findAll() {
        List<AnimalEntity> animalEntities = getAnimalRepository().findAll();
        setParentAniamls(animalEntities);
        animalEntities.sort(Comparator.comparing(AnimalEntity::getFullname));
        return animalEntities;
    }

    private void setParentAniamls(List<AnimalEntity> list) {
        Map<Long, AnimalEntity> animalMap = new HashMap<>();
        List<AnimalEntity> unassignedList = new ArrayList<>();
        for (AnimalEntity animal : list) {
            animalMap.put(animal.getId(), animal);
            if (animal.getParentId() != null) {
                AnimalEntity parentAnimal = animalMap.get(animal.getParentId());
                if (parentAnimal != null)
                    animal.setParentAnimal(parentAnimal);
                else
                    unassignedList.add(animal);
            }
        }


        for (AnimalEntity animal : unassignedList) {
            animal.setParentAnimal(animalMap.get(animal.getParentId()));
        }

    }

    public AnimalEntity find(Long id) {
        return getAnimalRepository().findById(id).orElseGet(null);
    }

    public void delete(Long id) {
        getAnimalRepository().deleteById(id);
    }

    public AnimalEntity save(AnimalEntity animal) {
        getAnimalRepository().saveAndFlush(animal);
        return animal;
    }


    public AnimalRepository getAnimalRepository() {
        return animalRepository;
    }
}
