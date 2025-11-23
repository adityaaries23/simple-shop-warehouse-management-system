package com.example.demo.repository;

import com.example.demo.model.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PricingRepository extends JpaRepository<Pricing, Long> {
    @Query(value = "SELECT s FROM Pricing s WHERE s.variant.id = :variantId")
    Optional<Pricing> findByVariantId(Long variantId);
}