package ua.com.gunin.NIX11.dto;

import lombok.Value;
import ua.com.gunin.NIX11.model.Product;
import ua.com.gunin.NIX11.model.User;
import ua.com.gunin.NIX11.model.enums.InvoiceStatus;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class InvoiceDTO {
    String id;
    User user;
    LocalDateTime created;
    long sum;
    String address;
    String recipient;
    String phoneNumber;
    String email;
    InvoiceStatus status;
    List<Product> products;
}
