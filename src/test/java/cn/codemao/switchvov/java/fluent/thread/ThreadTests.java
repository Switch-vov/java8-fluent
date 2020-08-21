package cn.codemao.switchvov.java.fluent.thread;

import cn.codemao.switchvov.java.fluent.data.DataSets;
import cn.codemao.switchvov.java.fluent.data.Hero;
import cn.codemao.switchvov.java.fluent.thread.config.ThreadPoolConfig;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author switch
 * @since 2020/8/18
 */
public class ThreadTests {
    private static List<Hero> heroes;
    private static Map<Integer, Hero> heroMapping;
    private static List<Integer> heroIds;

    private static ThreadPoolTaskExecutor taskExecutor;

    @BeforeClass
    public static void initClass() {
        heroes = DataSets.heroes();
        heroMapping = heroes.stream()
                .collect(toMap(Hero::getId, Function.identity(), (key1, key2) -> key2));
        heroIds = heroes.stream().map(Hero::getId).collect(toList());
        taskExecutor = ThreadPoolConfig.taskExecutor();
        taskExecutor.initialize();
    }

    @AfterClass
    public static void destroyClass() {
        try {
            if (taskExecutor != null) {
                taskExecutor.shutdown();
            }
        } catch (Exception e) {
            if (taskExecutor != null) {
                taskExecutor.shutdown();
            }
        }
    }

    public Stream<CompletableFuture<List<Hero>>> getFuturesStream(List<List<Integer>> idSubLists) {
        return idSubLists.stream()
                .map(ids -> CompletableFuture.supplyAsync(
                        () -> ids.parallelStream().map(heroMapping::get).collect(toList()), taskExecutor));
    }

    @Test
    public void test() {
        int partitionSize = 10;
        List<List<Integer>> heroIdSubLists = Lists.partition(heroIds, partitionSize);
        getFuturesStream(heroIdSubLists);
        List<CompletableFuture<Void>> batchFutures = getFuturesStream(heroIdSubLists)
                .map(future -> future.thenApply(this::batchMap))
                .map(future -> future.thenAccept(this::batchInsert))
                .collect(Collectors.toList());
        List<Void> results = batchFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private List<Pair<Integer, String>> batchMap(List<Hero> heroes) {
        return heroes.parallelStream()
                .map(hero -> Pair.of(hero.getId(), hero.getName()))
                .collect(toList());
    }

    private void batchInsert(List<Pair<Integer, String>> idNameList) {
        System.out.println("插入" + idNameList.size() + "条，内容为：" + idNameList);
    }
}
