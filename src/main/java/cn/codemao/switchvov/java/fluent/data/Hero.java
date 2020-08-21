package cn.codemao.switchvov.java.fluent.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 英雄
 *
 * @author switch
 * @since 2020/8/11
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Hero implements Comparable<Hero> {
    /**
     * ID
     */
    private Integer id;

    /**
     * 英雄名称
     */
    private String name;

    /**
     * HP最大值
     */
    private BigDecimal hpMax;

    /**
     * HP每级成长
     */
    private BigDecimal hpGrowth;

    /**
     * HP初始值
     */
    private BigDecimal hpStart;

    /**
     * 攻击最大值
     */
    private BigDecimal attackMax;

    /**
     * 攻击每级成长
     */
    private BigDecimal attackGrowth;

    /**
     * 攻击初始值
     */
    private BigDecimal attackStart;

    /**
     * 攻击范围:近战;远程
     */
    private String attackRange;

    /**
     * 主位:坦克;战士;法师;刺客;射手;辅助
     */
    private String roleMain;

    /**
     * 次位:坦克;战士;法师;刺客;射手;辅助
     */
    private String roleAssist;

    /**
     * 生日
     */
    private LocalDate birthday;

    @Override
    public int compareTo(@NotNull Hero hero) {
        // 只比较攻击增长率
        return this.attackGrowth.compareTo(hero.attackGrowth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hero)) {
            return false;
        }
        Hero hero = (Hero) o;
        return Objects.equals(getId(), hero.getId()) &&
                Objects.equals(getName(), hero.getName()) &&
                Objects.equals(getHpMax(), hero.getHpMax()) &&
                Objects.equals(getHpGrowth(), hero.getHpGrowth()) &&
                Objects.equals(getHpStart(), hero.getHpStart()) &&
                Objects.equals(getAttackMax(), hero.getAttackMax()) &&
                Objects.equals(getAttackGrowth(), hero.getAttackGrowth()) &&
                Objects.equals(getAttackStart(), hero.getAttackStart()) &&
                Objects.equals(getAttackRange(), hero.getAttackRange()) &&
                Objects.equals(getRoleMain(), hero.getRoleMain()) &&
                Objects.equals(getRoleAssist(), hero.getRoleAssist()) &&
                Objects.equals(getBirthday(), hero.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getHpMax(), getHpGrowth(), getHpStart(),
                getAttackMax(), getAttackGrowth(), getAttackStart(), getAttackRange(), getRoleMain(), getRoleAssist(), getBirthday());
    }
}
