package ua.com.gunin.NIX11.service.product;

import org.springframework.stereotype.Component;
import ua.com.gunin.NIX11.model.Product;
import ua.com.gunin.NIX11.model.enums.Color;
import ua.com.gunin.NIX11.model.enums.Manufacturer;
import ua.com.gunin.NIX11.model.enums.PetType;
import ua.com.gunin.NIX11.model.enums.ProductType;

import java.time.LocalDateTime;

@Component
public class ProductCreator {
    public Product create(
            String title, LocalDateTime created, long count, Manufacturer manufacturer,
            ProductType productType, double weight, Color color, PetType petType,
            long price, String description, String imgUrl
    ) {
        final Product product = new Product();
        product.setTitle(title);
        product.setCreated(created);
        product.setCount(count);
        product.setManufacturer(manufacturer);
        product.setProductType(productType);
        product.setWeight(weight);
        product.setColor(color);
        product.setPetType(petType);
        product.setPrice(price);
        product.setDescription(description);
        product.setImgUrl(imgUrl);
        return product;
    }
}
