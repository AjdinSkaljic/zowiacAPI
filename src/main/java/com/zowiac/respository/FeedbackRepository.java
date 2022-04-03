package com.zowiac.respository;

import com.zowiac.model.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {

}
