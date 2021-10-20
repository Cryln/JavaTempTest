package cn.geralt.sept26th;

import java.util.*;

public class Main2 {
    private  HashMap<Character,Integer> map = new HashMap<>();
    public static void main(String[] args) {

        Main2 temp = new Main2();
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println(temp.test(line));

    }
    public Main2(){
        map.put('#', 0);
        map.put('+', 1);
        map.put('x', 2);
        map.put('@', 3);
    }

    /*
    10+2@1x2
     */
    public int test(String s){
        Stack<Integer> stk1 = new Stack<>(); //操作数
        Stack<Character> stk2 = new Stack<>(); // 操作符
        StringBuilder num = new StringBuilder();

        stk2.push('#');
        s += '#';
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if(Character.isDigit(a)){
                num.append(a);
            }else{
                int n = Integer.parseInt(num.toString());
                num = new StringBuilder();
                stk1.push(n);
                char top = stk2.peek();


                if(isBigger(a,top)>0){
                    stk2.push(a);
                }else {
                    while(isBigger(a,top)<=0&&(top!='#'||a!='#')){
                        int numA = stk1.pop();
                        int numB = stk1.pop();
                        int ans = cal(stk2.pop(),numA,numB);
                        stk1.push(ans);
                        top = stk2.peek();
                        Arrays.sort(new int[]{});

                    }
                    stk2.push(a);
                }


            }

        }
        return stk1.peek();
    }
    private int isBigger(char a,char b){
        return map.get(a)-map.get(b);
    }
    private int cal(char x,int a,int b){
        switch (x){
            case '+':{
                return a+b;
            }
            case 'x':{
                return a*b;
            }
            case '@':{
                return a|(a+b);
            }
            default: return 0;
        }
    }
}
