package com.daiancosta.brokeragenote.domain.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    @Column(name = "title_code")
    private String institution;
    private String titleCode;
    private String description;
    private BigDecimal quantity;
    private BigDecimal price;
    @Column(name = "price_unit")
    private BigDecimal priceUnit;
    @Column(name = "type_transaction")
    private String typeTransaction;
    @Column(name = "type_operation")
    private String typeOperation;
}
