package ua.com.gunin.NIX11.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.gunin.NIX11.dto.BasketDTO;
import ua.com.gunin.NIX11.dto.BasketDetailDTO;
import ua.com.gunin.NIX11.model.Basket;
import ua.com.gunin.NIX11.model.Product;
import ua.com.gunin.NIX11.model.User;
import ua.com.gunin.NIX11.repository.BasketRepository;
import ua.com.gunin.NIX11.repository.ProductRepository;
import ua.com.gunin.NIX11.service.user.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasketServiceImp implements BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public BasketServiceImp(BasketRepository basketRepository, ProductRepository productRepository, UserService userService) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Basket createBasket(User user, List<String> productIds) {
        Basket basket = new Basket();
        basket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        basket.setProducts(productList);
        return basketRepository.save(basket);
    }

    private List<Product> getCollectRefProductsByIds(List<String> productIds) {
        return productIds.stream()
                .map(productRepository::getReferenceById)
                .collect(Collectors.toList());
    }

    @Override
    public void addProducts(Basket basket, List<String> productIds) {
        List<Product> products = basket.getProducts();
        List<Product> newProductList;
        if (products == null) {
            newProductList = new ArrayList<>();
        } else {
            newProductList = new ArrayList<>(products);
        }
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        basket.setProducts(newProductList);
        basketRepository.save(basket);
    }

    @Override
    public BasketDTO getBasketByUser(String username) {
        User user = userService.findByUsername(username);
        if (user == null || user.getBasket() == null) {
            return new BasketDTO();
        }

        BasketDTO basketDTO = new BasketDTO();
        Map<String, BasketDetailDTO> mapByProductId = new HashMap<>();

        List<Product> products = user.getBasket().getProducts();
        for (Product product :products) {
            BasketDetailDTO detailDTO = mapByProductId.get(product.getId());
            if (detailDTO == null) {
                mapByProductId.put(product.getId(), new BasketDetailDTO(product));
            } else {
                detailDTO.setCount(detailDTO.getCount() + 1);
                detailDTO.setSum(detailDTO.getSum() + product.getPrice());
            }
        }

        basketDTO.setBasketDetailDTOS(new ArrayList<>(mapByProductId.values()));
        basketDTO.aggregate();

        return basketDTO;
    }
}
