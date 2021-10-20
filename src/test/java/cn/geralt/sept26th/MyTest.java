package cn.geralt.sept26th;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MyTest {
    static Stream<Arguments> customParamProvider() {
        int[] data = new int[]{3,1,2,5,2,4};
        return Stream.of(
//                arguments("apple"," is tasty, "),
//                arguments("lemon", " is not Okay."),
                arguments(data)
        );
    }

    /**
     * max water
     * @param arr int整型一维数组 the array
     * @return long长整型
     */
    @ParameterizedTest
    @MethodSource("customParamProvider")
    void maxWater (int[] arr) {
        // write code here
        int[] leftMax = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if(i==0){
                leftMax[i] = Math.max(0,arr[i]);
            }else{
                leftMax[i] = Math.max(leftMax[i-1],arr[i]);
            }
        }
        int[] rightMax = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            if(i==arr.length-1){
                rightMax[i] = Math.max(0,arr[i]);
            }else{
                rightMax[i] = Math.max(rightMax[i+1],arr[i]);
            }
        }
        int[] ans = new int[arr.length];
        for (int i = 1; i < ans.length-1; i++) {
            ans[i] = Math.max(Math.min(leftMax[i-1],rightMax[i+1])-arr[i],0);
        }

        for (int i : ans) {
            System.out.println(i);
        }
    }
}
