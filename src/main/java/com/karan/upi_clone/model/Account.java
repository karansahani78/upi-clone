package com.karan.upi_clone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "account_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "VPA cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@upi$", message = "VPA must be valid and end with '@upi'")
    private String vpa;

    @NotNull(message = "Balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative")
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private AppUser user;

    @NotNull(message = "Account must have an active status")
    private Boolean isActive = true;
}
