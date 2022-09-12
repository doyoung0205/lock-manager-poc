package me.doyoung.lockmanagerpoc.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("pages")
public class ItemWebController {

    @RequestMapping("/items")
    public String routeItemListPage() {
        return "item/list";
    }

    @RequestMapping("/items/{itemId:[\\d]+}")
    public String routeItemDetailPage(@PathVariable Long itemId) {
        return "item/detail";
    }
}
