package com.example.demo.controller;

import com.example.demo.constant.DefaultConstant;
import com.example.demo.model.entity.Item;
import com.example.demo.model.request.ItemRequest;
import com.example.demo.model.request.ItemUpdateRequest;
import com.example.demo.model.response.DefaultResponse;
import com.example.demo.model.response.ItemDetailResponse;
import com.example.demo.model.response.PaginationResponse;
import com.example.demo.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<PaginationResponse> allItem(@RequestParam(defaultValue = "") String name,
                                                      @ParameterObject Pageable pageable) {
        return new ResponseEntity<>(itemService.getAllItem(name,pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDetailResponse> findItemById(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> createItem(@RequestBody @Valid ItemRequest item) {
        return new ResponseEntity<>(itemService.create(item),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateItem(@RequestBody @Valid ItemUpdateRequest itemRequest, @PathVariable Long id) {
        return new ResponseEntity<>(itemService.update(itemRequest,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteItemById(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(new DefaultResponse(null, DefaultConstant.SUCCESS), HttpStatus.OK);
    }

    @PutMapping("/{id}/order/{variantId}")
    public ResponseEntity<DefaultResponse> order(@PathVariable Long id,@PathVariable Long variantId) {
        itemService.orderItem(id,variantId);
        return new ResponseEntity<>(new DefaultResponse(null, DefaultConstant.SUCCESS), HttpStatus.OK);
    }
}
