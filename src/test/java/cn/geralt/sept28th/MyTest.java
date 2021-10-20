package cn.geralt.sept28th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class MyTest {
    @DisplayName("lengthOfLongestSubstring")
    @ParameterizedTest
    @ValueSource(strings = {"asdasdasd","asdasdasdawd"})
    void test0(String s){
        Set<Character> mySet = new HashSet<>();
        int left = 0;
        int ansLeft = 0;
        int max = 0;
        int curr = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while(mySet.contains(c)){
                mySet.remove(s.charAt(left));
                left++;
                curr--;
            }
            mySet.add(c);
            curr ++;
            if(curr>max){
                ansLeft = left;
                max = curr;
            }
        }
        System.out.println(s.substring(ansLeft,ansLeft+max));
        final BigDecimal bigDecimal = new BigDecimal(0);
    }

    @Test
    void test1(){
        Integer a = 129;
        foo(a);
        System.out.println(a);
    }
    void foo(Integer a){
        a += 10;
    }

}
