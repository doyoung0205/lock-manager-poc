package me.doyoung.lockmanagerpoc.interfaces.web;

import me.doyoung.lockmanagerpoc.application.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class ItemWebController {
    private final ItemService itemService;

    public ItemWebController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String routeItemListPage(Model model) {
        model.addAttribute("itemList", itemService.findAll());
        return "pages/items/list";
    }

    @GetMapping("/items/{itemId:\\d+}")
    public String routeItemDetailPage(Model model, @PathVariable Long itemId) {
        model.addAttribute("item", itemService.getItem(itemId));
        return "/pages/items/detail";
    }
}
