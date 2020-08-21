package cn.codemao.switchvov.java.fluent.stream

import cn.codemao.switchvov.java.fluent.data.DataSets
import cn.codemao.switchvov.java.fluent.data.Hero
import org.apache.commons.lang3.tuple.Pair
import org.junit.BeforeClass
import org.junit.Test
import java.math.BigDecimal
import java.util.*

/**
 *
 *
 * @author switch
 * @since 2020/8/16
 */
class KotlinStreamTests {
    companion object {
        private lateinit var heroes: List<Hero>

        @BeforeClass
        @JvmStatic
        fun initClass() {
            heroes = DataSets.heroes()
        }
    }

    @Test
    fun t1() {
        // 英雄的主位置一共有几类，分别是什么
        // 映射
        val roleMains = heroes.map(Hero::getRoleMain)
            // 过滤为空的数据
            .filter(Objects::nonNull)
            // 去重
            .distinct()
        println(roleMains.size)
        println(roleMains)
    }

    @Test
    fun t2() {
        // 英雄按主次位置分组后，输出每个分组有多少英雄，其中：近战英雄有多少位，远程英雄有多少位

        // 主次位置分组的英雄数量
        val groupHeroCount = heroes.groupingBy {
            Pair.of(it.roleMain, it.roleAssist)
        }.eachCount()

        // 主次分组后，再按攻击分为分组的英雄数量
        val groupThenGroupCount = heroes.groupBy {
            Pair.of(it.roleMain, it.roleAssist)
        }.map {
            val value = it.value.groupingBy(Hero::getAttackRange).eachCount()
            Pair.of(it.key, value)
        }.associateBy({ it.left }, { it.value })

        // 遍历输出
        groupThenGroupCount.forEach { (groupKey, groupValue) ->
            val groupingCount = groupHeroCount[groupKey]
            print("英雄分组key为:$groupKey;英雄数量:$groupingCount;")
            groupValue.forEach { (countKey, countValue) ->
                print("英雄攻击范围:$countKey;英雄数量:$countValue;")
            }
            println()
        }
    }

    @Test
    fun t3() {
        // 求近战英雄HP初始值的加总
        val sum = heroes.filter { "近战" == it.attackRange }
            .map(Hero::getHpStart)
            .filter(Objects::nonNull)
            .reduce(BigDecimal::add)
        println("近战英雄HP初始值的加总为:$sum")
    }
}