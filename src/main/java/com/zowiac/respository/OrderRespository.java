package com.zowiac.respository;

import com.zowiac.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends JpaRepository<OrderEntity, Long> {

    //get max receipt id

    @Query(value = "SELECT max(receipt_id) FROM orders ", nativeQuery = true)
    Long findMaxReceiptId();


}
