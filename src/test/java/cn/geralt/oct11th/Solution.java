package cn.geralt.oct11th;
import java.util.*;




public class Solution {
    /**
     * Note: 类名、方法名、参数名已经指定，请勿修改
     *
     *
     * 给予二叉树的头结点，请返回小偷依据上述原则，能偷到的最多的钱为何？
     * @param node TreeNode类 头结点
     * @return int整型
     */



    public Map<TreeNode,DataNode> map = new HashMap<>();

    public int getMaxMoney(TreeNode node) {
        // write code here
        DataNode leftData = new DataNode(0,0);
        DataNode rightData = new DataNode(0,0);

        if(node==null){
            return 0;
        }
        if(node.left!=null){
            getMaxMoney(node.left);
            leftData = map.get(node.left);
        }
        if(node.right!=null){
            getMaxMoney(node.right);
            rightData = map.get(node.right);
        }
        int _yes = node.val+leftData.no + rightData.no;
        int _no = leftData.yes + rightData.yes;

        map.put(node,new DataNode(_yes,_no));
        return Math.max(_yes,_no);
    }
}
class DataNode{
    public int yes;
    public int no;
    public DataNode(int _y,int _n){
        yes = _y;
        no = _n;
    }
}

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;
  public TreeNode(int val) {
    this.val = val;
  }
}
