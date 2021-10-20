package cn.geralt.oct8th;

public class Outer {
    private int outerDada = 0;
    static int staticData = 0;
    static class StaticInner{
        private int innerData = 0;
        static int staticData = 0;
        void test(){
            System.out.println(this.innerData);
            System.out.println(staticData);
        }
    }
    class CommonInner{
        private int innerData = 0;

        void test(){
            System.out.println(this.innerData);
            System.out.println(outerDada);
            System.out.println(staticData);
        }

    }

    public static void main(String[] args) {
        Outer o  = new Outer();
        CommonInner c = o.new CommonInner();
    }
}
