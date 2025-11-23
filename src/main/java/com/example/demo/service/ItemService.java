package com.example.demo.service;

import com.example.demo.constant.DefaultConstant;
import com.example.demo.exceptions.NotFoundExceptions;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.Pricing;
import com.example.demo.model.entity.Stock;
import com.example.demo.model.entity.Variant;
import com.example.demo.model.request.ItemRequest;
import com.example.demo.model.request.ItemUpdateRequest;
import com.example.demo.model.request.VariantRequest;
import com.example.demo.model.request.VariantUpdateRequest;
import com.example.demo.model.response.*;
import com.example.demo.projections.ItemProjections;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PricingRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.VariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final StockRepository stockRepository;
    private final PricingRepository pricingRepository;
    private final VariantRepository variantRepository;

    public PaginationResponse getAllItem(String name, Pageable pageable) {
        Page<ItemProjections> itemProjections = itemRepository.getItemProjections(Helper.seacrhString(name), pageable);
        List<ItemResponse> list = itemProjections.getContent().stream().map(ItemResponse::new).toList();
        return new PaginationResponse(itemProjections, list);
    }

    @Transactional
    public ItemDetailResponse findById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(NotFoundExceptions::new);
        List<VariantResponse> list = variantRepository.findAllByItemId(item.getId()).stream().map(variant -> {
            Stock stock = stockRepository.findByVariantId(variant.getId()).orElseThrow(NotFoundExceptions::new);
            Pricing pricing = pricingRepository.findByVariantId(variant.getId()).orElseThrow(NotFoundExceptions::new);
            return new VariantResponse(variant, stock, pricing);
        }).toList();
        return new ItemDetailResponse(item, list);
    }

    @Transactional(rollbackOn = Exception.class)
    public DefaultResponse create(ItemRequest itemRequest) {
        Item item = new Item();
        item.setName(itemRequest.getName());
        item.setDescription(itemRequest.getDescription());
        item = itemRepository.save(item);

        Item finalItem = item;
        itemRequest.getVariants().forEach(request -> {
            Variant variant = setVariant(request, finalItem, null);
            setStock(request, variant);
            setPrice(request, variant);
        });
        return new DefaultResponse(item.getId(), DefaultConstant.SUCCESS);
    }

    @Transactional(rollbackOn = Exception.class)
    public DefaultResponse update(ItemUpdateRequest itemRequest, Long id) {
        Item item = itemRepository.findById(id).orElseThrow(NotFoundExceptions::new);
        item.setName(itemRequest.getName());
        item.setDescription(itemRequest.getDescription());
        item = itemRepository.save(item);
        Item finalItem = item;

        itemRequest.getVariants().forEach(request -> {
            Variant variant = setVariant(request, finalItem, request.getVariantId());
            setStock(request, variant);
            setPrice(request, variant);
        });

        List<Long> list = itemRequest.getVariants().stream().map(VariantUpdateRequest::getVariantId).filter(Objects::nonNull).toList();
        deleteVariant(list, id);

        return new DefaultResponse(item.getId(), DefaultConstant.SUCCESS);
    }

    private void setPrice(VariantRequest request, Variant variant) {
        Pricing pricing = new Pricing();
        Optional<Pricing> optional = pricingRepository.findByVariantId(variant.getId());
        if (optional.isPresent()) pricing = optional.get();

        pricing.setVariant(variant);
        pricing.setPrice(request.getPrice());
        pricing.setCurrency(request.getCurrency());
        pricingRepository.save(pricing);
    }

    private void setStock(VariantRequest request, Variant variant) {
        Stock stock = new Stock();
        Optional<Stock> optional = stockRepository.findByVariantId(variant.getId());
        if (optional.isPresent()) stock = optional.get();

        stock.setVariant(variant);
        stock.setQuantity(request.getQuantity());
        stockRepository.save(stock);
    }

    private Variant setVariant(VariantRequest request, Item finalItem, Long variantId) {
        Variant variant = new Variant();
        if (variantId != null) {
            variant = variantRepository.findById(variantId).orElseThrow(NotFoundExceptions::new);
        } else {
            variant.setSku(UUID.randomUUID().toString());
        }
        variant.setItem(finalItem);
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());
        variant = variantRepository.save(variant);
        return variant;
    }

    private void deleteVariant(List<Long> variantIds, Long itemId) {
        List<Variant> variants = variantRepository.findAllByItemId(itemId);
        variants.stream().filter(v -> !variantIds.contains(v.getId())).forEach(v -> v.setIsDeleted(true));
        variantRepository.saveAll(variants);
    }

    public void deleteItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(NotFoundExceptions::new);
        item.setIsDeleted(true);
        itemRepository.save(item);
    }

    @Transactional(rollbackOn = Exception.class)
    public void orderItem(Long id, Long variantId) {
        itemRepository.findById(id).orElseThrow(NotFoundExceptions::new);
        variantRepository.findById(variantId).orElseThrow(NotFoundExceptions::new);

        Stock stock = stockRepository.findByVariantId(variantId).orElseThrow(NotFoundExceptions::new);
        if (stock.getQuantity() <= 0) throw new NotFoundExceptions();
        stock.setQuantity(stock.getQuantity() - 1);
        stockRepository.save(stock);
    }
}
