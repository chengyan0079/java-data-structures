package cy.tree.binarySearchTree;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 *  二分搜索树
 *  继承Comparable表示可比较的
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private class Node{

        private E val;

        private Node left, right;

        public Node(E e){
            this.val = e;
            this.left = null;
            this.right = null;
        }

    }

    private int size;

    // 根节点
    private Node root;

    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public Node getRoot(){
        return root;
    }

    public boolean isEmply(){
        return size == 0;
    }

    /**
     *  添加一个节点
     */
    public void add(E e){
        root = add(root, e);
    }

    //　递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e){
        // 如果节点为空，直接返回新增node
        if(node == null){
            size ++;
            return new Node(e);
        }

        // 继续递归
        if(e.compareTo(node.val) < 0){
            // 左孩子继续递归
            node.left = add(node.left, e);
        }else if(e.compareTo(node.val) > 0){
            // 右孩子继续递归
            node.right = add(node.right, e);
        }
        // 相等什么都不做，直接返回

        return node;
    }


    public boolean contains(E e){
        return contains(root, e);
    }

    // 递归算法
    private boolean contains(Node node, E e){
        if(node == null){
            return false;
        }
        if(node.val.compareTo(e) == 0){
            return true;
        }else if(node.val.compareTo(e) < 0){
            return contains(node.left, e);
        }else {
            // node.val.compareTo(e) > 0
            return contains(node.right, e);
        }
    }

    /**
     *  前序遍历（深度优先遍历）
     */
    public void proOrder(){
        proOrder(root);
    }

    // 递归算法 从左到右依次访问
    private void proOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.val);
        proOrder(node.left);
        proOrder(node.right);
    }


    /**
     *  中序遍历（深度优先遍历）
     */
    public void inOrder(){
        inOrder(root);
    }
    // 递归算法 从左到右依次访问
    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);

    }

    /**
     *  后序遍历（深度优先遍历）
     */
    public void postOrder(){
        postOrder(root);
    }
    // 递归算法 从左到右依次访问
    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }


    /**
     *  非递归前序遍历（深度优先遍历）
     *  用栈来实现递归思路，从右孩子到左孩子依次开始入栈操作
     *               15(1)
     *             /      \
     *           6(2)      23(7)
     *         /   \       /   \
     *       4(3)  7(6)  19(8)  71(9)
     *      /   \
     *     2(4)  5(5)
     *  遍历输出顺序如图括号，结果为：15,6,4,2,5,7,23,19,71
     *
     * 执行逻辑为：
     * 入栈15
     * 循环1：出栈15，入栈右23，入栈左6
     * 循环2：出栈6，入栈右7，入栈左4
     * 循环3：出栈4，入栈右5，入栈左2
     * 循环4：出栈2
     * 循环5：出栈5
     * 循环6：出栈7
     * 循环7：出栈23，入栈右71，入栈左19
     * 循环8：出栈19
     * 循环9：出栈71
     */
    public void preOrderNR(){

        if(root == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        // 从根节点开始入栈
        stack.push(root);
        while(!stack.isEmpty()){
            //　出栈表示执行遍历操作
            Node cur = stack.pop();
            System.out.println(cur.val);

            // 右孩子非空 右孩子入栈
            if(cur.right != null){
                stack.push(cur.right);
            }
            // 左孩子非空 左孩子入栈
            if(cur.left != null){
                stack.push(cur.left);
            }
        }

    }

    /**
     *  非递归中序遍历（深度优先遍历）
     *  用栈来实现递归思路
     *               15(6)
     *             /      \
     *           6(4)      23(8)
     *         /   \       /   \
     *       4(2)  7(5)  19(7)  71(9)
     *      /   \
     *     2(1)  5(3)
     *  遍历输出顺序如图括号，结果为：2,4,5,6,7,15,19,23,71
     *
     * 执行逻辑为：
     * 循环1->入栈15
     * 循环2->入栈6
     * 循环3->入栈4
     * 循环4->入栈2
     * 循环5->出栈2
     * 循环6->出栈4
     * 循环7->入栈5
     * 循环8->出栈5
     * 循环9->出栈6
     * 循环10->入栈7
     * 循环11->出栈7
     * 循环12->出栈15
     * 循环13->入栈23
     * 循环14->入栈19
     * 循环15->出栈19
     * 循环16->出栈23
     * 循环17->入栈71
     * 循环18->出栈71
     */
    public void inOrderNR() {
        if (root == null) {
            return;
        }
        Node p = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);
                p = p.left;
            }else if(!stack.isEmpty()) {
                p = stack.pop(); //取出栈顶元素
                System.out.println(p.val);
                p = p.right; //访问右子树
            }
        }

    }

    /**
     *  非递归后序遍历（深度优先遍历）
     *  用栈来实现递归思路
     *               15(9)
     *             /      \
     *           6(5)      23(8)
     *         /   \       /   \
     *       4(3)  7(4)  19(6)  71(7)
     *      /   \
     *    2(1)  5(2)
     *  遍历输出顺序如图括号，结果为：2,5,4,7,6,19,71,23,15
     *
     *  循环1:入栈15，访问次数为1
     *  循环2:入栈6，访问次数为1
     *  循环3:入栈4，访问次数为1
     *  循环4:入栈2，访问次数为1
     *  循环5:取得栈顶2，访问次数为2
     *  循环6:出栈2
     *  循环7:取得栈顶4，访问次数为2
     *  循环8:入栈5，访问次数为1
     *  循环9:取得栈顶5，访问次数为2
     *  循环10:出栈5
     *  循环11:出栈4
     *  循环12:取得栈顶6，访问次数为2
     *  循环13:入栈7，访问次数为1
     *  循环14:取得栈顶7，访问次数为2
     *  循环15:出栈7
     *  循环16:出栈6
     *  循环17:取得栈顶15，访问次数为2
     *  循环18:入栈23，访问次数为1
     *  循环19:入栈19，访问次数为1
     *  循环20:取得栈顶19，访问次数为2
     *  循环21:出栈19
     *  循环22:取得栈顶23，访问次数为2
     *  循环23:入栈71，访问次数为1
     *  循环24:取得栈顶71，访问次数为2
     *  循环25:出栈71
     *  循环26:出栈23
     *  循环27:出栈15
     **/
    public void postOrderNR(){
        if (root == null) {
            return;
        }
        Node node = root;
        Stack<Node> stack = new Stack<>();
        // 用Map来存储访问次数
        Map<E, Integer> map = new HashMap<>();
        while (!stack.isEmpty() || node != null){
            // 当左孩子为空
            if(node != null){
                stack.push(node);// 入栈
                map.put(node.val, 1); // 访问次数为1
                node = node.left; // 继续访问左孩子
            }else{
                node = stack.peek(); //取得栈顶
                // 如果访问次数为2
                if(map.get(node.val) == 2){
                    stack.pop();// 取出栈顶
                    System.out.println(node.val);// 执行数据
                    node = null;// 赋值为空继续循环
                }else{
                    //如果访问次数不是2，将该节点访问次数改为2
                    map.put(node.val, 2);
                    // 继续访问右孩子
                    node = node.right;
                }
            }
        }
    }

    /**
     *  层序遍历（广度优先遍历），非递归
     *  用队列的实现
     *               15(1)
     *             /     \
     *           6(2)    23(3)
     *         /   \     /   \
     *      4(4)  7(5) 19(6)  71(7)
     *      /       \
     *    2(8)      5(9)
     *  遍历输出顺序如图括号，结果为：15,6,23,4,7,19,71,2,5
     *
     *  入队根15
     *  循环1:出队15,入队6,入队23
     *  循环2:出队6,入队4,入队7
     *  循环3:出队23,入队19,入队71
     *  循环4:出队4,入队2,入队5
     *  循环5:出队7
     *  循环6:出队19
     *  循环7:出队71
     *  循环8:出队2
     *  循环9:出队5
     */
    public void levelOrder(){
        // 用链表来实现队列
        Queue<Node> queue = new LinkedList<>();
        // 根节点入队
        queue.add(root);
        while (!queue.isEmpty()){
            // 出队
            Node curr = queue.remove();
            System.out.println(curr.val);

            // 有左孩子了入队
            if (curr.left != null){
                ((LinkedList<Node>) queue).add(curr.left);
            }
            // 有右孩子了入队
            if (curr.right != null){
                ((LinkedList<Node>) queue).add(curr.right);
            }
        }
    }

    /**
     *  查询二叉树的最小元素，递归算法
     */
    public E getMinElement(){
        if(size == 0){
            throw  new IllegalArgumentException("BST is empty!");
        }
        Node getNode = getMinElement(root);
        return getNode.val;
    }

    // 递归算法
    private Node getMinElement(Node node){
        // 因为最小节点一定在左孩子
        if(node.left == null){
            return node;
        }
        return getMinElement(node.left);
    }

    /**
     *  查询二叉树的最大元素
     */
    public E getMaxElement(){
        if(size == 0){
            throw  new IllegalArgumentException("BST is empty!");
        }
        Node getNode = getMaxElement(root);
        return getNode.val;
    }

    // 递归算法
    private Node getMaxElement(Node node){
        // 因为最大节点一定在右孩子
        if(node.right == null){
            return node;
        }
        return getMaxElement(node.right);
    }

    /**
     *  删除最小值所在的节点,递归算法
     */
    public E removeMin(){
        // 找到最小元素，为返回元素
        E ret = getMinElement();
        // 执行删除操作
        root = removeMin(root);
        return ret;
    }

    // 递归算法
    private Node removeMin(Node node){
        // 最小节点一定在左孩子
        if(node.left == null){
            // 取得节点右孩子
            Node rightNode = node.right;
            // 将右孩子为空
            node.right = null;
            size --;
            return rightNode;
        }
        // 将又孩子给上层左孩子
        node.left = removeMin(node.left);
        return node;
    }

    /**
     *  删除最大值所在的节点,递归算法
     */
    public E removeMax(){
        // 找到最大元素，为返回元素
        E ret = getMaxElement();
        // 执行删除操作
        root = removeMax(root);
        return ret;
    }

    // 递归算法
    private Node removeMax(Node node){
        // 最大节点已经在右孩子
        if(node.right == null){
            // 取得左孩子节点
            Node leftNode = node.left;
            // 左孩子为空
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.val + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++){
            res.append("--");
        }
        return res.toString();
    }




}
