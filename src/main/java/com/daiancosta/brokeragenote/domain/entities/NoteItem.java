package com.daiancosta.brokeragenote.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "note_item")
@Data
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
    @Column(name = "type_title")
    private String typeTitle;
    private String description;
    private BigDecimal quantity;
    @Column(name = "price_unit")
    private BigDecimal priceUnit;
    private BigDecimal price;
    @Column(name = "fee_unit", precision = 19, scale = 9)
    private BigDecimal feeUnit;
    @Column(name = "type_transaction")
    private String typeTransaction;

    @ManyToOne
    @JoinColumn(name = "note_id")
    @JsonIgnore
    private Note note;
}
