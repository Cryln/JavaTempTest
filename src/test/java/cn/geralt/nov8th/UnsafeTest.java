package cn.geralt.nov8th;

import lombok.Data;
import lombok.Getter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeTest {

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    static final Unsafe u = reflectGetUnsafe();
    static long AOFFSET;

    static {
        try {
            AOFFSET = u.objectFieldOffset(UnsafeTest.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Getter
    private volatile int value;

    public UnsafeTest(int _value) {
        value = _value;
    }

    public void increase(){
        while(!u.compareAndSwapInt(this, AOFFSET, value, value + 1));
    }

    public static void main(String[] args) throws InterruptedException {
        final UnsafeTest u = new UnsafeTest(0);
        final Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                u.increase();
            }
        });
        final Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                u.increase();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(u.getValue());
    }
}
