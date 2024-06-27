package com.zowiac.respository;

import com.zowiac.model.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    @Query(value = "select n from AnimalEntity  n where n.allowReport = 'J'")
    List<AnimalEntity> findOnlyForReporting();


    @Query(value = "select n from AnimalEntity  n where n.allowReport = 'J' and n.reportType = :reportType")
    List<AnimalEntity> findByReportType(@Param("reportType") String reportType);

}
