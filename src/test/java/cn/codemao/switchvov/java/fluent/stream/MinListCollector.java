package cn.codemao.switchvov.java.fluent.stream;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * 最小列表收集器
 *
 * @author switch
 * @since 2020/8/18
 */
public class MinListCollector<T extends Comparable<? super T>> implements Collector<T, List<T>, List<T>> {
    /**
     * 收集器的特性
     *
     * @see Characteristics
     */
    private final static Set<Characteristics> CHARACTERISTICS = Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    private final static int ZERO = 0;

    /**
     * 最小值
     */
    private final AtomicReference<T> min = new AtomicReference<>();

    @Override
    public Supplier<List<T>> supplier() {
        // supplier参数用于生成结果容器，容器类型为A
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        // accumulator用于消费元素，也就是归纳元素，这里的T就是元素，它会将流中的元素一个一个与结果容器A发生操作
        return (list, element) -> {
            // 获取最小值
            T minValue = min.get();
            if (Objects.isNull(minValue)) {
                // 第一次比较
                list.add(element);
                min.set(element);
            } else if (element.compareTo(minValue) < ZERO) {
                // 发现更小的值
                list.clear();
                list.add(element);
                min.compareAndSet(minValue, element);
            } else if (element.compareTo(minValue) == ZERO) {
                // 与最小值相等
                list.add(element);
            }
        };
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        // combiner用于两个两个合并并行执行的线程的执行结果，将其合并为一个最终结果A
        return (left, right) -> {
            // 最小值列表合并
            List<T> leftList = getMinList(left);
            List<T> rightList = getMinList(right);
            leftList.addAll(rightList);
            return leftList;
        };
    }

    private List<T> getMinList(List<T> list) {
        return list.stream()
                .filter(element -> element.compareTo(min.get()) == ZERO)
                .collect(Collectors.toList());
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        // finisher用于将之前整合完的结果R转换成为A
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        // characteristics表示当前Collector的特征值，这是个不可变Set
        return CHARACTERISTICS;
    }
}
