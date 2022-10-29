package ua.com.gunin.NIX11.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import ua.com.gunin.NIX11.model.enums.InvoiceStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private static final String SEQ_NAME = "invoice_seq";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    private LocalDateTime created;
    private long sum;
    @NotBlank
    private String address;
    @NotBlank
    private String recipient;
    @NotBlank
    private String phoneNumber;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Invoice_Product",
            joinColumns = {@JoinColumn(name = "inv_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "pr_id", referencedColumnName = "id")}
    )
    private List<Product> products;
}
