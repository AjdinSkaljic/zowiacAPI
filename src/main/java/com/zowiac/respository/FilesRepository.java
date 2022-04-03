package com.zowiac.respository;

import com.zowiac.model.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, UUID> {
    @Query(value = "select n from FilesEntity  n where n.originId = :id and n.originType = :type ")
    List<FilesEntity> findByOrigin(@Param("id") Long originID, @Param("type") String originType);


}
