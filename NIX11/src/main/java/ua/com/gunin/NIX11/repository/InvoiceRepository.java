package ua.com.gunin.NIX11.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.gunin.NIX11.model.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {
}
