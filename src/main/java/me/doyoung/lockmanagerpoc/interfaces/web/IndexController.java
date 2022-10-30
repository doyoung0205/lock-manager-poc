package me.doyoung.lockmanagerpoc.interfaces.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {

    @RequestMapping({"/pages", "/index", "/", ""})
    public RedirectView redirectPage() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/pages/items");
        return redirectView;
    }
}
