package cn.geralt.oct20th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class MyTest {

    @DisplayName("FutureTaskTest")
    @Test
    void test0() throws ExecutionException, InterruptedException {
        ThreadLocal<Object> t = new ThreadLocal<Object>();
        t.set("hello from main");
        ExecutorService myPool = Executors.newCachedThreadPool();
        FutureTask<Integer> ft = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(t.get());
                t.set("hello from the ft");
                System.out.println(t.get());
                Thread.sleep(5000);
                return 199;
            }
        });
        myPool.execute(ft);

        System.out.println("hello");
        System.out.println(ft.get());
        System.out.println("world");
        System.out.println(t.get());
    }
}
