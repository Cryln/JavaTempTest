package cn.geralt.sept23th;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MyTest {

    static Stream<Arguments> customParamProvider() {
        return Stream.of(
//                arguments("apple"," is tasty, "),
//                arguments("lemon", " is not Okay."),
                arguments("I like coding!","You like cooking!")
        );
    }

    /**
     * longest common substring
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    @ParameterizedTest
    @MethodSource("customParamProvider")
    @DisplayName("longest common substring")
    void lcs(String str1,String str2){
        int m,n;
        m = str1.length();
        n = str2.length();
        int max = 0;
        int end = -1;
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    int temp = dp[i-1][j-1]+1;
                    if(temp>max){
                        max = temp;
                        end = j;
                    }
                    dp[i][j] = temp;
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        System.out.println(max);
        System.out.println(str2.substring(end-max,end));
    }


    @Test
    void test2() throws InterruptedException {
        AtomicInteger int1 = new AtomicInteger(0);
        final Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    int1.accumulateAndGet(10, Integer::sum);
                }
            }
        });
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    int1.accumulateAndGet(10, Integer::sum);
                }
            }
        });
        thread0.start();
        thread1.start();
        thread0.join();
        thread1.join();
        System.out.println(int1);
    }
}
