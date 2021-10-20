package cn.geralt.oct8th;

import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

public class MyTest {
    static Stream<Arguments> getParams(){
        return Stream.of(Arguments.arguments(
                (Object) new int[]{}
        ));
    }


    @DisplayName("Least Frequently Used Cache")
    @ParameterizedTest
    @MethodSource("getParams")
    void test0(int[][] operators){
        /**
         * @params operators[i][0] : 0 -> get, 1 -> put
         */
        final long count = Arrays.stream(operators).filter(x -> x[0] == 0).count();
        int[] ans = new int[(int) count];
        for (int i = 0; i < operators.length; i++) {

        }

    }

    static class LFUCache{
        private int capacity;
        private int size;
        private TreeMap<Long,Node> timeToNodeMap;
        private Map<Node,Long> nodeToTimeMap;
        public LFUCache(int _capacity){
            size = 0;
            capacity = _capacity;
            timeToNodeMap = new TreeMap<Long,Node>();
            nodeToTimeMap = new HashMap<Node,Long>();
        }

        public void put(Node data){
            Long current = System.currentTimeMillis();
            if (nodeToTimeMap.containsKey(data)) {
                Long last = nodeToTimeMap.get(data);
                timeToNodeMap.remove(last);
            }
            nodeToTimeMap.put(data,current);
            timeToNodeMap.put(current,data);
            size++;
        }

        public void use(Long data){

        }

    }

    @Data
    static class Node{
        private int key;
        private int val;
    }

}
