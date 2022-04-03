package com.zowiac.respository;


import com.zowiac.model.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

    @Query(value = "select n from AuthorityEntity n where lower(n.city) like lower(:searchString) or lower(n.postalCode) like lower(:searchString) ")
    List<AuthorityEntity> findByPlzOrt(@Param("searchString") String searchString);

}
