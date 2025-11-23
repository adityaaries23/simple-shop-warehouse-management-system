package com.example.demo.repository;

import com.example.demo.model.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = """
    SELECT i FROM Item i WHERE i.name LIKE :name
    """, countQuery = "SELECT count(1) FROM Item i WHERE i.name LIKE :name")
    Page<Item> findByName(String name, Pageable pageable);
}