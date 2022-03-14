package com.daiancosta.brokeragenote.domain.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InstitutionEnum {
    XP("XP INVESTIMENTOS CCTVM S/A"),
    CLEAR("CLEAR CORRETORA - GRUPO XP"),
    INTER("INTER");

    private String description;
}
