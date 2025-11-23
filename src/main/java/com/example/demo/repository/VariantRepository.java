package com.example.demo.repository;

import com.example.demo.model.entity.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VariantRepository extends JpaRepository<Variant, Long> {
    @Query(value = """
    SELECT v FROM Variant v
    WHERE (:itemId IS NULL OR v.item.id = :itemId) AND v.item.name LIKE :itemName
    """)
    Page<Variant> findAll(Long itemId, String itemName, Pageable pageable);

    @Query(value = "SELECT v FROM Variant v WHERE v.item.id = :itemId")
    List<Variant> findAllByItemId(Long itemId);
}