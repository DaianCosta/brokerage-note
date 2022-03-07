package com.daiancosta.brokeragenote.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Business {
    private String date;
    private String movementType;
    private String market;
    private String deadline;
    private String institution;
    private String tradingCode;
    private Double quantity;
    private Double price;
    private Double total;
}
