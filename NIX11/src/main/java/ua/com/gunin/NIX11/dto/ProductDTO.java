package ua.com.gunin.NIX11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.gunin.NIX11.model.enums.Color;
import ua.com.gunin.NIX11.model.enums.Manufacturer;
import ua.com.gunin.NIX11.model.enums.PetType;
import ua.com.gunin.NIX11.model.enums.ProductType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    String id;
    String title;
    LocalDateTime created;
    long count;
    Manufacturer manufacturer;
    ProductType productType;
    double weight;
    Color color;
    PetType petType;
    long price;
    String description;
    String imgUrl;
}
