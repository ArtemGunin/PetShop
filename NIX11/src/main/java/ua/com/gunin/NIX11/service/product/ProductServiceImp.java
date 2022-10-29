package ua.com.gunin.NIX11.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.gunin.NIX11.dto.ProductDTO;
import ua.com.gunin.NIX11.mapper.ProductMapper;
import ua.com.gunin.NIX11.model.Basket;
import ua.com.gunin.NIX11.model.Product;
import ua.com.gunin.NIX11.model.User;
import ua.com.gunin.NIX11.model.enums.Color;
import ua.com.gunin.NIX11.model.enums.Manufacturer;
import ua.com.gunin.NIX11.model.enums.PetType;
import ua.com.gunin.NIX11.model.enums.ProductType;
import ua.com.gunin.NIX11.repository.ProductRepository;
import ua.com.gunin.NIX11.service.BasketService;
import ua.com.gunin.NIX11.service.user.UserService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;
    private final ProductCreator productCreator;
    private final UserService userService;
    private final BasketService basketService;


    @Autowired
    public ProductServiceImp(
            ProductRepository productRepository,
            ProductCreator productCreator,
            UserService userService,
            BasketService basketService
    ) {
        this.productRepository = productRepository;
        this.productCreator = productCreator;
        this.userService = userService;
        this.basketService = basketService;
    }

    @Override
    public ProductDTO findById(String id) {
        return mapper.fromProduct(productRepository.findFirstById(id).orElseThrow());
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    public ProductDTO getOneOrCreate(String id) {
        return mapper.fromProduct(productRepository.findById(id).orElseGet(() -> {
            final Product product = productCreator.create(
                    "Default title",
                    LocalDateTime.now(),
                    0,
                    Manufacturer.DEFAULT,
                    ProductType.DEFAULT,
                    0.0,
                    Color.BLACK,
                    PetType.DEFAULT,
                    0,
                    "Default description",
                    "");
            productRepository.save(product);
            return product;
        }));
    }

    @Override
    public ProductDTO create(Product product) {
        productRepository.save(product);
        return mapper.fromProduct(product);
    }

    @Override
    public List<ProductDTO> findByTitle(String title) {
        return mapper.fromProductList(productRepository.findByTitleContainingIgnoreCase(title));
    }

    @Override
    public List<ProductDTO> findByProductType(ProductType productType) {
        return mapper.fromProductList(productRepository.findByProductType(productType));
    }

    @Override
    public List<ProductDTO> findByPetType(PetType petType) {
        return mapper.fromProductList(productRepository.findByPetType(petType));
    }


    @Override
    @Transactional
    public void update(ProductDTO productDTO) {
        Product product = mapper.toProduct(productDTO);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void addToUserBasket(String productId, String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found - " + username);
        }

        Basket basket = user.getBasket();
        if (basket == null) {
            Basket newBasket = basketService.createBasket(user, Collections.singletonList(productId));
            user.setBasket(newBasket);
            userService.save(user);
        } else {
            basketService.addProducts(basket, Collections.singletonList(productId));
        }
    }
}
