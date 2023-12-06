package com.zowiac.service;

import com.zowiac.model.*;
import com.zowiac.respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class ReportService {

    private final ReportRepository reportRepository;
    private final AnimalRepository animalRepository;
    private final EvidenceTypeRepository evidenceTypeRepository;
    private final FilesRepository filesRepository;
    private final HideRepository hideRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, AnimalRepository animalRepository,
                         EvidenceTypeRepository evidenceTypeRepository, FilesRepository filesRepository,
                         HideRepository hideRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.animalRepository = animalRepository;
        this.evidenceTypeRepository = evidenceTypeRepository;
        this.filesRepository = filesRepository;
        this.hideRepository = hideRepository;
        this.userRepository = userRepository;
    }

    public List<ReportEntity> findAllByUser(String user) {
        List<AnimalEntity> animalEntityList = getAnimalRepository().findAll();
        Map<Long, AnimalEntity> animalMap = new HashMap<>();
        animalEntityList.forEach(animalEntity -> animalMap.put(animalEntity.getId(), animalEntity));

        List<EvidenceTypeEntity> evidenceTypeEntityList = getEvidenceTypeRepository().findAll();
        Map<Long, EvidenceTypeEntity> evidenceTypeMap = new HashMap<>();
        evidenceTypeEntityList.forEach(evidenceTypeEntity -> evidenceTypeMap.put(evidenceTypeEntity.getId(), evidenceTypeEntity));


        List<HideEntity> hideList = hideRepository.findByUser(user);
        Map<Long, HideEntity> hideMap = new HashMap<>();
        hideList.forEach(hide -> hideMap.put(hide.getId(), hide));


        List<ReportEntity> reportEntityList;
        if (user != null)
            reportEntityList = getReportRepository().findByUser(user);
        else
            reportEntityList = getReportRepository().findAll();

        reportEntityList.forEach(reportEntity -> {
            reportEntity.setAnimalEntity(animalMap.get(reportEntity.getAnimalId()));
            if (reportEntity.getSubAnimalId() != null)
                reportEntity.setSubAnimalEntity(animalMap.get(reportEntity.getSubAnimalId()));

            reportEntity.setEvidenceTypeEntity(evidenceTypeMap.get(reportEntity.getEvidenceType()));
            if (reportEntity.getHuntingHide() != null) {
                HideEntity hide = hideMap.get(reportEntity.getHuntingHide());
                if (hide != null)
                    reportEntity.setHuntingHideName(hide.getName());
            }

            if (reportEntity.getUser() != null && !reportEntity.getUser().isEmpty())
                reportEntity.setUserEntity(getUserRepository().findById(reportEntity.getUser()).orElseGet(null));


        });
        return reportEntityList;
    }


    public List<ReportEntity> findAll() {
        return findAllByUser(null);
    }

    public ReportEntity find(Long id) {
        return getReportRepository().findById(id).orElseGet(null);
    }

    public void delete(Long id) {
        ReportEntity report = find(id);
        if (report != null) {
            List<FilesEntity> files = getFilesRepository().findByOrigin(id, FilesEntity.ORIGIN_TYPE_REPORT);
            if (files != null)
                getFilesRepository().deleteAll(files);
            getReportRepository().deleteById(id);
        }
    }


    public void deleteByRefId(UUID refId) {
        ReportEntity report = getReportRepository().findByRefId(refId);
        report.setDeleted("X");
        save(report);
    }

    public ReportEntity save(ReportEntity report) {
        getReportRepository().saveAndFlush(report);
        return report;
    }

    public void renameUser(String oldUser, String newUser) {
        List<ReportEntity> reportEntityList = getReportRepository().findByUser(oldUser);
        reportEntityList.forEach(reportEntity -> {
            reportEntity.setUser(newUser);
            save(reportEntity);
        });
    }

    public ReportRepository getReportRepository() {
        return reportRepository;
    }

    public AnimalRepository getAnimalRepository() {
        return animalRepository;
    }

    public EvidenceTypeRepository getEvidenceTypeRepository() {
        return evidenceTypeRepository;
    }

    public FilesRepository getFilesRepository() {
        return filesRepository;
    }

    public HideRepository getHideRepository() {
        return hideRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
