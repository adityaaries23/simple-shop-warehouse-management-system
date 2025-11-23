package com.example.demo.service;

import com.example.demo.constant.DefaultConstant;
import com.example.demo.exceptions.NotFoundExceptions;
import com.example.demo.model.entity.Item;
import com.example.demo.model.request.ItemRequest;
import com.example.demo.model.response.DefaultResponse;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public PagedModel<Item> findAll(String name, Pageable pageable) {
        return new PagedModel<>(itemRepository.findByName(Helper.seacrhString(name), pageable));
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(NotFoundExceptions::new);
    }

    public DefaultResponse create(ItemRequest itemRequest) {
        Item item = new Item();
        item.setName(itemRequest.getName());
        item.setDescription(itemRequest.getDescription());
        item = itemRepository.save(item);
        return new DefaultResponse(item.getId(), DefaultConstant.SUCCESS);
    }

    public DefaultResponse update(ItemRequest itemRequest, Long id) {
        Item item = findById(id);
        item.setName(itemRequest.getName());
        item.setDescription(itemRequest.getDescription());

        item = itemRepository.save(item);
        return new DefaultResponse(item.getId(), DefaultConstant.SUCCESS);
    }

    public DefaultResponse delete(Long id) {
        Item item = findById(id);
        item.setIsDeleted(true);
        itemRepository.save(item);
        return new DefaultResponse(null, DefaultConstant.SUCCESS);
    }
}
