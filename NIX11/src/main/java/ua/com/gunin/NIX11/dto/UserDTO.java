package ua.com.gunin.NIX11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.gunin.NIX11.model.Basket;
import ua.com.gunin.NIX11.model.Invoice;
import ua.com.gunin.NIX11.model.Pet;
import ua.com.gunin.NIX11.model.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    String id;
    String name;
    String surname;
    String email;
    String username;
    String password;
    String city;
    String phoneNumber;
    String deliveryAddress;
    String passwordConfirm;
    Role role;
    long bonus;
    LocalDate dateOfBirth;
    List<Pet> pets;
    List<Invoice> invoices;
    LocalDateTime created;
    Basket basket;
}
