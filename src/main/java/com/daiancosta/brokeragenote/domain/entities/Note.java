package com.daiancosta.brokeragenote.domain.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "note")
@Data
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String number;
    private String register;
    private String institution;
    @Column(name = "settlement_fee")
    private BigDecimal settlementFee;//taxa de liquidacao
    @Column(name = "registration_fee")
    private BigDecimal registrationFee;//taxa de registro
    @Column(name = "total_fee_bovespa")
    private BigDecimal totalFeeBovespa;//total taxa bovespa
    @Column(name = "total_operation_cost")
    private BigDecimal totalOperationCost;//total custos operacionais
    @Column(name = "liquid_for")
    private BigDecimal liquidFor;//liquido para
    @Column(name = "total_in_cach_purchases")
    private BigDecimal totalInCashPurchases;//total compra a vista
    @Column(name = "total_in_cach_sales")
    private BigDecimal totalInCashSales;//total venda a vista
    @Column(name = "total_options_purchase")
    private BigDecimal totalOptionsPurchase;//opcao de compra
    @Column(name = "total_options_sale")
    private BigDecimal totalOptionsSale;//opcao de venda
    @Column(name = "user_id")
    private UUID userId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "note_id")
    private List<NoteItem> items;
}
