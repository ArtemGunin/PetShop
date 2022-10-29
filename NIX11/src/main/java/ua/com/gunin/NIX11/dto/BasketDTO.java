package ua.com.gunin.NIX11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDTO {
    private int productsCount;
    private long sum;
    private List<BasketDetailDTO> basketDetailDTOS = new ArrayList<>();

    public void aggregate() {
        this.productsCount = basketDetailDTOS.size();
        this.sum = basketDetailDTOS.stream()
                .map(BasketDetailDTO::getSum)
                .mapToLong(Long::longValue)
                .sum();
    }
}
