package com.daiancosta.brokeragenote.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Note {
    private Long id;
    private Date date;
    private String number;
    private String register;
    private String institution;
    private BigDecimal settlementFee;//taxa de liquidacao
    private BigDecimal registrationFee;//taxa de registro
    private BigDecimal totalFeeBovespa;//total taxa bovespa
    private BigDecimal totalOperationCost;//total custos operacionais
    private BigDecimal liquidFor;//liquido para
    private BigDecimal totalInCashPurchases;//total compra a vista
    private BigDecimal totalInCashSales;//total venda a vista
    private BigDecimal totalOptionsPurchase;//opcao de compra
    private BigDecimal totalOptionsSale;//opcao de venda
    private List<NoteItem> items;
}
