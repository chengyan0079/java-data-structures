package cy.tree.binarySearchTree;

import java.util.Stack;

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
     *  前序遍历
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
     *  中序遍历
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
     *  后序遍历
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
     *  非递归前序遍历
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
     *  非递归中序遍历
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
