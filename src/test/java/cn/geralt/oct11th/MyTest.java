package cn.geralt.oct11th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MyTest {
    static Stream<Arguments> getParameters(){
        return Stream.of(
                Arguments.arguments(
                        "abc123ABCAB","AB","abc"
                )
        );
    }

    @DisplayName("Shopee Exam")
    @ParameterizedTest
    @MethodSource("getParameters")
    void test0(String src, String match, String replace){
        final String s = src.replaceAll(match, replace);
        System.out.println(s);
    }

    public int padovanSequence(int n) {
        // write code here
        if(n<1) return 0;
        else if(n==1){
            return 1;
        }
        else if(n==2){
            return 1;
        }
        else if(n==3){
            return 1;
        }
        else{
            return padovanSequence(n-2) + padovanSequence(n-3);
        }
    }


}
