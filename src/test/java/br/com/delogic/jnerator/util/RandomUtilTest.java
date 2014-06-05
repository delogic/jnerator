package br.com.delogic.jnerator.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class RandomUtilTest extends Assert {

    private double yearPeriod = 365.25 * 24 * 60 * 60 * 1000;

    @Test
    public void testGetDateBetween() {
        Date dayFrom = new Date((long) (System.currentTimeMillis() - yearPeriod));
        Date dayTo = new Date((long) (System.currentTimeMillis() + yearPeriod));

        for (int i = 0; i < 10000000; i++) {
            Date randomDate = RandomUtil.getDateBetween(2);
            assertTrue(randomDate.compareTo(dayFrom) > 0);
            assertTrue(randomDate.compareTo(dayTo) < 0);
        }

    }

    @Test
    public void testGetDateTo() {
        Date dayFrom = new Date((long) (System.currentTimeMillis() - yearPeriod));
        Date dayTo = new Date((long) (System.currentTimeMillis() + yearPeriod));

        for (int i = 0; i < 10000000; i++) {
            Date futureRandomDate = RandomUtil.getDateTo(1);
            assertTrue(futureRandomDate.compareTo(new Date()) > 0);
            assertTrue(futureRandomDate.compareTo(dayTo) < 0);

            Date pastRandomDate = RandomUtil.getDateTo(-1);
            assertTrue(pastRandomDate.compareTo(dayFrom) > 0);
            assertTrue(pastRandomDate.compareTo(new Date()) < 0);
        }

    }

}
