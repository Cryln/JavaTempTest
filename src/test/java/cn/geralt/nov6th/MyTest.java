package cn.geralt.nov6th;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Random;
import java.util.stream.Stream;

public class MyTest {

    static Stream<Arguments> p0(){
        return Stream.of(Arguments.arguments(

        ));
    }

    @Test
    void test0(){
        int ans = 0;
        do{
            ans = getRand();
        }while(ans>320000-1);

        System.out.println(ans);

    }
    int getRand(){
        final Random rand = new Random(System.currentTimeMillis());
        int i = rand.nextInt(65536); // 16bits
        return (i<<3)|(rand.nextInt(65536)&7); // 19bits
    }
}
