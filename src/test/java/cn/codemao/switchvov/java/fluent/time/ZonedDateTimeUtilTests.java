package cn.codemao.switchvov.java.fluent.time;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static cn.codemao.switchvov.java.fluent.time.util.ZonedDateTimeUtil.*;
import static java.util.stream.Collectors.joining;

/**
 * @author switch
 * @since 2020/7/31
 */

public class ZonedDateTimeUtilTests {
    private static ZonedDateTime now;

    @BeforeClass
    public static void initClass() {
        now = now();
    }

    @Test
    public void testMonthFirst() {
        ZonedDateTime monthFirst = monthFirst(now);
        System.out.println("monthFirst:" + monthFirst);
        System.out.println("monthFirst-1:" + monthFirst.minusMonths(1L));
    }

    @Test
    public void testMonthLast() {
        ZonedDateTime monthLast = monthLast(now);
        System.out.println("monthLast:" + monthLast.toString());
    }

    @Test
    public void testFromEpochSecond() {
        System.out.println("now:" + now);
        System.out.println("now:" + now.toEpochSecond());
        System.out.println("now:" + fromEpochSecond(now.toEpochSecond()));
    }

    @Test
    public void testParse() {
        System.out.println("now:" + to(now, MILLISECOND_FORMATTER));
        System.out.println("now:" + to(now));
        System.out.println("now:" + to(now, DATE_FORMATTER));
        System.out.println("now:" + to(now, YEAR_MONTH_FORMATTER));
        System.out.println("now:" + from(Date.from(now.toInstant())));
        System.out.println("now:" + parse(to(now)));
        System.out.println("now:" + parse(to(now, YEAR_MONTH_FORMATTER), YEAR_MONTH_FORMATTER));
    }

    @Test
    public void testRange() {
        ZonedDateTime monthFirst = monthFirst(now);
        System.out.println("monthFirst:" + monthFirst);
        ZonedDateTime afterThreeMonth = monthFirst.plusMonths(3);
        System.out.println("afterThreeMonth:" + afterThreeMonth);
        List<ZonedDateTime> ranges = range(monthFirst, afterThreeMonth, ChronoUnit.MONTHS);
        System.out.println("range:\n" + ranges.stream().map(ZonedDateTime::toString).collect(joining("\n")));
    }

    @Test
    public void testFromSystem() {
        System.out.println(fromSystem(LocalDateTime.now()));
    }
}
