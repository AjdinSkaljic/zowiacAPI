package com.zowiac.respository;

import com.zowiac.model.UserRolesEntity;
import com.zowiac.model.UserRolesEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRolesRepository extends JpaRepository<UserRolesEntity, UserRolesEntityPK> {

    @Query(value = "select u from UserRolesEntity u where u.userName = :user")
    List<UserRolesEntity> findByUser(@Param("user") String user);


}
