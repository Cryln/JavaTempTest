package cn.geralt.oct24th;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyTest {
    static Stream<Arguments> get(){
        return Stream.of(Arguments.arguments(
                new int[]{3,2,3,1,2,4,5,5,6}, //nums
                4 //k
        ));
    }

    @ParameterizedTest
    @MethodSource("get")
    void test0(int[] nums,int k){
        final List<Integer> collect = Arrays.stream(nums)
                .mapToObj(x -> Integer.valueOf(x))
                .sorted((a,b)->b-a)
                .collect(Collectors.toList());
        System.out.println(collect.get(k-1));
    }
}
