package com.daiancosta.brokeragenote.domain.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "movement")
@Data
@Getter
@Setter
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;
    private String institution;
    @Column(name = "title_code")
    private String titleCode;
    @Column(name = "type_operation")
    private String typeOperation;
    private String description;
    private BigDecimal quantity;
    @Column(name = "price_unit")
    private BigDecimal priceUnit;
    private BigDecimal price;
    @Column(name = "type_transaction")
    private String typeTransaction;
    private UUID userId;
}
