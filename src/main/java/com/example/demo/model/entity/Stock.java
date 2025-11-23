package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Getter
@Setter
@Entity
@Audited
public class Stock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false)
    @NotAudited
    private Variant variant;

    @ColumnDefault("0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}