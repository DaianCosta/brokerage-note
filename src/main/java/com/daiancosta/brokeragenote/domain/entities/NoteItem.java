package com.daiancosta.brokeragenote.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "note_item")
@Getter
@Setter
public class NoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title_code")
    private String titleCode;
    @Column(name = "type_market")
    private String typeMarket;
    @Column(name = "type_operation")
    private String typeOperation;
    private String description;
    private BigDecimal quantity;
    @Column(name = "price_unit")
    private BigDecimal priceUnit;
    private BigDecimal price;
    @Column(name = "fee_unit")
    private BigDecimal feeUnit;
    @Column(name = "type_transaction")
    private String typeTransaction;

    @ManyToOne
    @JoinColumn(name = "note_id")
    @JsonIgnore
    private Note note;
}
