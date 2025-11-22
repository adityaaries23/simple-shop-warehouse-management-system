package com.example.demo.controller;

import com.example.demo.model.entity.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    private ResponseEntity<List<Item>> allItem() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }
}
