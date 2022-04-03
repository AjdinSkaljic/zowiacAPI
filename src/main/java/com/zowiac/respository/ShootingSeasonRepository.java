package com.zowiac.respository;

import com.zowiac.model.ShootingSeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ShootingSeasonRepository extends JpaRepository<ShootingSeasonEntity, Long> {

    @Query(value = "select n from ShootingSeasonEntity  n where n.state = :bundesland")
    List<ShootingSeasonEntity> findByBundesland(@Param("bundesland") String bundesland);

}
