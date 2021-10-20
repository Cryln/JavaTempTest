package cn.geralt.oct1st;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MyTest {
    private volatile int a = 0;
    static private int TIME = 1000000;

    @DisplayName("volatileTest")
    @Test
    void test0() throws InterruptedException {
        final Thread thread0 = new Thread(() -> {
        synchronized (MyTest.class){
            for (int i = 0; i < TIME; i++) {
                a++;
            }
        }
        });
        final Thread thread1 = new Thread(() -> {
            synchronized (MyTest.class){
                for (int i = 0; i < TIME; i++) {
                    a++;
                }
            }
        });
        thread0.start();
        thread1.start();

        thread0.join();
        thread1.join();
        System.out.println(a);
    }
    @Test
    void test1(){
        AtomicInteger i = new AtomicInteger(1);
    }
    @Test
    void test2(){
        int[] a = {1,2,3,3,4,5};
        final Object[] objects =Arrays.stream(a)
                .boxed()
                .toArray();
        final List<Integer> collect = Arrays.stream(objects)
                .map(x->(Integer)x)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
