package ua.com.gunin.NIX11.service.user;

import ua.com.gunin.NIX11.model.User;

public class UserValidator {

    public static void checkUser(final User user) {

        if (user.getPassword().length() < 4) {
            throw new IllegalArgumentException("Invalid password.");
        }
        String name = user.getName();
        if (name.length() < 3) {
            throw new IllegalArgumentException("Invalid name:" + name);
        }
        String surname = user.getSurname();
        if (surname.length() < 3) {
            throw new IllegalArgumentException("Invalid surname:" + surname);
        }
    }
}
