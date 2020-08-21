package cn.codemao.switchvov.java.fluent.thread.config;

import cn.codemao.switchvov.java.fluent.thread.util.ThreadPoolUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author switch
 * @since 2020/8/21
 */
//@Configuration
public class ThreadPoolConfig {
    /**
     * 计算规则：N(thread) = N(cpu) * U(cpu) * (1 + w/c)
     * N(thread)：线程池大小
     * N(cpu)：处理器核数
     * U(cpu)：期望CPU利用率（该值应该介于0和1之间）
     * w/c：是等待时间与计算时间的比率，比如说IO操作即为等待时间，计算处理即为计算时间
     */
    private static final Integer TASK_POOL_SIZE = 50;
    private static final Integer TASK_MAX_POOL_SIZE = 100;
    private static final Integer TASK_QUEUE_CAPACITY = 1000;

//    @Bean("taskExecutor")
//    public ThreadPoolTaskExecutor taskExecutor() {
//        return ThreadPoolUtil.getDefaultExecutor(TASK_POOL_SIZE, TASK_MAX_POOL_SIZE, TASK_QUEUE_CAPACITY);
//    }

    private static final ThreadPoolTaskExecutor TASK_EXECUTOR = ThreadPoolUtil.getDefaultExecutor(
            TASK_POOL_SIZE,
            TASK_MAX_POOL_SIZE,
            TASK_QUEUE_CAPACITY
    );

    public static ThreadPoolTaskExecutor taskExecutor() {
        return TASK_EXECUTOR;
    }
}
