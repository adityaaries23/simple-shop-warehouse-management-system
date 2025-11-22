package com.example.demo.repository;

import com.example.demo.model.entity.Item;
import org.springframework.data.repository.Repository;

public interface ItemRepository extends Repository<Item, Long> {
}