package ua.com.gunin.NIX11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.gunin.NIX11.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDetailDTO {
    private String title;
    private String productId;
    private long price;
    private long count;
    private long sum;

    public BasketDetailDTO(Product product) {
        this.title = product.getTitle();
        this.productId = product.getId();
        this.price = product.getPrice();
        this.count = 1;
        this.sum = product.getPrice();
    }
}
