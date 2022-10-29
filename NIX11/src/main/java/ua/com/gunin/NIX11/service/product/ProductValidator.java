package ua.com.gunin.NIX11.service.product;

import ua.com.gunin.NIX11.model.Product;

public abstract class ProductValidator {

    public static void checkProduct(final Product product) {
        final String description = product.getDescription();
        if (description != null && Character.isLowerCase(description.charAt(0))) {
            throw new IllegalArgumentException("Invalid description: " + description);
        }
    }
}
