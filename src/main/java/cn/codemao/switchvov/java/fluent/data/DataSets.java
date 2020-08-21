package cn.codemao.switchvov.java.fluent.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 分享用到的数据集
 *
 * @author switch
 * @since 2020/8/11
 */
public final class DataSets {
    private static final List<Hero> heroes;

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    static {
        heroes = new ArrayList<>();
        heroes.add(new Hero(10000, "夏侯惇", value("7350"), value("288.8"), value("3307"), value("321"), value("11.57"), value("159"), "近战", "坦克", "战士", date("2016-07-19")));
        heroes.add(new Hero(10001, "钟无艳", value("7000"), value("275"), value("3150"), value("318"), value("11"), value("164"), "近战", "战士", "坦克", null));
        heroes.add(new Hero(10002, "张飞", value("8341"), value("329.4"), value("3450"), value("301"), value("10.57"), value("153"), "近战", "坦克", "辅助", null));
        heroes.add(new Hero(10003, "牛魔", value("8476"), value("352.8"), value("3537"), value("273"), value("8.357"), value("156"), "近战", "坦克", "辅助", date("2015-11-24")));
        heroes.add(new Hero(10004, "吕布", value("7344"), value("270"), value("3564"), value("343"), value("12.36"), value("170"), "近战", "战士", "坦克", date("2015-12-22")));
        heroes.add(new Hero(10005, "亚瑟", value("8050"), value("316.3"), value("3622"), value("346"), value("13"), value("164"), "近战", "战士", "坦克", null));
        heroes.add(new Hero(10006, "芈月", value("6164"), value("281.5"), value("3105"), value("289"), value("9.786"), value("152"), "远程", "法师", "坦克", date("2015-12-08")));
        heroes.add(new Hero(10007, "程咬金", value("8611"), value("369.6"), value("3437"), value("316"), value("11.07"), value("161"), "近战", "坦克", "战士", null));
        heroes.add(new Hero(10008, "廉颇", value("9328"), value("412.1"), value("3558"), value("286"), value("8.786"), value("163"), "近战", "坦克", null, null));
        heroes.add(new Hero(10009, "东皇太一", value("7669"), value("319.1"), value("3201"), value("286"), value("8.786"), value("163"), "近战", "坦克", null, date("2017-03-30")));
        heroes.add(new Hero(10010, "庄周", value("8149"), value("345.6"), value("3311"), value("297"), value("9.071"), value("170"), "近战", "辅助", "坦克", null));
        heroes.add(new Hero(10011, "太乙真人", value("6835"), value("242.3"), value("3443"), value("284"), value("9.286"), value("154"), "近战", "辅助", "坦克", date("2016-11-24")));
        heroes.add(new Hero(10012, "白起", value("8638"), value("366.3"), value("3510"), value("288"), value("9.286"), value("158"), "近战", "坦克", null, null));
        heroes.add(new Hero(10013, "雅典娜", value("6264"), value("243"), value("2862"), value("327"), value("11.79"), value("162"), "近战", "战士", "坦克", null));
        heroes.add(new Hero(10014, "刘邦", value("8073"), value("336"), value("3369"), value("302"), value("10.29"), value("158"), "近战", "坦克", "辅助", date("2016-04-26")));
        heroes.add(new Hero(10015, "刘禅", value("8581"), value("372.6"), value("3364"), value("295"), value("8.357"), value("178"), "近战", "坦克", null, null));
        heroes.add(new Hero(10016, "墨子", value("7176"), value("292.4"), value("3083"), value("328"), value("10.5"), value("181"), "近战", "法师", "坦克", null));
        heroes.add(new Hero(10017, "项羽", value("8057"), value("380.1"), value("3535"), value("306"), value("10.64"), value("157"), "近战", "坦克", null, null));
        heroes.add(new Hero(10018, "关羽", value("7107"), value("270.4"), value("3322"), value("343"), value("12.36"), value("170"), "近战", "战士", "坦克", date("2016-06-28")));
        heroes.add(new Hero(10019, "后羿", value("5986"), value("200.3"), value("3182"), value("396"), value("16.79"), value("161"), "远程", "射手", null, null));
        heroes.add(new Hero(10020, "马可波罗", value("5584"), value("181.6"), value("3041"), value("362"), value("13.36"), value("175"), "远程", "射手", null, date("2016-08-23")));
        heroes.add(new Hero(10021, "鲁班七号", value("5989"), value("184.9"), value("3401"), value("400"), value("16.14"), value("174"), "远程", "射手", null, null));
        heroes.add(new Hero(10022, "李元芳", value("5725"), value("194.1"), value("3007"), value("396"), value("16.79"), value("161"), "远程", "射手", null, date("2016-04-12")));
        heroes.add(new Hero(10023, "孙尚香", value("6014"), value("198.5"), value("3235"), value("411"), value("17.07"), value("172"), "远程", "射手", null, null));
        heroes.add(new Hero(10024, "黄忠", value("5898"), value("194.6"), value("3173"), value("403"), value("16.5"), value("172"), "远程", "射手", null, null));
        heroes.add(new Hero(10025, "狄仁杰", value("5710"), value("176.3"), value("3242"), value("376"), value("14.79"), value("169"), "远程", "射手", null, null));
        heroes.add(new Hero(10026, "虞姬", value("5669"), value("192.3"), value("2977"), value("407"), value("17.29"), value("165"), "远程", "射手", null, date("2016-05-24")));
        heroes.add(new Hero(10027, "成吉思汗", value("5799"), value("198"), value("3027"), value("394"), value("15"), value("184"), "远程", "射手", null, date("2016-09-27")));
        heroes.add(new Hero(10028, "嬴政", value("5471"), value("167.6"), value("3125"), value("309"), value("10.86"), value("157"), "远程", "法师", null, null));
        heroes.add(new Hero(10029, "武则天", value("5037"), value("155.5"), value("2860"), value("297"), value("8.857"), value("173"), "远程", "法师", null, null));
        heroes.add(new Hero(10030, "露娜", value("6612"), value("256.5"), value("3021"), value("335"), value("12.29"), value("163"), "近战", "战士", "法师", null));
        heroes.add(new Hero(10031, "甄姬", value("5584"), value("181.6"), value("3041"), value("296"), value("9.357"), value("165"), "远程", "法师", null, null));
        heroes.add(new Hero(10032, "妲己", value("5824"), value("185.4"), value("3229"), value("293"), value("8.786"), value("170"), "远程", "法师", null, null));
        heroes.add(new Hero(10033, "干将莫邪", value("5583"), value("171"), value("3189"), value("292"), value("9.5"), value("159"), "远程", "法师", null, date("2017-05-22")));
        heroes.add(new Hero(10034, "姜子牙", value("5399"), value("174.4"), value("2958"), value("317"), value("10.64"), value("168"), "远程", "法师", "辅助", null));
        heroes.add(new Hero(10035, "王昭君", value("5429"), value("167.6"), value("3083"), value("296"), value("9.357"), value("165"), "远程", "法师", null, null));
        heroes.add(new Hero(10036, "诸葛亮", value("5655"), value("180"), value("3135"), value("287"), value("9.357"), value("156"), "远程", "法师", null, null));
        heroes.add(new Hero(10037, "不知火舞", value("6014"), value("198.5"), value("3235"), value("293"), value("8.786"), value("170"), "近战", "法师", "刺客", date("2016-05-12")));
        heroes.add(new Hero(10038, "貂蝉", value("5611"), value("185.1"), value("3019"), value("287"), value("8.571"), value("167"), "近战", "法师", "刺客", date("2015-12-15")));
        heroes.add(new Hero(10039, "孙膑", value("6811"), value("257.4"), value("3208"), value("328"), value("10.86"), value("176"), "远程", "辅助", "法师", null));
        heroes.add(new Hero(10040, "安琪拉", value("5994"), value("190.8"), value("3323"), value("293"), value("8.786"), value("170"), "远程", "法师", null, null));
        heroes.add(new Hero(10041, "小乔", value("5916"), value("202"), value("3088"), value("263"), value("7.857"), value("153"), "远程", "法师", null, null));
        heroes.add(new Hero(10042, "周瑜", value("5513"), value("172.9"), value("3093"), value("298"), value("9.857"), value("160"), "远程", "法师", null, date("2015-11-10")));
        heroes.add(new Hero(10043, "张良", value("5799"), value("198"), value("3027"), value("293"), value("8.786"), value("170"), "远程", "法师", null, date("2015-10-26")));
        heroes.add(new Hero(10044, "高渐离", value("6165"), value("217.4"), value("3122"), value("290"), value("9.071"), value("163"), "远程", "法师", null, null));
        heroes.add(new Hero(10045, "扁鹊", value("6703"), value("249.9"), value("3205"), value("309"), value("10.07"), value("168"), "远程", "法师", "辅助", null));
        heroes.add(new Hero(10046, "钟馗", value("6280"), value("204.3"), value("3420"), value("278"), value("8.286"), value("162"), "近战", "法师", "战士", date("2016-03-24")));
        heroes.add(new Hero(10047, "大乔", value("5399"), value("174.4"), value("2958"), value("305"), value("9.786"), value("168"), "近战", "辅助", null, date("2017-02-28")));
        heroes.add(new Hero(10048, "鬼谷子", value("7107"), value("270.4"), value("3322"), value("297"), value("9.643"), value("162"), "近战", "辅助", null, date("2017-06-29")));
        heroes.add(new Hero(10049, "蔡文姬", value("5910"), value("190.9"), value("3238"), value("292"), value("9.5"), value("159"), "远程", "辅助", null, date("2016-07-08")));
        heroes.add(new Hero(10050, "花木兰", value("5397"), value("179.4"), value("2886"), value("362"), value("14"), value("166"), "近战", "战士", "刺客", date("2016-01-01")));
        heroes.add(new Hero(10051, "赵云", value("6732"), value("247.5"), value("3267"), value("380"), value("14.79"), value("173"), "近战", "战士", "刺客", null));
        heroes.add(new Hero(10052, "橘石京", value("7000"), value("275"), value("3150"), value("347"), value("13"), value("165"), "近战", "刺客", "战士", null));
        heroes.add(new Hero(10053, "李白", value("5483"), value("179.6"), value("2968"), value("330"), value("11.5"), value("169"), "近战", "刺客", "战士", date("2016-03-01")));
        heroes.add(new Hero(10054, "韩信", value("5655"), value("190.5"), value("2988"), value("386"), value("15.29"), value("172"), "近战", "刺客", "战士", null));
        heroes.add(new Hero(10055, "杨戬", value("7420"), value("291.5"), value("3339"), value("325"), value("11.36"), value("166"), "近战", "战士", null, date("2016-10-11")));
        heroes.add(new Hero(10056, "达摩", value("7140"), value("280.5"), value("3213"), value("355"), value("13.14"), value("171"), "近战", "战士", null, null));
        heroes.add(new Hero(10057, "孙悟空", value("6585"), value("235.1"), value("3293"), value("349"), value("13"), value("167"), "近战", "战士", "刺客", null));
        heroes.add(new Hero(10058, "刘备", value("6900"), value("262.5"), value("3225"), value("363"), value("14.29"), value("163"), "远程", "战士", null, date("2016-02-02")));
        heroes.add(new Hero(10059, "曹操", value("7473"), value("286.1"), value("3467"), value("361"), value("13.36"), value("174"), "近战", "战士", null, null));
        heroes.add(new Hero(10060, "典韦", value("7516"), value("291.6"), value("3434"), value("345"), value("12.64"), value("168"), "近战", "战士", null, null));
        heroes.add(new Hero(10061, "宫本武藏", value("6210"), value("236.3"), value("2902"), value("330"), value("12.36"), value("157"), "近战", "战士", null, date("2015-10-30")));
        heroes.add(new Hero(10062, "老夫子", value("7155"), value("270.4"), value("3370"), value("329"), value("11.5"), value("168"), "近战", "战士", null, null));
        heroes.add(new Hero(10063, "哪吒", value("7268"), value("270.4"), value("3483"), value("320"), value("11.5"), value("159"), "近战", "战士", null, date("2017-01-12")));
        heroes.add(new Hero(10064, "阿轲", value("5968"), value("192.8"), value("3269"), value("427"), value("17.86"), value("177"), "近战", "刺客", null, null));
        heroes.add(new Hero(10065, "娜可露露", value("6205"), value("211.9"), value("3239"), value("385"), value("15.14"), value("173"), "近战", "刺客", null, date("2016-02-22")));
        heroes.add(new Hero(10066, "兰陵王", value("6232"), value("210"), value("3292"), value("388"), value("15.5"), value("171"), "近战", "刺客", null, null));
        heroes.add(new Hero(10067, "铠", value("6700"), value("237.5"), value("3375"), value("328"), value("10.86"), value("176"), "近战", "战士", "坦克", null));
        heroes.add(new Hero(10068, "百里守约", value("5611"), value("185.1"), value("3019"), value("410"), value("15.86"), value("188"), "远程", "射手", "刺客", date("2017-08-08")));
    }

    private static BigDecimal value(String value) {
        return new BigDecimal(value);
    }

    private static LocalDate date(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static List<Hero> heroes() {
        return heroes;
    }
}
