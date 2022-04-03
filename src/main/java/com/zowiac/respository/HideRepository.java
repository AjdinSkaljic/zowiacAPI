package com.zowiac.respository;

import com.zowiac.model.HideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface HideRepository extends JpaRepository<HideEntity, Long> {

    @Query(value = "select * from hides h where h.ref_id = :refId  limit 1 ", nativeQuery = true)
    HideEntity findByRefId(@Param("refId") UUID refId);

    @Query(value = "select h from HideEntity h where h.userName = :user")
    List<HideEntity> findByUser(@Param("user") String user);
}


