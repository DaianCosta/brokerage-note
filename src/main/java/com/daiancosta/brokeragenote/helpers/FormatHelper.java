package com.daiancosta.brokeragenote.helpers;

import java.math.BigDecimal;

public class FormatHelper {

    public static BigDecimal stringToBigDecimal(final String value) {
        try {

            String newValue = value;
            if (value.length() <= 8) {
                newValue = value.replace(",", ".");
            }

            return new BigDecimal(newValue);
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }
}
