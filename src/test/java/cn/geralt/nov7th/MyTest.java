package cn.geralt.nov7th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MyTest {

    @DisplayName("JSTAT test")
    @Test
    void test0() throws InterruptedException {
        while(true){
            Thread.sleep(1000);
            System.out.println("123");
        }
    }


//    volatile int a = 0;
//    static Unsafe u = Unsafe.getUnsafe();
//    static Long aOffset ;
//    static {
//        Class clazz = MyTest.class;
//        try {
//            aOffset = u.objectFieldOffset(clazz.getField("a"));
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//    }


//    public static void main(String[] args) throws InterruptedException {
////        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
////        HashMap<Integer,Integer> map2 = new HashMap<>();
//        final MyTest myTest = new MyTest();
//
//        final Thread thread = new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                while(!u.compareAndSwapInt(myTest,aOffset,myTest.a,myTest.a+1));
//            }
//        });
//        final Thread thread2 = new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                while(!u.compareAndSwapInt(myTest,aOffset,myTest.a,myTest.a+1));
//            }
//        });
//        thread.start();
//        thread2.start();
//        thread.join();
//        thread2.join();
//        System.out.println(myTest.a);
//    }

}
