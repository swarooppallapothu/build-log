package com.buildlogger.util;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Swaroop Pallapothu on 26 Mar, 2021
 */
public class ApplicationUtils {

    static final Logger LOGGER = Logger.getLogger(ApplicationUtils.class.getName());

    public static int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NullPointerException | NumberFormatException e) {
            LOGGER.info("Invalid number " + value);
            return 0;
        }
    }

    public static String parseString(String value) {
        return Objects.isNull(value) ? "" : value;
    }


}
