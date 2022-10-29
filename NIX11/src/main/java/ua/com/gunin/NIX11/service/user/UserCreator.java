package ua.com.gunin.NIX11.service.user;

import org.springframework.stereotype.Component;
import ua.com.gunin.NIX11.model.Invoice;
import ua.com.gunin.NIX11.model.Pet;
import ua.com.gunin.NIX11.model.User;
import ua.com.gunin.NIX11.model.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserCreator {
    public User create(
            String name, String surname, String email, String password, String city,
            String phoneNumber, String deliveryAddress, Role role, long bonus,
            LocalDate dateOfBirth, List<Pet> pets, List<Invoice> invoices, LocalDateTime created
    ) {
        final User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setCity(city);
        user.setPhoneNumber(phoneNumber);
        user.setDeliveryAddress(deliveryAddress);
        user.setRole(role);
        user.setBonus(bonus);
        user.setDateOfBirth(dateOfBirth);
        user.setPets(pets);
        user.setInvoices(invoices);
        user.setCreated(created);
        return user;
    }
}
