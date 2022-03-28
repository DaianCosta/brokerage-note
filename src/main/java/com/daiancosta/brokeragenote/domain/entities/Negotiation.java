package com.daiancosta.brokeragenote.domain.entities;

import com.daiancosta.brokeragenote.domain.entities.constants.TypeTitle;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "negotiation")
@Data
public class Negotiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @Column(name = "movement_type")
    private String movementType;
    private String market;
    private LocalDate deadline;
    private String institution;
    @Column(name = "title_code")
    private String titleCode;
    @Column(name = "type_title")
    private String typeTitle;
    private BigDecimal quantity;
    @Column(name = "price_unit")
    private BigDecimal priceUnit;
    private BigDecimal price;
    @Column(name = "user_id")
    private UUID userId;
}
