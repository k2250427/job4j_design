package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean someBool = true;
        char someChar = 'A';
        short shortNumber = 222;
        int intNumber = 33;
        long longNumber = 123L;
        float floatNumber = 584.11F;
        double doubleNumber = 123.23D;
        LOG.debug("Primitives - boolean: {}, char: {}, short: {}, int: {}, "
                + "long: {}, float: {}, double: {}", someBool, someChar, shortNumber,
                intNumber, longNumber, floatNumber, doubleNumber);
    }
}
