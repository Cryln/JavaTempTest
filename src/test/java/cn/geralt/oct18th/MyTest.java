package cn.geralt.oct18th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyTest {

    static Stream<Arguments> getArguments(){
        return Stream.of(Arguments.arguments(
                19
        ));
    }

    @DisplayName("快乐数")
    @ParameterizedTest
    @MethodSource("getArguments")
    void test0(int num){
        Set<Integer> set = new HashSet<Integer>();
        Integer current = num;

        while(true){
            if(set.contains(current)){
                System.out.println("False");
                return;
            }
            set.add(current);
            String n = current.toString();
            Integer temp = 0;
            for (int i = 0; i < n.length(); i++) {
                temp += (int) Math.pow(n.charAt(i)-'0', 2);
            }
            if (temp == 1) {
                System.out.println("True");
                return;
            }
            current = temp;
        }
    }

    @DisplayName("FizzBuzz")
    @ParameterizedTest
    @ValueSource(ints = {17})
    void test1(int num){
        int[] nums = new int[num];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i+1;
        }
        final List<Integer> collect = Arrays.stream(nums).mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());
        final List<String> collect1 = collect.stream().map(x -> String.valueOf(x)).map((x) -> {
            if (Integer.valueOf(x) % 15 == 0) return "FizzBuzz";
            else if (Integer.valueOf(x) % 5 == 0) return "Buzz";
            else if (Integer.valueOf(x) % 3 == 0) return "Fizz";
            else return x;
        }).collect(Collectors.toList());
        collect1.forEach(System.out::println);
    }

    @DisplayName("加油站")
    @ParameterizedTest
    void test2(){

    }
}
