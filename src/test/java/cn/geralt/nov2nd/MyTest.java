package cn.geralt.nov2nd;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class MyTest {
    static Stream<Arguments> get(){
        return Stream.of(Arguments.arguments(
//                new int[]{1 ,2 ,3 ,4, 5, 6 ,7},
//                (Object) new int[]{999999 ,33 ,91 ,139 ,193 ,258 ,262 ,308,318,324,361,401,403,435,513,572,634,646,733,
//                        778,785,827,902,964,1015,1078,1144,1164,1212,1250,1277,1354,1445,1503,1512,1552,1598,1665,1706,
//                        1715,1738,1758,1834,1859,1862,1919,2004,2026,2087,2183,2259,2267,2270,2359,2411,2508,2527,2548,
//                        2568,2644,2686,2758,2822,2914,2988,2999,3073,3104,3188,3188,3284,3289,3309,3394,3485,3576,3595,
//                        3620,3654,3706,3764,3844,3935,3981,4062,4063,4063,4079,4118,4188,4246,4325,4345,4394,4456,4554,
//                        4632,4669,4678,4692,4744,4774,4792,4833,4929,4937,4954,5021,5049,5145,5176,5263,5346,5408,5507,
//                        5555,5589,5680,5719,5738,5775,5797,5868,5870,5920,5981,6061,6066,6117,6201,6226,6266,6284,6366,
//                        6397,6439,6482,6484,6559,6599,6652,6672,6760,6777,6811,6851,6944,6973,7009,7068,7096,7147,7221,
//                        7310,7357,7404,7488,7506,7557,7632,7706,7777,7872,7962,8061,8093,8159,8230,8282,8297,8377,8404,
//                        8474,8474,8500,8541,8615,8634,8722,8791,8803,8860,8895,8962,9036,9090,9117,9198,9216,9294,9341,
//                        9389,9446,9466,9544,9551,9576,9615,9675,9683,9768,9799,9836,9843,9846,9869,9906,9906,9937,10008,
//                        10065,10105,10107,10131,10168,10267,10329,10368,10379,10440,10508,10566,10617,10652,10665,10686,
//                        10745,10772,10817,10874,10898,10940,11001,11007,11049,11101,11157,11219,11243,11260,11293,11332,
//                        11396,11439,11502,11587,11601,11629,11689,11751,11841,11914,11944,12002,12085,12117,12136,12139,
//                        12220,12234,12290,12342,12421,12428,12500,12563,12573,12671,12689,12770,12798,12854,12926,12995,
//                        13091,13132,13161,13182,13271,13348,13374,13394,13439,13443,13495,13529,13569,13580,13629,13691,
//                        13768,13865,13901,13946,14030,14112,14118,14214,14267,14273,14364,14456,14520,14617,14657,14732,
//                        14830,14920,15010,15036,15063,15141,15151,15155,15202,15293,15320,15378,15468,15524,15605,15702,
//                        15773,15868,15915,15927,15967,15991,16006,16009,16049,16084,16180,16210,16267,16296,16325,16403,
//                        16408,16419,16499,16587,16650,16730,16764,16848,16873,16917,16982,17007,17083,17143,17229,17279,
//                        17321,17413,17444,17524,17579,17614,17702,17751,17810,17847,17849,17897,17900,17919,18014,18080,
//                        18090,18105}
        ));
    }

    @DisplayName("fizzbuzz")
    @ParameterizedTest
    @MethodSource("get")
    void test0() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition fizz = lock.newCondition();
        Condition buzz = lock.newCondition();
        Condition number = lock.newCondition();
        Condition enter = lock.newCondition();
        CountDownLatch latch = new CountDownLatch(3);

        Data num = new Data(1);

        new Player("fizz",fizz,lock,latch,enter).start();
        new Player("buzz",buzz,lock,latch,enter).start();
        new Player(num,number,lock,latch,enter).start();

//        Thread.sleep(1000);
        latch.await();

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

    class Player extends Thread{
        private final Object task;
        private final Condition condition;
        private final ReentrantLock lock;
        private final CountDownLatch latch;
        private final Condition done;
        public Player(Object _task, Condition _condition, ReentrantLock _lock, CountDownLatch _latch,Condition _done){
            task = _task;
            condition = _condition;
            lock = _lock;
            latch = _latch;
            done = _done;
        }
        @Override
        public void run() {
            lock.lock();
            try {
//                barrier.await();
                latch.countDown();
                while(true){
                    condition.await();
                    System.out.print(task.toString());
                    done.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    @AllArgsConstructor
    class Data{
        int value;

        @Override
        public String toString() {
            return ""+value;
        }
    }



    @Test
    void test1(){
        Integer a = 33;
        Integer b = 33;
        System.out.println(a==b);
        Integer c = 333;
        Integer d = 333;
        Integer e = 666;
        System.out.println(e==d+c);
    }

    @DisplayName("ClassLoader Test")
    @Test
    void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myclassLoader  = new MyClassLoader();
        final Class<?> aClass = myclassLoader.findClass("",false);
        final Object o = aClass.getDeclaredConstructor(Integer.class,String.class).newInstance(new Object[]{Integer.valueOf(3),"hhh"});
        System.out.println(Temp.class.equals(aClass));
        System.out.println(o);
    }
    class MyClassLoader extends ClassLoader {
        protected Class<?> findClass(String name, boolean resolve) throws ClassNotFoundException {
            File f  = new File("D:\\Codes\\Java\\JavaLearn\\JavaTempTest\\target\\test-classes\\cn\\geralt\\nov2nd\\Temp.class");
            byte[] bytes = null;
            try {
                FileInputStream fis = new FileInputStream(f);
                bytes = fis.readAllBytes();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return defineClass("cn.geralt.nov2nd.Temp",bytes,0,bytes.length);
        }
    }


    @DisplayName("Proxy")
    @Test
    void test3(){
        Object o = new Object();
        Object o2 = getProxy(o);
        System.out.println(o2);
    }
    Object getProxy(Object o){
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), new Class[]{}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("haha");
                final Object invoke = method.invoke(o, args);
                System.out.println("xixi");

                return invoke;
            }
        });
    }

    @Test
    void test4(){
        final ExecutorService executorService = Executors.newCachedThreadPool();
        new ThreadPoolExecutor(4,2,100L,TimeUnit.SECONDS,new ArrayBlockingQueue<>(10),(a,b)->{
            a.run();
        });
    }


    @ParameterizedTest
    @MethodSource("get")
    void test5(int[] input){
        boolean flag = (input[0]<input[input.length-1]);
        if(flag) {
            System.out.println(input[0]);
            return;
        }
        else{

            int mid;
            int left = 0;
            int right = input.length-1;
            while(true){
                int temp = (left + right)/2 + 1;
                mid = input[temp];
                if(mid<input[0]){ // right part
                    right = mid;
                    if(mid<input[temp-1]){
                        System.out.println(mid);
                        return;
                    }
                }else{  // left part
                    left = mid;
                    if(mid>input[temp+1]){
                        System.out.println(input[temp+1]);
                        return;
                    }
                }



            }


        }
    }

}
