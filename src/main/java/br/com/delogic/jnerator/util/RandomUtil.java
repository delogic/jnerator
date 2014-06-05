package br.com.delogic.jnerator.util;

import java.util.Date;
import java.util.Random;

/**
 * Random utilities to return numbers or dates to be used for random data
 * generation.
 *
 * @author celio@delogic.com.br
 *
 * @since 04/06/2014
 */
public class RandomUtil {

    private final static Random random     = new Random();
    private final static double yearPeriod = 365.25 * 24 * 60 * 60 * 1000;

    /**
     * Returns a valid date between the period presented. The period is
     * specified in years but can be specified in decimal points, e.g. 0.5 means
     * half year.
     *
     * @param year
     *            in decimal point from 0.00001 to Inf.
     * @return a valid date between year / 2 before or after now.
     */
    public static Date getDateBetween(float year) {
        double timeToBeUsed = yearPeriod / 2 * year * random.nextDouble();
        timeToBeUsed *= random.nextBoolean() ? 1 : -1;
        return new Date((long) (System.currentTimeMillis() + timeToBeUsed));
    }

    /**
     * Returns a valid date from now up to or down to the period presented. The
     * period is specified in years but can be specified in decimal points, e.g.
     * 0.5 means half year. The year can be negative which means past or
     * positive which means future.
     *
     * @param year
     *            in decimal point from 0.00001 to Inf. Positve or negative for
     *            future or past.
     * @return a valid date between year before or after now.
     */
    public static Date getDateTo(float year) {
        double timeToBeUsed = yearPeriod * year * random.nextDouble();
        return new Date((long) (System.currentTimeMillis() + timeToBeUsed));
    }

}
