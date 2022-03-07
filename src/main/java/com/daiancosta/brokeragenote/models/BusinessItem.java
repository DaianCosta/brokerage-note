package com.daiancosta.brokeragenote.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BusinessItem {
    private Long id;
    private String titleCode;
    private String typeMarket;
    private String typeOperation;
    private String description;
    private BigDecimal quantity;
    private BigDecimal priceUnit;
    private BigDecimal price;
    private String typeTransaction;
}
