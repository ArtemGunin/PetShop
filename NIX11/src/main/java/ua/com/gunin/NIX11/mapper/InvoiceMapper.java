package ua.com.gunin.NIX11.mapper;

import ua.com.gunin.NIX11.dto.InvoiceDTO;
import ua.com.gunin.NIX11.model.Invoice;

public final class InvoiceMapper {

    public static InvoiceDTO mapInvoiceToDTO(Invoice invoice) {
        return new InvoiceDTO(
                invoice.getId(),
                invoice.getUser(),
                invoice.getCreated(),
                invoice.getSum(),
                invoice.getAddress(),
                invoice.getRecipient(),
                invoice.getPhoneNumber(),
                invoice.getEmail(),
                invoice.getStatus(),
                invoice.getProducts()
        );
    }
}
