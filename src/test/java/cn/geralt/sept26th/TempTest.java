package cn.geralt.sept26th;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TempTest {
    static Stream<Arguments> customParamProvider() {
        int[] data = new int[]{5, 4, 4, 2, 2, 5, 9};
        return Stream.of(
//                arguments("apple"," is tasty, "),
//                arguments("lemon", " is not Okay."),
                arguments(data)
        );
    }

    /*
    1
7
5 4 4 2 2 5 9
     */
    @ParameterizedTest
    @MethodSource("customParamProvider")
    void test(int[] input){
        int[] pd = new int[input.length];
        int max = 0;
        for (int i = pd.length - 1; i >= 0; i--) {
            pd[i] = input[i];
            if(i+input[i]<pd.length) {
                pd[i] += pd[i + input[i]];
            }
            max = Math.max(max,pd[i]);

        }
        System.out.println(max);
    }

}
