package com.zowiac.respository;

import com.zowiac.model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    @Query(value = "select * from reports  n where n.ref_id = :refId  limit 1 ", nativeQuery = true)
    ReportEntity findByRefId(@Param("refId") UUID refId);

    @Query(value = "select r from ReportEntity r where r.user = :user and r.deleted is null order by r.date desc, r.time desc")
    List<ReportEntity> findByUser(@Param("user") String user);
}
