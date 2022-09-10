package me.doyoung.lockmanagerpoc.application;

import lombok.RequiredArgsConstructor;
import me.doyoung.lockmanagerpoc.application.dto.ItemDto;
import me.doyoung.lockmanagerpoc.domain.item.Item;
import me.doyoung.lockmanagerpoc.infra.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public ItemDto.Response getItem(Long id) {
        final Item item = getItemById(id);
        return ItemDto.Response.from(item);
    }


    @Transactional(readOnly = true)
    public List<ItemDto.Response> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemDto.Response::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public ItemDto.Response update(Long id, ItemDto.UpdateRequest request) {
        final Item item = getItemById(id);
        item.update(request.toCommand());
        return ItemDto.Response.from(item);
    }

    @Transactional
    public ItemDto.Response save(ItemDto.SaveRequest request) {
        final Item item = itemRepository.save(request.toEntity());
        return ItemDto.Response.from(item);
    }

    private Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id[%s] 의 상품을 찾지 못하였습니다."));
    }
}
