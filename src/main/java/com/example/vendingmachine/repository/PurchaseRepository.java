package com.example.vendingmachine.repository;

import com.example.vendingmachine.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByLocalDateAfter(LocalDate localDate);

    @Query(value = "SELECT * FROM PURCHASE WHERE LOCAL_DATE BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Purchase> getAllBetweenDates(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
                                           @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate);

}
