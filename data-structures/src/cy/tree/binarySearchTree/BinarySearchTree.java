package cy.tree.binarySearchTree;

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
     *  前序遍历，递归算法（深度优先遍历）
     */
    public List<E> proOrder(){
        return proOrder(root);
    }

    // 递归算法 从左到右依次访问
    private List<E> proOrder(Node node){
        ArrayList<E> res = new ArrayList<>();
        if(node == null){
            return res;
        }
        System.out.println(node.val);
        proOrder(node.left);
        proOrder(node.right);
        return res;
    }


    /**
     *  中序遍历，递归算法（深度优先遍历）
     */
    public List<E> inOrder(){
        return inOrder(root);
    }
    // 递归算法 从左到右依次访问
    private List<E> inOrder(Node node){
        ArrayList<E> res = new ArrayList<>();
        if(node == null){
            return res;
        }
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
        return res;
    }

    /**
     *  后序遍历，递归算法（深度优先遍历）
     */
    public List<E> postOrder(){
        return postOrder(root);
    }
    // 递归算法 从左到右依次访问
    private List<E> postOrder(Node node){
        ArrayList<E> res = new ArrayList<>();
        if(node == null){
            return res;
        }
        postOrder(node.left);
        postOrder(node.right);
        res.add(node.val);
        return res;
    }


    /**
     *  非递归前序遍历（深度优先遍历）
     *  用栈来实现递归思路
     *  时间O(n)，空间O(n)
     *
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
    public List<E> preOrderNR(){

        ArrayList<E> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Stack<Node> stack = new Stack<>();
        // 从根节点开始入栈
        stack.push(root);
        while(!stack.isEmpty()){
            //　出栈表示执行遍历操作
            Node curr = stack.pop();
            res.add(curr.val);

            // 右孩子非空 右孩子入栈
            if(curr.right != null){
                stack.push(curr.right);
            }
            // 左孩子非空 左孩子入栈
            if(curr.left != null){
                stack.push(curr.left);
            }
        }
        return res;
    }

    /**
     *  非递归中序遍历（深度优先遍历）
     *  用栈来实现递归思路
     *  时间O(n)，空间O(n)
     *
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
    public List<E> inOrderNR() {
        ArrayList<E> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop(); //取出栈顶元素
                res.add(curr.val);
                curr = curr.right; //访问右子树
            }
        }
        return res;
    }

    /**
     *  非递归后序遍历（深度优先遍历）
     *  用栈来实现递归思路
     *  时间O(n)，空间O(n)
     *
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
    public List<E> postOrderNR(){
        ArrayList<E> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        // 用Map来存储访问次数
        Map<E, Integer> map = new HashMap<>();
        while (!stack.isEmpty() || curr != null){
            // 当左孩子为空
            if(curr != null){
                stack.push(curr);// 入栈
                map.put(curr.val, 1); // 访问次数为1
                curr = curr.left; // 继续访问左孩子
            }else{
                curr = stack.peek(); //取得栈顶
                // 如果访问次数为2
                if(map.get(curr.val) == 2){
                    stack.pop();// 取出栈顶
                    res.add(curr.val);// 执行数据
                    curr = null;// 赋值为空继续循环
                }else{
                    //如果访问次数不是2，将该节点访问次数改为2
                    map.put(curr.val, 2);
                    // 继续访问右孩子
                    curr = curr.right;
                }
            }
        }
        return res;
    }

    /**
     *  层序遍历（广度优先遍历），非递归
     *  用队列的实现
     *  时间O(n)，空间O(n)
     *
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
    public List<E> levelOrder(){
        ArrayList<E> res = new ArrayList<>();
        // 用链表来实现队列
        Queue<Node> queue = new LinkedList<>();
        // 根节点入队
        queue.add(root);
        while (!queue.isEmpty()){
            // 出队
            Node curr = queue.remove();
            res.add(curr.val);

            // 有左孩子了入队
            if (curr.left != null){
                ((LinkedList<Node>) queue).add(curr.left);
            }
            // 有右孩子了入队
            if (curr.right != null){
                ((LinkedList<Node>) queue).add(curr.right);
            }
        }
        return res;
    }

    /**
     *  非递归中序遍历
     *  时间O(n)，空间O(1)
     *
     *  仅与非递归前序遍历输出位置不同，其他均相同
     */
    public List<E> inOrderTraveral() {

        ArrayList<E> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Node cur = root;
        while(cur != null){
            // 如果当前节点的左孩子为空，则输出当前节点，并将其右孩子作为当前节点。
            if(cur.left == null){
                res.add(cur.val);
                cur = cur.right;
            }
            // 如果当前节点的左孩子不为空，在当前节点的左孩子中找到当前节点的前驱节点。
            else{
                // 取得当前节点的前驱节点
                Node prev = cur.left;
                while(prev.right != null && prev.right != cur){
                    prev = prev.right;
                }

                //如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
                if(prev.right == null){
                    prev.right = cur;
                    cur = cur.left;
                }
                //如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
                else{
                    prev.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    /**
     *  非递归前序遍历
     *  时间O(n)，空间O(1)
     *
     *  循环1，当前cur=15，前驱prev=7，放入list=15，更改prev.right=15，更改cur=6
     * 循环2，当前cur=6，前驱prev=5，放入list=6，更改prev.right=6，更改cur=4
     * 循环3，当前cur=4，前驱prev=2，放入list=4，更改prev.right=4，更改cur=2
     * 循环4，当前cur=2，放入list=2，因为当前cur.right=4，更改当前cur=4
     * 循环5，当前cur=4，因为当前右cur.right=5，更改当前cur=5
     * 循环6，当前cur=5，放入list=5，因为当前右cur.right=6，更改当前cur=6
     * 循环7，当前cur=6，因为当前右cur.right=7，更改当前cur=7
     * 循环8，当前cur=7，放入list=7，因为当前右cur.right=15，更改当前cur=15
     * 循环9，当前cur15，前驱prev=7，当前右cur.right=23，更改当前cur=23
     * 循环10，当前cur23，前驱prev=19，放入list=23，更改prev.right=23，更改cur=19
     * 循环11，当前cur19，放入list=19，因为当前右cur.right=23，更改当前cur=23
     * 循环12，当前cur23，前驱prev=19，当前右cur.right=71，更改当前cur=71
     * 循环13，当前cur71，放入list=71
     */
    public List<E> preOrderTraveral() {

        ArrayList<E> res = new ArrayList<E>();
        if(root == null) {
            return res;
        }
        Node cur = root;
        while(cur != null){
            // 如果当前节点的左孩子为空，则输出当前节点，并将其右孩子作为当前节点。
            if(cur.left == null){
                res.add(cur.val);
                cur = cur.right;
            }
            // 如果当前节点的左孩子不为空，在当前节点的左孩子中找到当前节点的前驱节点。
            else{
                // 取得当前节点的前驱节点
                Node prev = cur.left;
                while(prev.right != null && prev.right != cur){
                    prev = prev.right;
                }

                //如果前驱节点的右孩子为空，输出当前节点。将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
                if(prev.right == null){
                    res.add(cur.val);
                    prev.right = cur;
                    cur = cur.left;
                }
                //如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。当前节点更新为当前节点的右孩子。
                else{
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    /**
     *  非递归后序遍历
     *  时间O(n)，空间O(1)
     *
     *  仅与非递归前序遍历输出位置不同，插入数组倒序排列
     */
    public List<E> postorderTraversal() {

        ArrayList<E> res = new ArrayList<E>();
        if (root == null) {
            return res;
        }
        // 虚拟节点，root为虚拟节点的左孩子
        Integer i = -1;
        Node dummyRoot = new Node((E) i);
        dummyRoot.left = root;

        Node cur = dummyRoot;// 当前节点为虚拟节点的整个二叉树
        while (cur != null) {
            // 如果当前节点的左孩子为空，则将其右孩子作为当前节点。
            if (cur.left == null) {
                cur = cur.right;
            }
            // 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点的前驱节点。
            else {
                // 取得当前节点的前驱节点
                Node pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                //如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。
                // 倒序输出从当前节点的左孩子到该前驱节点这条路径上的所有节点。当前节点更新为当前节点的右孩子。
                else {
                    pre.right = null;
                    // 插入返回的数组里（倒序）
                    reverseTraversal(cur.left, res);
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    /**
     *  加入数组倒序排列
      */
    private void reverseTraversal(Node node, ArrayList<E> res){
        int start = res.size();
        while(node != null){
            res.add(node.val);
            node = node.right;
        }

        int i = start;
        int j = res.size() - 1;
        // 将上述插入顺序进行倒序
        while(i < j){
            res.set(i, res.get(j));
            res.set(j, res.get(i));
            i ++;
            j --;
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

    /**
     *  删除任意元素
     */
    public void remove(E e){
        root = remove(root, e);
    }

    // 递归算法
    private Node remove(Node node, E e){
        if(node == null){
            return null;
        }
        if(e.compareTo(node.val) > 0){
            node.right = remove(node.right, e);
            return node;
        }else if(e.compareTo(node.val) < 0){
            node.left = remove(node.left, e);
            return node;
        }else{
            // e.compareTo(node.val) == 0
            if(node.left == null){
                // 取得节点右孩子
                Node rightNode = node.right;
                // 将右孩子为空
                node.right = null;
                size --;
                return rightNode;
            }
            if(node.right == null){
                // 取得左孩子节点
                Node leftNode = node.left;
                // 左孩子为空
                node.left = null;
                size --;
                return leftNode;
            }

            // 如果左右孩子都不为空的情况，下列思路二选一
            // （1）找到比待删除节点大的最小节点，也就是右孩子的最小节点，用此节点来顶替待删除节点位置
            // （2）找到比待删除节点小的最大节点，也就是左孩子的最大节点，用此节点来顶替待删除节点位置

            // 第（1）种思路，找到比待删除节点的右孩子的最小节点
            Node successor = getMinElement(node.right);// 后继节点
            //删除右孩子最小节点，用此节点来顶替待删除节点位置
            successor.right = removeMin(node.right);// 这里已经进行删除操作，size--，不用再维护size
            successor.left = node.left;
            node.left = null;
            node.right = null;
            return successor;

//            // 第（2）种思路，找到比待删除节点的左孩子的最大节点
//            Node predecessor = getMaxElement(node.left);// 前驱节点
//            //删除左孩子的最大节点，用此节点来顶替待删除节点位置
//            predecessor.left = removeMax(node.left);// 这里已经进行删除操作，size--，不用再维护size
//            predecessor.right = node.right;
//            node.left = null;
//            node.right = null;
//            return predecessor;
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
