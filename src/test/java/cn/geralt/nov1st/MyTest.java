package cn.geralt.nov1st;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyTest {
    static final  int[] a ;
    static {
        a = new int[2];
        a[0] = 1;
        a[1] =2;
    }

    static Stream<Arguments> get(){
        return Stream.of(Arguments.arguments(
            new int[]{1,5,8,3,1,6,8,10}
        ));
    }


    @ParameterizedTest
    @MethodSource("get")
    void test0(int[] input){
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a,b)->b-a);
//        Arrays.stream(input)
//                .mapToObj(Integer::valueOf)
//                .forEach(x->{q.offer(x);});

        for (int i = 0; i < input.length; i++) {
            q.offer(input[i]);
        }

        q.forEach(System.out::println);

        for (int i=0,s = q.size();i<s;i++){
            System.out.println(q.poll());
        }


    }

    @Test
    void test1(){
        HashSet<Integer> set = new LinkedHashSet<>();
        Integer temp = null;
        System.out.println(set.add(12345));
        for (Integer integer : set) {
            temp  = integer;
        }
        Integer temp2 = null;
        System.out.println(set.add(12345));
        for (Integer integer : set) {
            temp2  = integer;
        }
        System.out.println(Objects.equals(temp,temp2));
    }

    @Test
    void Test2() throws InterruptedException {
        String hello = "hello";
        String world = "world";
        new Thread(()->{
            synchronized(hello){
                try {
                    System.out.println(Thread.currentThread().getName()+"got hello_lock");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("??");
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"getting world_lock");
                synchronized (world){
                    System.out.println(Thread.currentThread().getName()+"got world_lock");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(()->{
            synchronized(world){
                try {
                    System.out.println(Thread.currentThread().getName()+ "got world_lock");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"getting hello_lock");
                synchronized (hello){
                    System.out.println(Thread.currentThread().getName()+"got hello_lock");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Thread.currentThread().join();
    }



    @Test
    @DisplayName("ConditionTest")
    void test3() throws InterruptedException, BrokenBarrierException {

        final ReentrantLock lock = new ReentrantLock();
        Condition fizz = lock.newCondition();
        Condition buzz = lock.newCondition();
        Condition number = lock.newCondition();
        Condition enter = lock.newCondition();
        CountDownLatch barrier = new CountDownLatch(3);

        Data num = new Data(1);

        new Thread(()->{
                lock.lock();
                try {
                    while(true){
                        barrier.countDown();
                        fizz.await();
                        System.out.print("fizz");
                        enter.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

        }).start();

        new Thread(()->{
                lock.lock();
                try {
            while(true){
                    barrier.countDown();
                    buzz.await();
                    System.out.print("buzz");
                    enter.signal();
            }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

        }).start();
        new Thread(()->{
                lock.lock();
                try {
            while(true){
                barrier.countDown();
                    number.await();
                    System.out.print(num.value);
                    enter.signal();
            }
                } catch (InterruptedException  e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
        }).start();


        barrier.await();

        // main thread
        lock.lock();
        for (int i = 0; i < 1000; i++) {

//            Thread.sleep(1000);
            if(num.value%3==0){
                fizz.signal();
            }
            if(num.value%5==0){
                buzz.signal();
            }
            if(num.value%3!=0&&num.value%5!=0){
                number.signal();
            }
            enter.await();
            System.out.println();
            num.value++;

        }
        lock.unlock();
    }


    @AllArgsConstructor
    class Data{
        int value;
    }

    @AllArgsConstructor
    class Temp implements Comparable<Temp>{
        Integer value;
        @Override
        public int compareTo(Temp o) {
            return value-o.value;
        }
    }




}
