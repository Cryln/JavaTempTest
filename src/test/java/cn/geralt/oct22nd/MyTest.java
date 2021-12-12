package cn.geralt.oct22nd;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;


public class MyTest {

    @Test
    void test0(){
        int[] data = {1,4,6,2,5,8,9,3,12,45,0};
        List<Integer> collect = Arrays.stream(data).mapToObj(x -> Integer.valueOf(x)).sorted((a,b)->b-a).collect(Collectors.toList());
        collect.forEach(System.out::println);
        long[] data1 = new long[2];
    }
}
