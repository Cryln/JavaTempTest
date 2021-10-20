package cn.geralt.oct9th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

public class MyTest {

    static Stream<Arguments> getParameters(){
        return Stream.of(Arguments.arguments(
                (Object) new int[][]{
                        {1, 4, 7, 11, 15},
                        {2, 5, 8, 12, 19},
                        {3, 6, 9, 16, 22},
                        {10, 13, 14, 17, 24},
                        {18, 21, 23, 26, 30}
                }, 20
        ));
    }


    @DisplayName("240. 搜索二维矩阵 II")
    @ParameterizedTest
    @MethodSource("getParameters")
    void test0(int[][] data,int target){
        int i,j;
        i = 0;
        j = data[0].length-1;
        int[] result = new int[2];
        boolean flag = false;
        while(i < data.length && j > -1){
            if(data[i][j]==target){
                result[0] = i;
                result[1] = j;
                flag = true;
                break;
            } else if(data[i][j]>target){
                j--;
            }else {
                i++;
            }
        }
        if(flag){
            System.out.println(result[0]+": "+result[1]);
        }
        else{
            System.out.println("not found");
        }
    }


}
