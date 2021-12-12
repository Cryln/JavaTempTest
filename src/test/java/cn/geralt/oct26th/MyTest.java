package cn.geralt.oct26th;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

public class MyTest {
    static Stream<Arguments> get(){
        return Stream.of(Arguments.arguments(

        ));
    }

    @ParameterizedTest
    @MethodSource("get")
    void test0(){
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999964
        System.out.println(a == b);// false

    }
}
