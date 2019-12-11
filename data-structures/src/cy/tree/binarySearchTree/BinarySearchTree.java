package cy.tree.binarySearchTree;

import java.util.*;

/**
 *  ����������
 *  �̳�Comparable��ʾ�ɱȽϵ�
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

    // ���ڵ�
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
     *  ���һ���ڵ�
     */
    public void add(E e){
        root = add(root, e);
    }

    //���ݹ��㷨
    // ���ز����½ڵ������������ĸ�
    private Node add(Node node, E e){
        // ����ڵ�Ϊ�գ�ֱ�ӷ�������node
        if(node == null){
            size ++;
            return new Node(e);
        }

        // �����ݹ�
        if(e.compareTo(node.val) < 0){
            // ���Ӽ����ݹ�
            node.left = add(node.left, e);
        }else if(e.compareTo(node.val) > 0){
            // �Һ��Ӽ����ݹ�
            node.right = add(node.right, e);
        }
        // ���ʲô��������ֱ�ӷ���

        return node;
    }


    public boolean contains(E e){
        return contains(root, e);
    }

    // �ݹ��㷨
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
     *  ǰ��������ݹ��㷨��������ȱ�����
     */
    public List<E> proOrder(){
        return proOrder(root);
    }

    // �ݹ��㷨 ���������η���
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
     *  ����������ݹ��㷨��������ȱ�����
     */
    public List<E> inOrder(){
        return inOrder(root);
    }
    // �ݹ��㷨 ���������η���
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
     *  ����������ݹ��㷨��������ȱ�����
     */
    public List<E> postOrder(){
        return postOrder(root);
    }
    // �ݹ��㷨 ���������η���
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
     *  �ǵݹ�ǰ�������������ȱ�����
     *  ��ջ��ʵ�ֵݹ�˼·
     *  ʱ��O(n)���ռ�O(n)
     *
     *               15(1)
     *             /      \
     *           6(2)      23(7)
     *         /   \       /   \
     *       4(3)  7(6)  19(8)  71(9)
     *      /   \
     *     2(4)  5(5)
     *  �������˳����ͼ���ţ����Ϊ��15,6,4,2,5,7,23,19,71
     *
     * ִ���߼�Ϊ��
     * ��ջ15
     * ѭ��1����ջ15����ջ��23����ջ��6
     * ѭ��2����ջ6����ջ��7����ջ��4
     * ѭ��3����ջ4����ջ��5����ջ��2
     * ѭ��4����ջ2
     * ѭ��5����ջ5
     * ѭ��6����ջ7
     * ѭ��7����ջ23����ջ��71����ջ��19
     * ѭ��8����ջ19
     * ѭ��9����ջ71
     */
    public List<E> preOrderNR(){

        ArrayList<E> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Stack<Node> stack = new Stack<>();
        // �Ӹ��ڵ㿪ʼ��ջ
        stack.push(root);
        while(!stack.isEmpty()){
            //����ջ��ʾִ�б�������
            Node curr = stack.pop();
            res.add(curr.val);

            // �Һ��ӷǿ� �Һ�����ջ
            if(curr.right != null){
                stack.push(curr.right);
            }
            // ���ӷǿ� ������ջ
            if(curr.left != null){
                stack.push(curr.left);
            }
        }
        return res;
    }

    /**
     *  �ǵݹ����������������ȱ�����
     *  ��ջ��ʵ�ֵݹ�˼·
     *  ʱ��O(n)���ռ�O(n)
     *
     *               15(6)
     *             /      \
     *           6(4)      23(8)
     *         /   \       /   \
     *       4(2)  7(5)  19(7)  71(9)
     *      /   \
     *     2(1)  5(3)
     *  �������˳����ͼ���ţ����Ϊ��2,4,5,6,7,15,19,23,71
     *
     * ִ���߼�Ϊ��
     * ѭ��1->��ջ15
     * ѭ��2->��ջ6
     * ѭ��3->��ջ4
     * ѭ��4->��ջ2
     * ѭ��5->��ջ2
     * ѭ��6->��ջ4
     * ѭ��7->��ջ5
     * ѭ��8->��ջ5
     * ѭ��9->��ջ6
     * ѭ��10->��ջ7
     * ѭ��11->��ջ7
     * ѭ��12->��ջ15
     * ѭ��13->��ջ23
     * ѭ��14->��ջ19
     * ѭ��15->��ջ19
     * ѭ��16->��ջ23
     * ѭ��17->��ջ71
     * ѭ��18->��ջ71
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
                curr = stack.pop(); //ȡ��ջ��Ԫ��
                res.add(curr.val);
                curr = curr.right; //����������
            }
        }
        return res;
    }

    /**
     *  �ǵݹ���������������ȱ�����
     *  ��ջ��ʵ�ֵݹ�˼·
     *  ʱ��O(n)���ռ�O(n)
     *
     *               15(9)
     *             /      \
     *           6(5)      23(8)
     *         /   \       /   \
     *       4(3)  7(4)  19(6)  71(7)
     *      /   \
     *    2(1)  5(2)
     *  �������˳����ͼ���ţ����Ϊ��2,5,4,7,6,19,71,23,15
     *
     *  ѭ��1:��ջ15�����ʴ���Ϊ1
     *  ѭ��2:��ջ6�����ʴ���Ϊ1
     *  ѭ��3:��ջ4�����ʴ���Ϊ1
     *  ѭ��4:��ջ2�����ʴ���Ϊ1
     *  ѭ��5:ȡ��ջ��2�����ʴ���Ϊ2
     *  ѭ��6:��ջ2
     *  ѭ��7:ȡ��ջ��4�����ʴ���Ϊ2
     *  ѭ��8:��ջ5�����ʴ���Ϊ1
     *  ѭ��9:ȡ��ջ��5�����ʴ���Ϊ2
     *  ѭ��10:��ջ5
     *  ѭ��11:��ջ4
     *  ѭ��12:ȡ��ջ��6�����ʴ���Ϊ2
     *  ѭ��13:��ջ7�����ʴ���Ϊ1
     *  ѭ��14:ȡ��ջ��7�����ʴ���Ϊ2
     *  ѭ��15:��ջ7
     *  ѭ��16:��ջ6
     *  ѭ��17:ȡ��ջ��15�����ʴ���Ϊ2
     *  ѭ��18:��ջ23�����ʴ���Ϊ1
     *  ѭ��19:��ջ19�����ʴ���Ϊ1
     *  ѭ��20:ȡ��ջ��19�����ʴ���Ϊ2
     *  ѭ��21:��ջ19
     *  ѭ��22:ȡ��ջ��23�����ʴ���Ϊ2
     *  ѭ��23:��ջ71�����ʴ���Ϊ1
     *  ѭ��24:ȡ��ջ��71�����ʴ���Ϊ2
     *  ѭ��25:��ջ71
     *  ѭ��26:��ջ23
     *  ѭ��27:��ջ15
     **/
    public List<E> postOrderNR(){
        ArrayList<E> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        // ��Map���洢���ʴ���
        Map<E, Integer> map = new HashMap<>();
        while (!stack.isEmpty() || curr != null){
            // ������Ϊ��
            if(curr != null){
                stack.push(curr);// ��ջ
                map.put(curr.val, 1); // ���ʴ���Ϊ1
                curr = curr.left; // ������������
            }else{
                curr = stack.peek(); //ȡ��ջ��
                // ������ʴ���Ϊ2
                if(map.get(curr.val) == 2){
                    stack.pop();// ȡ��ջ��
                    res.add(curr.val);// ִ������
                    curr = null;// ��ֵΪ�ռ���ѭ��
                }else{
                    //������ʴ�������2�����ýڵ���ʴ�����Ϊ2
                    map.put(curr.val, 2);
                    // ���������Һ���
                    curr = curr.right;
                }
            }
        }
        return res;
    }

    /**
     *  ���������������ȱ��������ǵݹ�
     *  �ö��е�ʵ��
     *  ʱ��O(n)���ռ�O(n)
     *
     *               15(1)
     *             /     \
     *           6(2)    23(3)
     *         /   \     /   \
     *      4(4)  7(5) 19(6)  71(7)
     *      /       \
     *    2(8)      5(9)
     *  �������˳����ͼ���ţ����Ϊ��15,6,23,4,7,19,71,2,5
     *
     *  ��Ӹ�15
     *  ѭ��1:����15,���6,���23
     *  ѭ��2:����6,���4,���7
     *  ѭ��3:����23,���19,���71
     *  ѭ��4:����4,���2,���5
     *  ѭ��5:����7
     *  ѭ��6:����19
     *  ѭ��7:����71
     *  ѭ��8:����2
     *  ѭ��9:����5
     */
    public List<E> levelOrder(){
        ArrayList<E> res = new ArrayList<>();
        // ��������ʵ�ֶ���
        Queue<Node> queue = new LinkedList<>();
        // ���ڵ����
        queue.add(root);
        while (!queue.isEmpty()){
            // ����
            Node curr = queue.remove();
            res.add(curr.val);

            // �����������
            if (curr.left != null){
                ((LinkedList<Node>) queue).add(curr.left);
            }
            // ���Һ��������
            if (curr.right != null){
                ((LinkedList<Node>) queue).add(curr.right);
            }
        }
        return res;
    }

    /**
     *  �ǵݹ��������
     *  ʱ��O(n)���ռ�O(1)
     *
     *  ����ǵݹ�ǰ��������λ�ò�ͬ����������ͬ
     */
    public List<E> inOrderTraveral() {

        ArrayList<E> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Node cur = root;
        while(cur != null){
            // �����ǰ�ڵ������Ϊ�գ��������ǰ�ڵ㣬�������Һ�����Ϊ��ǰ�ڵ㡣
            if(cur.left == null){
                res.add(cur.val);
                cur = cur.right;
            }
            // �����ǰ�ڵ�����Ӳ�Ϊ�գ��ڵ�ǰ�ڵ���������ҵ���ǰ�ڵ��ǰ���ڵ㡣
            else{
                // ȡ�õ�ǰ�ڵ��ǰ���ڵ�
                Node prev = cur.left;
                while(prev.right != null && prev.right != cur){
                    prev = prev.right;
                }

                //���ǰ���ڵ���Һ���Ϊ�գ��������Һ�������Ϊ��ǰ�ڵ㡣��ǰ�ڵ����Ϊ��ǰ�ڵ�����ӡ�
                if(prev.right == null){
                    prev.right = cur;
                    cur = cur.left;
                }
                //���ǰ���ڵ���Һ���Ϊ��ǰ�ڵ㣬�������Һ���������Ϊ�գ��ָ�������״���������ǰ�ڵ㡣��ǰ�ڵ����Ϊ��ǰ�ڵ���Һ��ӡ�
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
     *  �ǵݹ�ǰ�����
     *  ʱ��O(n)���ռ�O(1)
     *
     *  ѭ��1����ǰcur=15��ǰ��prev=7������list=15������prev.right=15������cur=6
     * ѭ��2����ǰcur=6��ǰ��prev=5������list=6������prev.right=6������cur=4
     * ѭ��3����ǰcur=4��ǰ��prev=2������list=4������prev.right=4������cur=2
     * ѭ��4����ǰcur=2������list=2����Ϊ��ǰcur.right=4�����ĵ�ǰcur=4
     * ѭ��5����ǰcur=4����Ϊ��ǰ��cur.right=5�����ĵ�ǰcur=5
     * ѭ��6����ǰcur=5������list=5����Ϊ��ǰ��cur.right=6�����ĵ�ǰcur=6
     * ѭ��7����ǰcur=6����Ϊ��ǰ��cur.right=7�����ĵ�ǰcur=7
     * ѭ��8����ǰcur=7������list=7����Ϊ��ǰ��cur.right=15�����ĵ�ǰcur=15
     * ѭ��9����ǰcur15��ǰ��prev=7����ǰ��cur.right=23�����ĵ�ǰcur=23
     * ѭ��10����ǰcur23��ǰ��prev=19������list=23������prev.right=23������cur=19
     * ѭ��11����ǰcur19������list=19����Ϊ��ǰ��cur.right=23�����ĵ�ǰcur=23
     * ѭ��12����ǰcur23��ǰ��prev=19����ǰ��cur.right=71�����ĵ�ǰcur=71
     * ѭ��13����ǰcur71������list=71
     */
    public List<E> preOrderTraveral() {

        ArrayList<E> res = new ArrayList<E>();
        if(root == null) {
            return res;
        }
        Node cur = root;
        while(cur != null){
            // �����ǰ�ڵ������Ϊ�գ��������ǰ�ڵ㣬�������Һ�����Ϊ��ǰ�ڵ㡣
            if(cur.left == null){
                res.add(cur.val);
                cur = cur.right;
            }
            // �����ǰ�ڵ�����Ӳ�Ϊ�գ��ڵ�ǰ�ڵ���������ҵ���ǰ�ڵ��ǰ���ڵ㡣
            else{
                // ȡ�õ�ǰ�ڵ��ǰ���ڵ�
                Node prev = cur.left;
                while(prev.right != null && prev.right != cur){
                    prev = prev.right;
                }

                //���ǰ���ڵ���Һ���Ϊ�գ������ǰ�ڵ㡣�������Һ�������Ϊ��ǰ�ڵ㡣��ǰ�ڵ����Ϊ��ǰ�ڵ�����ӡ�
                if(prev.right == null){
                    res.add(cur.val);
                    prev.right = cur;
                    cur = cur.left;
                }
                //���ǰ���ڵ���Һ���Ϊ��ǰ�ڵ㣬�������Һ���������Ϊ�գ��ָ�������״������ǰ�ڵ����Ϊ��ǰ�ڵ���Һ��ӡ�
                else{
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    /**
     *  �ǵݹ�������
     *  ʱ��O(n)���ռ�O(1)
     *
     *  ����ǵݹ�ǰ��������λ�ò�ͬ���������鵹������
     */
    public List<E> postorderTraversal() {

        ArrayList<E> res = new ArrayList<E>();
        if (root == null) {
            return res;
        }
        // ����ڵ㣬rootΪ����ڵ������
        Integer i = -1;
        Node dummyRoot = new Node((E) i);
        dummyRoot.left = root;

        Node cur = dummyRoot;// ��ǰ�ڵ�Ϊ����ڵ������������
        while (cur != null) {
            // �����ǰ�ڵ������Ϊ�գ������Һ�����Ϊ��ǰ�ڵ㡣
            if (cur.left == null) {
                cur = cur.right;
            }
            // �����ǰ�ڵ�����Ӳ�Ϊ�գ��ڵ�ǰ�ڵ�����������ҵ���ǰ�ڵ��ǰ���ڵ㡣
            else {
                // ȡ�õ�ǰ�ڵ��ǰ���ڵ�
                Node pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // ���ǰ���ڵ���Һ���Ϊ�գ��������Һ�������Ϊ��ǰ�ڵ㡣��ǰ�ڵ����Ϊ��ǰ�ڵ�����ӡ�
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                //���ǰ���ڵ���Һ���Ϊ��ǰ�ڵ㣬�������Һ���������Ϊ�ա�
                // ��������ӵ�ǰ�ڵ�����ӵ���ǰ���ڵ�����·���ϵ����нڵ㡣��ǰ�ڵ����Ϊ��ǰ�ڵ���Һ��ӡ�
                else {
                    pre.right = null;
                    // ���뷵�ص����������
                    reverseTraversal(cur.left, res);
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    /**
     *  �������鵹������
      */
    private void reverseTraversal(Node node, ArrayList<E> res){
        int start = res.size();
        while(node != null){
            res.add(node.val);
            node = node.right;
        }

        int i = start;
        int j = res.size() - 1;
        // ����������˳����е���
        while(i < j){
            res.set(i, res.get(j));
            res.set(j, res.get(i));
            i ++;
            j --;
        }
    }

    /**
     *  ��ѯ����������СԪ�أ��ݹ��㷨
     */
    public E getMinElement(){
        if(size == 0){
            throw  new IllegalArgumentException("BST is empty!");
        }
        Node getNode = getMinElement(root);
        return getNode.val;
    }

    // �ݹ��㷨
    private Node getMinElement(Node node){
        // ��Ϊ��С�ڵ�һ��������
        if(node.left == null){
            return node;
        }
        return getMinElement(node.left);
    }

    /**
     *  ��ѯ�����������Ԫ��
     */
    public E getMaxElement(){
        if(size == 0){
            throw  new IllegalArgumentException("BST is empty!");
        }
        Node getNode = getMaxElement(root);
        return getNode.val;
    }

    // �ݹ��㷨
    private Node getMaxElement(Node node){
        // ��Ϊ���ڵ�һ�����Һ���
        if(node.right == null){
            return node;
        }
        return getMaxElement(node.right);
    }

    /**
     *  ɾ����Сֵ���ڵĽڵ�,�ݹ��㷨
     */
    public E removeMin(){
        // �ҵ���СԪ�أ�Ϊ����Ԫ��
        E ret = getMinElement();
        // ִ��ɾ������
        root = removeMin(root);
        return ret;
    }

    // �ݹ��㷨
    private Node removeMin(Node node){
        // ��С�ڵ�һ��������
        if(node.left == null){
            // ȡ�ýڵ��Һ���
            Node rightNode = node.right;
            // ���Һ���Ϊ��
            node.right = null;
            size --;
            return rightNode;
        }
        // ���ֺ��Ӹ��ϲ�����
        node.left = removeMin(node.left);
        return node;
    }

    /**
     *  ɾ�����ֵ���ڵĽڵ�,�ݹ��㷨
     */
    public E removeMax(){
        // �ҵ����Ԫ�أ�Ϊ����Ԫ��
        E ret = getMaxElement();
        // ִ��ɾ������
        root = removeMax(root);
        return ret;
    }

    // �ݹ��㷨
    private Node removeMax(Node node){
        // ���ڵ��Ѿ����Һ���
        if(node.right == null){
            // ȡ�����ӽڵ�
            Node leftNode = node.left;
            // ����Ϊ��
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     *  ɾ������Ԫ��
     */
    public void remove(E e){
        root = remove(root, e);
    }

    // �ݹ��㷨
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
                // ȡ�ýڵ��Һ���
                Node rightNode = node.right;
                // ���Һ���Ϊ��
                node.right = null;
                size --;
                return rightNode;
            }
            if(node.right == null){
                // ȡ�����ӽڵ�
                Node leftNode = node.left;
                // ����Ϊ��
                node.left = null;
                size --;
                return leftNode;
            }

            // ������Һ��Ӷ���Ϊ�յ����������˼·��ѡһ
            // ��1���ҵ��ȴ�ɾ���ڵ�����С�ڵ㣬Ҳ�����Һ��ӵ���С�ڵ㣬�ô˽ڵ��������ɾ���ڵ�λ��
            // ��2���ҵ��ȴ�ɾ���ڵ�С�����ڵ㣬Ҳ�������ӵ����ڵ㣬�ô˽ڵ��������ɾ���ڵ�λ��

            // �ڣ�1����˼·���ҵ��ȴ�ɾ���ڵ���Һ��ӵ���С�ڵ�
            Node successor = getMinElement(node.right);// ��̽ڵ�
            //ɾ���Һ�����С�ڵ㣬�ô˽ڵ��������ɾ���ڵ�λ��
            successor.right = removeMin(node.right);// �����Ѿ�����ɾ��������size--��������ά��size
            successor.left = node.left;
            node.left = null;
            node.right = null;
            return successor;

//            // �ڣ�2����˼·���ҵ��ȴ�ɾ���ڵ�����ӵ����ڵ�
//            Node predecessor = getMaxElement(node.left);// ǰ���ڵ�
//            //ɾ�����ӵ����ڵ㣬�ô˽ڵ��������ɾ���ڵ�λ��
//            predecessor.left = removeMax(node.left);// �����Ѿ�����ɾ��������size--��������ά��size
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

    // ������nodeΪ���ڵ㣬���Ϊdepth���������������ַ���
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
