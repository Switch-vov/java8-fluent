package cn.codemao.switchvov.java.fluent.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @author switch
 * @since 2020/8/18
 */
public class OptionalTests {
    @Test
    public void t1() {
        // orElse
        System.out.println(Optional.ofNullable(null).orElse("张三"));
        System.out.println(Optional.ofNullable(null).orElseGet(() -> "李四"));
        System.out.println(Optional.ofNullable("王五").orElseThrow(NullPointerException::new));
    }

    @Test
    public void t2() {
        // isPresent
        Optional<String> name = Optional.ofNullable("张三");
        if (name.isPresent()) {
            System.out.println(name.get());
        }
    }

    @Test
    public void t3() {
        // map
        Optional<Integer> number = Optional.of("123456").map(Integer::valueOf);
        if (number.isPresent()) {
            System.out.println(number.get());
        }
    }

    @Test
    public void t4() {
        // flatMap
        Optional<Integer> number = Optional.of("123456").flatMap(s -> Optional.of(Integer.valueOf(s)));
        if (number.isPresent()) {
            System.out.println(number.get());
        }
    }

    @Test
    public void t5() {
        // 过滤
        String number = "123456";
        String filterNumber = Optional.of(number).filter(s -> !s.equals(number)).orElse("654321");
        System.out.println(filterNumber);
    }
}
