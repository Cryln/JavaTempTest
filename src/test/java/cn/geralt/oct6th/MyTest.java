package cn.geralt.oct6th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Queue;
import java.util.stream.Stream;

public class MyTest {

    static Stream<Arguments> myParam(){
        return Stream.of(
//          Arguments.arguments((Object) new int[]{0,1,0,2,1,0,1,3,2,1,2,1}),
          Arguments.arguments((Object) new int[]{0,1,0,2,1,0,1,3,2,1,2,1})
        );
    }

    @DisplayName("接雨水")
    @ParameterizedTest
    @MethodSource("myParam")
    void test0(int[] data){
        int[] left = new int[data.length];
        int[] right = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            if(i==0){
                left[i] = 0;
            }else{
                left[i] = Math.max(left[i-1],data[i-1]);
            }
        }
        for (int i = data.length - 1; i >= 0; i--) {
            if(i==data.length-1){
                right[i] = 0;
            }else {
                right[i] = Math.max(right[i+1],data[i+1]);
            }
        }

        int[] ans = new int[data.length];
        int result = 0;

        for (int i = 0; i < ans.length; i++) {
            ans[i] = Math.min(left[i],right[i]);
            result += Math.max(0,ans[i]-data[i]);
            System.out.println(ans[i]);
        }
        System.out.println(result);

    }
}
