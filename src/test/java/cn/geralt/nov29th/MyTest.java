package cn.geralt.nov29th;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class MyTest {

    @Test
    void test(){
        System.out.println("hello");
        foo(1,1,1);

    }

    int foo(int a, float b, float c){
        System.out.println("func1");
        return 0;
    }

    float bar(int a, float b, float c){
        System.out.println("func2");
        return 0;

    }


}
