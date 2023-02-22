package com.zowiac.respository;

import com.zowiac.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends JpaRepository<OrderEntity, Long> {
}
