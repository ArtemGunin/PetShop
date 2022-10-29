package ua.com.gunin.NIX11.service.product;

import ua.com.gunin.NIX11.dto.ProductDTO;
import ua.com.gunin.NIX11.model.Product;
import ua.com.gunin.NIX11.model.enums.PetType;
import ua.com.gunin.NIX11.model.enums.ProductType;

import java.util.List;

public interface ProductService {

    ProductDTO findById(String id);

    List<ProductDTO> getAll();

    ProductDTO getOneOrCreate(String id);

    ProductDTO create(Product product);

    List<ProductDTO> findByTitle(String title);

    List<ProductDTO> findByProductType(ProductType productType);

    List<ProductDTO> findByPetType(PetType petType);

    void update(ProductDTO productDTO);

    void addToUserBasket(String productId, String username);
}
