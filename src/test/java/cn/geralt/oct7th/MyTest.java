package cn.geralt.oct7th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class MyTest {
    static Stream<Arguments> myParam(){
        return Stream.of(Arguments.arguments(
                (Object) new int[]{7,1,5,3,6,4}
        ),Arguments.arguments(
                (Object) new int[]{7,6,4,3,1}
        ));
    }

    static Stream<Arguments> myParamMatrix(){
        return Stream.of(Arguments.arguments(
                (Object) new int[][] {
                        {1,1,0,0,0},
                        {0,1,0,1,1},
                        {0,0,0,1,1},
                        {1,0,0,0,0},
                        {0,0,1,1,1}}
                    ));
    }

    @DisplayName("股票的最大利润")
    @ParameterizedTest
    @MethodSource("myParam")
    void test0(int[] data){
        int[] dp = new int[data.length];
        int curMin = data[0];
        int result = 0;
        for (int i = 0; i < dp.length; i++) {
            if(i==0){
                dp[i] = 0;
            }
            else{
                dp[i] = Math.max(dp[i-1],data[i]-curMin);
            }
            result = Math.max(result,dp[i]);
            curMin = Math.min(curMin,data[i]);
        }

        System.out.println(result);
    }

    @DisplayName("岛屿数量")
    @ParameterizedTest
    @MethodSource("myParamMatrix")
    void test1(int[][] data){
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if(data[i][j] == 1){
                    dfs(data,i,j);
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    void dfs(int[][] data, int i, int j){
        data[i][j] = 0;
        if(i!=data.length-1 && data[i+1][j]==1){
            dfs(data,i+1,j);
        }
        if(i!=0 && data[i-1][j]==1){
            dfs(data,i-1,j);
        }
        if(j!=data[0].length-1 && data[i][j+1]==1){
            dfs(data,i,j+1);
        }
        if(j != 0 && data[i][j-1]==1){
            dfs(data,i,j-1);
        }
    }



}
