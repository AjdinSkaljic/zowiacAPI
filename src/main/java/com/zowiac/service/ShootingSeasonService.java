package com.zowiac.service;

import com.zowiac.model.ShootingSeasonEntity;
import com.zowiac.respository.AnimalRepository;
import com.zowiac.respository.ShootingSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class ShootingSeasonService {

    private final AnimalRepository animalRepository;
    private final ShootingSeasonRepository shootingSeasonRepository;

    @Autowired
    public ShootingSeasonService(AnimalRepository animalRepository, ShootingSeasonRepository shootingSeasonRepository) {
        this.animalRepository = animalRepository;
        this.shootingSeasonRepository = shootingSeasonRepository;
    }

    public List<ShootingSeasonEntity> findAll() {
        return getShootingSeasonRepository().findAll();
    }

    public List<ShootingSeasonEntity> findByBundesland(String bundesland) {
        List<ShootingSeasonEntity> shootingSeasonList = getShootingSeasonRepository().findByBundesland(bundesland);

        if (shootingSeasonList != null)
            shootingSeasonList.forEach(shootingSeason -> {
                if (shootingSeason.getAnimal().getParentId() != null && shootingSeason.getAnimal().getParentAnimal() == null) {
                    shootingSeason.getAnimal().setParentAnimal(getAnimalRepository().findById(shootingSeason.getAnimal().getParentId()).get());
                }

            });

        shootingSeasonList.sort(Comparator.comparing(ShootingSeasonEntity::getAnimalName));

        return shootingSeasonList;
    }


    public ShootingSeasonEntity find(Long id) {
        return getShootingSeasonRepository().findById(id).orElseGet(null);
    }

    public void delete(Long id) {
        getShootingSeasonRepository().deleteById(id);
    }

    public ShootingSeasonEntity save(ShootingSeasonEntity shootingSeason) {
        //AnimalEntity animal = getAnimalRepository().getOne(shootingSeason.getAnimalId());
        //shootingSeason.setAnimal(animal);
        getShootingSeasonRepository().saveAndFlush(shootingSeason);
        return shootingSeason;
    }


    public ShootingSeasonRepository getShootingSeasonRepository() {
        return shootingSeasonRepository;
    }

    public AnimalRepository getAnimalRepository() {
        return animalRepository;
    }
}
