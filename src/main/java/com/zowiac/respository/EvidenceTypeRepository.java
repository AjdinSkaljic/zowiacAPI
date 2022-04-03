package com.zowiac.respository;

import com.zowiac.model.EvidenceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EvidenceTypeRepository extends JpaRepository<EvidenceTypeEntity, Long> {

}
