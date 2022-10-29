package ua.com.gunin.NIX11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.gunin.NIX11.model.Basket;

public interface BasketRepository extends JpaRepository<Basket, String> {
}
