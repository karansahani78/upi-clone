package com.karan.upi_clone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @org.hibernate.annotations.GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private String id;

    @NotBlank(message = "From VPA cannot be blank")
    private String fromVpa;

    @NotBlank(message = "To VPA cannot be blank")
    private String toVpa;

    @NotBlank(message = "Amount cannot be blank")
    @Pattern(regexp = "\\d+(\\.\\d{1,2})?", message = "Amount must be a valid number with up to 2 decimal places")
    private String amount;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @NotNull(message = "CreatedAt cannot be null")
    private LocalDateTime createdAt = LocalDateTime.now();
}
