package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Audited
public class Pricing extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false)
    @NotAudited
    private Variant variant;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ColumnDefault("'IDR'")
    @Column(name = "currency", nullable = false, length = 10)
    private String currency;
}