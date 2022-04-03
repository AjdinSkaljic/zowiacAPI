package com.zowiac.service;

import com.zowiac.model.EvidenceTypeEntity;
import com.zowiac.respository.EvidenceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class EvidenceTypeService {

    private final EvidenceTypeRepository evidenceTypeRepository;

    @Autowired
    public EvidenceTypeService(EvidenceTypeRepository evidenceTypeRepository) {
        this.evidenceTypeRepository = evidenceTypeRepository;
    }

    public List<EvidenceTypeEntity> findAll() {
        return getEvidenceTypeRepository().findAll();
    }

    public EvidenceTypeEntity find(Long id) {
        return getEvidenceTypeRepository().findById(id).orElseGet(null);
    }

    public void delete(Long id) {
        getEvidenceTypeRepository().deleteById(id);
    }

    public EvidenceTypeEntity save(EvidenceTypeEntity evidenceType) {

        if (evidenceType.getName() == null)
            delete(evidenceType.getId());
        else
            getEvidenceTypeRepository().saveAndFlush(evidenceType);
        return evidenceType;
    }

    public EvidenceTypeRepository getEvidenceTypeRepository() {
        return evidenceTypeRepository;
    }
}
