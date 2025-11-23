package com.example.demo.controller;

import com.example.demo.model.entity.Item;
import com.example.demo.model.request.ItemRequest;
import com.example.demo.model.response.DefaultResponse;
import com.example.demo.service.ItemService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<PagedModel<Item>> allItem(@RequestParam(defaultValue = "") String name,
                                                     @ParameterObject Pageable pageable) {
        return new ResponseEntity<>(itemService.findAll(name,pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> createItem(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = ItemRequest.class))
    ) @RequestBody @Valid ItemRequest item) {
        return new ResponseEntity<>(itemService.create(item),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateItem(@RequestBody @Valid ItemRequest itemRequest, @PathVariable Long id) {
        return new ResponseEntity<>(itemService.update(itemRequest,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteItemById(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.delete(id), HttpStatus.OK);
    }
}
