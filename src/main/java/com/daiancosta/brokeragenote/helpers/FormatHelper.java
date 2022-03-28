package com.daiancosta.brokeragenote.helpers;

import java.math.BigDecimal;

public class FormatHelper {

    public static BigDecimal stringToBigDecimal(final String value) {
        try {

            final String newValue = value.replace(".", "").replace(",", ".")
                    .replace("-", "")
                    .replace("R$", "")
                    .replace(" ", "");

            return new BigDecimal(newValue);
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }
}
