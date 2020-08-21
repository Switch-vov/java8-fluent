package cn.codemao.switchvov.java.fluent.time.util;

import javax.validation.constraints.NotNull;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoField.*;

/**
 * 带时区时间工具类
 *
 * @author switch
 * @since 2020/7/31
 */
public final class ZonedDateTimeUtil {
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Shanghai");
    public static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+8");

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String MILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSZ";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String YEAR_MONTH_FORMAT = "yyyy-MM";

    public static final DateTimeFormatter STANDARD_FORMATTER = DateTimeFormatter.ofPattern(STANDARD_FORMAT);
    public static final DateTimeFormatter MILLISECOND_FORMATTER = DateTimeFormatter.ofPattern(MILLISECOND_FORMAT);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern(YEAR_MONTH_FORMAT);

    private static final int ZERO_FIELD = 0;
    private static final int ONE_FIELD = 1;

    private static final int ZERO_INT = 0;
    private static final long ONE_LONG = 1L;

    /**
     * 工具类不能创建对象
     */
    private ZonedDateTimeUtil() {

    }

    /**
     * 反序列化{@link String}到{@link ZonedDateTime}对象
     *
     * <p>时区ID，默认为[DEFAULT_ZONE_ID]</p>
     * <p>格式化器，默认为[STANDARD_FORMATTER]</p>
     *
     * @param timeString 字符串
     * @throws java.time.format.DateTimeParseException 解析错误抛出
     * @see ZonedDateTimeUtil#getField(TemporalAccessor, ChronoField)
     */
    public static ZonedDateTime parse(@NotNull String timeString) {
        return parse(timeString, DEFAULT_ZONE_ID, STANDARD_FORMATTER);
    }

    /**
     * 反序列化{@link String}到{@link ZonedDateTime}对象
     *
     * <p>时区ID，默认为[DEFAULT_ZONE_ID]</p>
     *
     * @param timeString 字符串
     * @param formatter  格式化器，默认为[STANDARD_FORMATTER]
     * @throws java.time.format.DateTimeParseException 解析错误抛出
     * @see ZonedDateTimeUtil#getField(TemporalAccessor, ChronoField)
     */
    public static ZonedDateTime parse(@NotNull String timeString, @NotNull DateTimeFormatter formatter) {
        return parse(timeString, DEFAULT_ZONE_ID, formatter);
    }

    /**
     * 反序列化{@link String}到{@link ZonedDateTime}对象
     *
     * @param timeString 字符串
     * @param zoneId     时区ID，默认为[DEFAULT_ZONE_ID]
     * @param formatter  格式化器，默认为[STANDARD_FORMATTER]
     * @throws java.time.format.DateTimeParseException 解析错误抛出
     * @see ZonedDateTimeUtil#getField(TemporalAccessor, ChronoField)
     */
    public static ZonedDateTime parse(@NotNull String timeString, @NotNull ZoneId zoneId, @NotNull DateTimeFormatter formatter) {
        TemporalAccessor accessor = formatter.parse(timeString);
        int year = getField(accessor, YEAR);
        int month = getField(accessor, MONTH_OF_YEAR);
        int day = getField(accessor, DAY_OF_MONTH);
        int hour = getField(accessor, HOUR_OF_DAY);
        int minute = getField(accessor, MINUTE_OF_HOUR);
        int second = getField(accessor, SECOND_OF_MINUTE);
        int nanoSecond = getField(accessor, NANO_OF_SECOND);
        return LocalDateTime.of(year, month, day, hour, minute, second, nanoSecond).atZone(zoneId);
    }

    /**
     * 从TemporalAccessor对象中获取field。
     * <p>已经处理异常情况</p>
     * <p>年月默认为1，其余默认为0</p>
     */
    private static int getField(@NotNull TemporalAccessor accessor, @NotNull ChronoField field) {
        int fieldValue = ZERO_FIELD;
        try {
            fieldValue = accessor.get(field);
        } catch (UnsupportedTemporalTypeException e) {
            switch (field) {
                case MONTH_OF_YEAR:
                case DAY_OF_MONTH: {
                    fieldValue = ONE_FIELD;
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return fieldValue;
    }

    /**
     * 序列化{@link ZonedDateTime}到{@link String}
     * <p>格式化器，默认为{@link ZonedDateTimeUtil#STANDARD_FORMATTER}</p>
     *
     * @param time 时区时间对象
     */
    public static String to(@NotNull ZonedDateTime time) {
        return to(time, STANDARD_FORMATTER);
    }

    /**
     * 序列化{@link ZonedDateTime}到{@link String}
     *
     * @param time      时区时间对象
     * @param formatter 格式化器
     */
    public static String to(@NotNull ZonedDateTime time, @NotNull DateTimeFormatter formatter) {
        return time.format(formatter);
    }

    /**
     * 获取时区时间对应的秒数
     *
     * @param time 时区时间对象
     */
    public static Long toEpochSecond(@NotNull ZonedDateTime time) {
        return time.toEpochSecond();
    }

    /**
     * 获取当前时间
     * <p>时区：东八区</p>
     *
     * @return 当前时间，带时区
     */
    public static ZonedDateTime now() {
        return now(DEFAULT_ZONE_ID);
    }

    /**
     * 获取当前时间
     *
     * @param zoneId 时区
     * @return 当前时间，带时区
     * @see ZonedDateTime
     * @see ZoneId
     */
    public static ZonedDateTime now(@NotNull ZoneId zoneId) {
        return ZonedDateTime.now(zoneId);
    }

    /**
     * {@link Date} 转 {@link ZonedDateTime}
     *
     * <p>时区：东八区</p>
     *
     * @param date date对象
     * @return [ZonedDateTime]对象
     * @see ZonedDateTimeUtil#from(Date, ZoneId)
     */
    public static ZonedDateTime from(@NotNull Date date) {
        return from(date, DEFAULT_ZONE_ID);
    }

    /**
     * {@link Date} 转 {@link ZonedDateTime}
     *
     * @param date   date对象
     * @param zoneId 时区
     * @return [ZonedDateTime]对象
     * @throws DateTimeException 时区错误抛出
     */
    public static ZonedDateTime from(@NotNull Date date, @NotNull ZoneId zoneId) {
        return date.toInstant().atZone(zoneId);
    }

    /**
     * {@link LocalDateTime} 转 {@link ZonedDateTime}
     *
     * <p>时区：东八区</p>
     *
     * @param dateTime LocalDateTime对象
     * @return [ZonedDateTime]对象
     * @see ZonedDateTimeUtil#from(LocalDateTime, ZoneId)
     */
    public static ZonedDateTime from(@NotNull LocalDateTime dateTime) {
        return from(dateTime, DEFAULT_ZONE_ID);
    }

    /**
     * {@link LocalDateTime} 转 {@link ZonedDateTime}
     *
     * @param dateTime LocalDateTime对象
     * @param zoneId   时区
     * @return [ZonedDateTime]对象
     * @throws DateTimeException 时区错误抛出
     */
    public static ZonedDateTime from(@NotNull LocalDateTime dateTime, @NotNull ZoneId zoneId) {
        return dateTime.atZone(zoneId);
    }

    /**
     * {@link LocalDate} 转 {@link ZonedDateTime}
     *
     * <p>时区：东八区</p>
     *
     * @param date LocalDate对象
     * @return [ZonedDateTime]对象
     * @see ZonedDateTimeUtil#from(LocalDate, ZoneId)
     */
    public static ZonedDateTime from(@NotNull LocalDate date) {
        return from(date, DEFAULT_ZONE_ID);
    }

    /**
     * {@link LocalDate} 转 {@link ZonedDateTime}
     *
     * @param date   LocalDate对象
     * @param zoneId 时区
     * @return [ZonedDateTime]对象
     * @throws DateTimeException 时区错误抛出
     */
    public static ZonedDateTime from(@NotNull LocalDate date, @NotNull ZoneId zoneId) {
        return date.atStartOfDay(zoneId);
    }

    /**
     * {@link LocalDateTime} 转 {@link ZonedDateTime}
     * <p>先转系统时区，再转指定时区</p>
     * <p>指定时区默认为：东八区</p>
     *
     * @param dateTime LocalDateTime对象
     * @return [ZonedDateTime]对象
     * @throws DateTimeException 时区错误抛出
     * @see ZonedDateTimeUtil#fromSystem(LocalDateTime, ZoneId)
     */
    public static ZonedDateTime fromSystem(@NotNull LocalDateTime dateTime) {
        return fromSystem(dateTime, DEFAULT_ZONE_ID);
    }

    /**
     * {@link LocalDateTime} 转 {@link ZonedDateTime}
     * <p>先转系统时区，再转指定时区</p>
     *
     * @param dateTime LocalDateTime对象
     * @param zoneId   时区
     * @return [ZonedDateTime]对象
     * @throws DateTimeException 时区错误抛出
     */
    public static ZonedDateTime fromSystem(@NotNull LocalDateTime dateTime, @NotNull ZoneId zoneId) {
        return dateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId);
    }

    /**
     * EpochSecond 转 {@link ZonedDateTime}
     *
     * <p>时区：东八区</p>
     *
     * @param epochSecond 秒级时间戳
     * @return {@link ZonedDateTime}对象
     * @throws DateTimeException 转化错误抛出
     * @see ZonedDateTimeUtil#fromEpochSecond(long, ZoneId)
     */
    public static ZonedDateTime fromEpochSecond(long epochSecond) {
        return fromEpochSecond(epochSecond, DEFAULT_ZONE_ID);
    }

    /**
     * EpochSecond 转 {@link ZonedDateTime}
     *
     * @param epochSecond 秒级时间戳
     * @param zoneId      时区
     * @return {@link ZonedDateTime}对象
     * @throws DateTimeException 转化错误抛出
     */
    public static ZonedDateTime fromEpochSecond(long epochSecond, @NotNull ZoneId zoneId) {
        Instant instant = Instant.ofEpochSecond(epochSecond);
        return instant.atZone(zoneId);
    }

    /**
     * 指定月最开始的{@link ZonedDateTime}
     */
    public static ZonedDateTime monthFirst(@NotNull ZonedDateTime time) {
        return dayFirst(time).withDayOfMonth(ONE_FIELD);
    }

    /**
     * 指定日最开始的{@link ZonedDateTime}
     */
    public static ZonedDateTime dayFirst(@NotNull ZonedDateTime time) {
        return hourFirst(time).withHour(ZERO_FIELD);
    }

    /**
     * 指定小时最开始的{@link ZonedDateTime}
     */
    public static ZonedDateTime hourFirst(@NotNull ZonedDateTime time) {
        return time.withMinute(ZERO_FIELD).withSecond(ZERO_FIELD).withNano(ZERO_FIELD);
    }

    /**
     * 指定月的最后的{@link ZonedDateTime}
     */
    public static ZonedDateTime monthLast(@NotNull ZonedDateTime time) {
        return monthFirst(time).plusMonths(ONE_LONG).minusNanos(ONE_LONG);
    }

    /**
     * 生成 begin 到 end 时间 按 step 步长 unit 单位的列表，endInclude控制右区间的开闭
     *
     * <p>endInclude默认: 开区间</p>
     * <p>步长默认: 1</p>
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @param unit  单位，{@link ChronoUnit}
     * @return 时间列表
     */
    public static List<ZonedDateTime> range(@NotNull ZonedDateTime begin, @NotNull ZonedDateTime end, @NotNull ChronoUnit unit) {
        return range(begin, end, ONE_LONG, unit);
    }


    /**
     * 生成 begin 到 end 时间 按 step 步长 unit 单位的列表，endInclude控制右区间的开闭
     *
     * <p>endInclude默认: 开区间</p>
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @param step  步长
     * @param unit  单位，{@link ChronoUnit}
     * @return 时间列表
     */
    public static List<ZonedDateTime> range(@NotNull ZonedDateTime begin, @NotNull ZonedDateTime end,
                                            long step, @NotNull ChronoUnit unit) {
        return range(begin, end, step, unit, Boolean.FALSE);
    }

    /**
     * 生成 begin 到 end 时间 按 step 步长 unit 单位的列表，endInclude控制右区间的开闭
     *
     * <p>步长默认: 1</p>
     *
     * @param begin      开始时间
     * @param end        结束时间
     * @param unit       单位，{@link ChronoUnit}
     * @param endInclude 控制右区间的开闭, true闭区间, false开区间
     * @return 时间列表
     */
    public static List<ZonedDateTime> range(@NotNull ZonedDateTime begin, @NotNull ZonedDateTime end,
                                            @NotNull ChronoUnit unit, boolean endInclude) {
        return range(begin, end, ONE_LONG, unit, endInclude);
    }

    /**
     * 生成 begin 到 end 时间 按 step 步长 unit 单位的列表，endInclude控制右区间的开闭
     *
     * @param begin      开始时间
     * @param end        结束时间
     * @param step       步长
     * @param unit       单位，{@link ChronoUnit}
     * @param endInclude 控制右区间的开闭, true闭区间, false开区间
     * @return 时间列表
     */
    public static List<ZonedDateTime> range(@NotNull ZonedDateTime begin, @NotNull ZonedDateTime end,
                                            long step, @NotNull ChronoUnit unit, boolean endInclude) {
        Objects.requireNonNull(begin);
        Objects.requireNonNull(end);
        Objects.requireNonNull(unit);
        List<ZonedDateTime> times = new ArrayList<>();
        // 右闭，则 begin ≤ end 时，循环
        // 右开，则 begin ＜ end 时，循环
        while (endInclude && begin.compareTo(end) <= ZERO_INT ||
                !endInclude && begin.compareTo(end) < ZERO_INT) {
            times.add(begin);
            begin = begin.plus(step, unit);
        }
        return times;
    }
}
