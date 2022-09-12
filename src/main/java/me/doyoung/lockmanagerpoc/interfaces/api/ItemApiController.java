package me.doyoung.lockmanagerpoc.interfaces.api;

import lombok.RequiredArgsConstructor;
import me.doyoung.lockmanagerpoc.application.ItemService;
import me.doyoung.lockmanagerpoc.application.dto.ItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemApiController {
    private final ItemService itemService;

    public ItemApiController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDto.Response> saveItem(@RequestBody ItemDto.SaveRequest request) {
        return ResponseEntity.ok(itemService.save(request));
    }

    @GetMapping()
    public ResponseEntity<List<ItemDto.Response>> getItemList() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto.Response> getItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.getItem(itemId));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemDto.Response> updateItem(@PathVariable Long itemId, @RequestBody ItemDto.UpdateRequest request) {
        return ResponseEntity.ok(itemService.update(itemId, request));
    }
}
