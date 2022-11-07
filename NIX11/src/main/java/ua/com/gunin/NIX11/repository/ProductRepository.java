package ua.com.gunin.NIX11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.gunin.NIX11.model.Product;
import ua.com.gunin.NIX11.model.enums.Color;
import ua.com.gunin.NIX11.model.enums.Manufacturer;
import ua.com.gunin.NIX11.model.enums.PetType;
import ua.com.gunin.NIX11.model.enums.ProductType;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByTitleContainingIgnoreCase(String title);

    List<Product> findByProductType(ProductType productType);

    List<Product> findByManufacturer(Manufacturer manufacturer);

    List<Product> findByPetType(PetType petType);

    List<Product> findByColor(Color color);

    Optional<Product> findFirstById(String id);
}
