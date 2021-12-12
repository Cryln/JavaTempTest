package cn.geralt.nov14th;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

public class MyTest {

    static Stream<Arguments> p0(){
        return Stream.of(Arguments.arguments(

        ));
    }

    @DisplayName("__")
    @ParameterizedTest
    @MethodSource("p0")
    void test0(){


    }

}
