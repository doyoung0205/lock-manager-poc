package me.doyoung.lockmanagerpoc.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pages")
public class ItemWebController {

    @GetMapping("/items")
    public String routeItemListPage() {
        return "pages/items/list";
    }

    @GetMapping("/items/{itemId:\\d+}")
    public String routeItemDetailPage(@PathVariable Long itemId) {
        return "/pages/items/detail";
    }
}
