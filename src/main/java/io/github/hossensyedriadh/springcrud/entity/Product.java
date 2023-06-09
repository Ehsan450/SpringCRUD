package io.github.hossensyedriadh.springcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public final class Product {
    @Id
    @Size(max = 12)
    @Column(name = "sku", nullable = false, length = 12)
    private String sku;

    @Size(min = 3, max = 120)
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Can only contain upper-case, lower-case letters, numbers and whitespaces")
    @Column(name = "name", updatable = false, nullable = false, length = 120)
    private String name;

    @NotNull
    @PositiveOrZero
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @NotNull
    @PositiveOrZero
    @Column(name = "price", nullable = false)
    private Double price;

    @PrePersist
    private void init() {
        this.sku = UUID.randomUUID().toString().split("-")[4];
    }
}
