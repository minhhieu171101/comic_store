package com.example.comic_store.repository;

import com.example.comic_store.entity.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    @Query(
            value = "SELECT " +
                    " ue.id," +
                    " uo.id, " +
                    " ue.fullName," +
                    " ue.address," +
                    " ue.phone," +
                    " uo.totalPrice," +
                    " uo.orderDate," +
                    " uo.status " +
                    "FROM UserOrder uo " +
                    "INNER JOIN UserEntity ue ON uo.userId = ue.id " +
                    "ORDER BY uo.createdAt",
            countQuery = "SELECT COUNT(uo) FROM UserOrder uo"
    )
    Page<Object[]> getAllPurchaseOrder(Pageable pageable);
}
