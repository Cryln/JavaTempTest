package cn.geralt.oct24th;
import java.util.*;
import java.util.stream.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[] data = new int[n];
        for(int i=0;i<n;i++){
            data[i] = sc.nextInt();
        }

        long sum = 0;

        for(int i=0;i<n-m+1;i++){
            int[] temp  = new int[m];
            System.arraycopy(data,i,temp,0,m);
            List<Integer> collect = Arrays.stream(temp)
                    .mapToObj(x -> Integer.valueOf(x))
                    .sorted((a,b)->b-a)
                    .collect(Collectors.toList());
            for(int j=0;j<k;j++){
                sum += collect.get(j);
            }
        }

        System.out.println(sum);

    }
}
