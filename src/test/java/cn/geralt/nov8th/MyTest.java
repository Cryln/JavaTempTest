package cn.geralt.nov8th;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MyTest {

    static Stream<Arguments> p0(){
        return Stream.of(Arguments.arguments(
            new int[]{1}
        ));
    }

    @DisplayName("_meituan_interview_")
    @ParameterizedTest
    @MethodSource("p0")
    void test0(int[] a){
        if(a.length==1){
            System.out.println(a[0]);
            return;
        }

        int left = 0;
        int right = a.length - 1; //inclusive
        int mid = 0;
        while (true){
            mid = (int) Math.ceil((left + right)/2);
            if(
                    (mid==0&& a[mid]>= a[mid+1]) || // num 0
                    (mid == a.length-1 && a[mid]>= a[mid-1] ) || //last one
                    (a[mid]>= a[mid-1] && a[mid]>= a[mid+1])){
                System.out.println(a[mid]);
                return;
            }else if(a[mid]>= a[mid-1] && a[mid] < a[mid+1]){
                left = mid+1;
            }else {
                right = mid - 1;
            }
        }
    }


    @Test
    void test1(){
        List<Object[]> list = new ArrayList<>();
        f(new int[]{1,2,3,4,5},3,0,list);
        for (Object[] objects : list) {
            for (Object object : objects) {
                System.out.println(object);
            }
        }

    }
    public static Stack<Integer> stack = new Stack<Integer>();
    private static void f(int[] data, int size, int cur, List<Object[]> list) {
        // TODO Auto-generated method stub
        if(cur == size) {
//            System.out.println(stack);
            final Object[] objects = stack.toArray();
            list.add(objects);
            return;
        }

        for(int i=0;i<data.length;i++) {
            if(!stack.contains(data[i])) {
                stack.add(data[i]);
                f(data, size, cur+1,list);
                stack.pop();
            }

        }
    }


    static Stream<Arguments> p1(){
        return Stream.of(Arguments.arguments(
           new int[]{1,2,3,4,4,5,5,5}
        ));
    }
    @ParameterizedTest
    @MethodSource("p1")
    void test2(int[] data){
//        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for (int i = 0; i < data.length; i++) {
//            if(map.containsKey(data[i])) {
//                map.put(data[i],map.get(data[i])+1);
//            }else{
//                map.put(data[i],1);
//            }
//        }
//
//        map.forEach();
        int[] table = new int[data.length];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < data.length; i++) {
            int index = 0;
            if(map.containsKey(data[i])){
                index = map.get(data[i]);
            }
            else{
                map.put(data[i],map.size());
                index = map.size()-1;
            }
            table[index]++;
        }
        Arrays.sort(table);
        while(table[table.length-2]!=0){
            table[table.length-1]-=table[table.length-2];
            table[table.length-2] = 0;
            Arrays.sort(table);
        }
        System.out.println(table[table.length-1]);
    }
}
