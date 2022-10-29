package ua.com.gunin.NIX11.dto;

import lombok.Value;
import ua.com.gunin.NIX11.model.User;
import ua.com.gunin.NIX11.model.enums.PetType;

@Value
public class PetDTO {
    String id;
    String name;
    User user;
    PetType petType;
}
