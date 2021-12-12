package cn.geralt.nov8th;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;


public class Solution {
    @ParameterizedTest
    @ValueSource(strings = {"1111"})
    void test0(String s){

        System.out.println(getRes(s));
    }
    public static int getRes(String input){
        if(input==null||input.equals("")){
            return 0;
        }
        int len = input.length();
        int[] dp = new int[len+1];
        dp[len] = 1;
        for(int i=len-1;i>=0;i--){
            if(input.charAt(i)=='0'){
                dp[i] = 0;
            }else{
                dp[i] = dp[i+1];
                if(i<len-1&&(input.charAt(i)-'0')*10+input.charAt(i+1)-'0'<27){
                    dp[i] += dp[i+2];
                }
            }
        }

        return dp[0];
    }
    public static int get(char[] arr,int i){
        if(i==arr.length) return 1;
        if(arr[i]=='0') return 0;
        long res = get(arr,i+1);
        if(i<arr.length-1&&(arr[i]-'0')*10+arr[i+1]-'0'<27){
            res += get(arr,i+2);
            res %= 1000000007;
        }
        return (int)res;
    }

    @Test
    void test2(){
        final List<int[]> foo = foo(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(foo.size());
        for (int[] ints : foo) {
            for (int i = 0; i < ints.length; i++) {
                System.out.print(ints[i]);
            }
            System.out.println();

        }
    }

    public static List<int[]>  foo(int[] data) {

        List<int[]> ans = new ArrayList<>();

        int nCnt = data.length;
        int nBit = (0xFFFFFFFF >>> (32 - nCnt));

        for (int i = 1; i <= nBit; i++) {
            int[] temp = new int[nCnt];
            for (int j = 0; j < nCnt; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
//                    System.out.print(data[j]);
                    temp[j] = data[j];
                }
            }
            ans.add(temp);
        }
        return ans;
    }


}