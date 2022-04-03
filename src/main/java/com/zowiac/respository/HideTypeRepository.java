package com.zowiac.respository;

import com.zowiac.model.HideTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HideTypeRepository extends JpaRepository<HideTypeEntity, Long> {
}
