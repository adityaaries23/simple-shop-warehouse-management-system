package com.example.demo.repository;

import com.example.demo.model.entity.Item;
import com.example.demo.projections.ItemProjections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = """
    SELECT i FROM Item i WHERE i.name LIKE :name
    """, countQuery = "SELECT count(1) FROM Item i WHERE i.name LIKE :name")
    Page<Item> findByName(String name, Pageable pageable);

    @Query(nativeQuery = true, value = """
    SELECT i.id            as item_id,
           i.name          as item_name,
           i.description   as item_description,
           MIN(p.price)    as min_price,
           MAX(p.price)    as max_price,
           MAX(s.quantity) as item_quantity
    FROM item i
             JOIN variant v ON i.id = v.item_id AND v.is_deleted = false
             JOIN pricing p ON v.id = p.variant_id AND p.is_deleted = false
             JOIN stock s ON v.id = s.variant_id AND s.is_deleted = false
    WHERE i.is_deleted = false
      AND i.name LIKE :name
    GROUP BY i.id, i.name, i.description
    """, countQuery = """
    SELECT count(1)
    FROM item i
             JOIN variant v ON i.id = v.item_id AND v.is_deleted = false
             JOIN pricing p ON v.id = p.variant_id AND p.is_deleted = false
             JOIN stock s ON v.id = s.variant_id AND s.is_deleted = false
    WHERE i.is_deleted = false
      AND i.name LIKE :name
    GROUP BY i.id, i.name, i.description
    """)
    Page<ItemProjections> getItemProjections(String name, Pageable pageable);
}