package com.example.demo.repository;

import com.example.demo.model.entity.Stock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "SELECT s FROM Stock s WHERE s.variant.id = :variantId")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Stock> findByVariantId(Long variantId);
}