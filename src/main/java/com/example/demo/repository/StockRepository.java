package com.example.demo.repository;

import com.example.demo.model.entity.Stock;
import org.springframework.data.repository.Repository;

public interface StockRepository extends Repository<Stock, Long> {
}