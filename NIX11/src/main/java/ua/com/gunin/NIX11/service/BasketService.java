package ua.com.gunin.NIX11.service;

import ua.com.gunin.NIX11.dto.BasketDTO;
import ua.com.gunin.NIX11.model.Basket;
import ua.com.gunin.NIX11.model.User;

import java.util.List;

public interface BasketService {
    Basket createBasket(User user, List<String> productIds);

    void addProducts(Basket basket, List<String> productIds);

    BasketDTO getBasketByUser(String username);
}
