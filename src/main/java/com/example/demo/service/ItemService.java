package com.example.demo.service;

import com.example.demo.model.entity.Item;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
