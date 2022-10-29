package ua.com.gunin.NIX11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.gunin.NIX11.dto.BasketDTO;
import ua.com.gunin.NIX11.service.BasketService;

import java.security.Principal;

@Controller
public class BasketController {

    private final BasketService basketService;


    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/basket")
    public String aboutBasket(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("basket", new BasketDTO());
        } else {
            BasketDTO basketDTO = basketService.getBasketByUser(principal.getName());
            model.addAttribute("basket", basketDTO);
        }
        return "basket";
    }
}
