package cn.geralt.sept26th;


import java.util.Arrays;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        int times = 1000000000;

        System.out.println("========== i++ ==========");
        long t2_start = System.nanoTime();
        for (int i = 0; i < times; i++) ;
        long t2_end = System.nanoTime();
        System.out.println(t2_end - t2_start);

        System.out.println("========== ++i ==========");
        long t1_start = System.nanoTime();
        for (int i = 0; i < times; ++i);
        long t1_end = System.nanoTime();
        System.out.println(t1_end - t1_start);

    }

}