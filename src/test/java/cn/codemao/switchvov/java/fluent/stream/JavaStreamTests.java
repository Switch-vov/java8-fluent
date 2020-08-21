package cn.codemao.switchvov.java.fluent.stream;

import cn.codemao.switchvov.java.fluent.data.DataSets;
import cn.codemao.switchvov.java.fluent.data.Hero;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.*;

/**
 * @author switch
 * @since 2020/8/16
 */
public class JavaStreamTests {
    private static List<Hero> heroes;

    @BeforeClass
    public static void initClass() {
        heroes = DataSets.heroes();
    }

    @Test
    public void t1() {
        // 英雄的主位置一共有几类，分别是什么
        List<String> roleMains = heroes.stream()
                // 映射
                .map(Hero::getRoleMain)
                // 过滤为空的数据
                .filter(Objects::nonNull)
                // 去重
                .distinct()
                // 收集列表
                .collect(toList());
        System.out.println(roleMains.size());
        System.out.println(roleMains);
    }

    @Test
    public void t2() {
        // 英雄按主次位置分组后，输出每个分组有多少英雄，其中：近战英雄有多少位，远程英雄有多少位

        // 主次位置分组的英雄数量
        Map<Pair<String, String>, Long> groupHeroCount = heroes.stream()
                .collect(groupingBy(hero -> Pair.of(hero.getRoleMain(), hero.getRoleAssist()), counting()));

        // 主次分组后，再按攻击分为分组的英雄数量
        Map<Pair<String, String>, Map<String, Long>> groupThenGroupCount = heroes.stream()
                .collect(groupingBy(hero -> Pair.of(hero.getRoleMain(), hero.getRoleAssist()),
                        groupingBy(Hero::getAttackRange, counting())));

        // 遍历输出
        groupThenGroupCount.forEach((groupKey, groupValue) -> {
            Long groupingCount = groupHeroCount.get(groupKey);
            System.out.print("英雄分组key为:" + groupKey + ";英雄数量:" + groupingCount + ";");
            groupValue.forEach((countKey, countValue) -> System.out.print("英雄攻击范围:" + countKey + ";英雄数量:" + countValue + ";"));
            System.out.println();
        });
    }

    @Test
    public void t3() {
        // 求近战英雄HP初始值的加总
        BigDecimal sum = heroes.stream()
                .filter(hero -> "近战".equals(hero.getAttackRange()))
                .map(Hero::getHpStart)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("近战英雄HP初始值的加总为:" + sum);
    }

    @Test
    public void t4() {
        // 通过最小列表收集器获取最小列表
        List<BigDecimal> minAttackGrowth = heroes.stream()
                .map(Hero::getAttackGrowth)
                .collect(new MinListCollector<>());
        System.out.println(minAttackGrowth);
        List<Hero> minHero = heroes.stream()
                .collect(new MinListCollector<>());
        System.out.println(minHero);
    }
}
