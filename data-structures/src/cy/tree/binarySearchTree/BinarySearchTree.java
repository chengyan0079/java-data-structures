package cy.tree.binarySearchTree;

import javax.sound.midi.Soundbank;
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
     *  ǰ�������������ȱ�����
     */
    public void proOrder(){
        proOrder(root);
    }

    // �ݹ��㷨 ���������η���
    private void proOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.val);
        proOrder(node.left);
        proOrder(node.right);
    }


    /**
     *  ���������������ȱ�����
     */
    public void inOrder(){
        inOrder(root);
    }
    // �ݹ��㷨 ���������η���
    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);

    }

    /**
     *  ���������������ȱ�����
     */
    public void postOrder(){
        postOrder(root);
    }
    // �ݹ��㷨 ���������η���
    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }


    /**
     *  �ǵݹ�ǰ�������������ȱ�����
     *  ��ջ��ʵ�ֵݹ�˼·�����Һ��ӵ��������ο�ʼ��ջ����
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
    public void preOrderNR(){

        if(root == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        // �Ӹ��ڵ㿪ʼ��ջ
        stack.push(root);
        while(!stack.isEmpty()){
            //����ջ��ʾִ�б�������
            Node cur = stack.pop();
            System.out.println(cur.val);

            // �Һ��ӷǿ� �Һ�����ջ
            if(cur.right != null){
                stack.push(cur.right);
            }
            // ���ӷǿ� ������ջ
            if(cur.left != null){
                stack.push(cur.left);
            }
        }

    }

    /**
     *  �ǵݹ����������������ȱ�����
     *  ��ջ��ʵ�ֵݹ�˼·
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
                p = stack.pop(); //ȡ��ջ��Ԫ��
                System.out.println(p.val);
                p = p.right; //����������
            }
        }

    }

    /**
     *  �ǵݹ���������������ȱ�����
     *  ��ջ��ʵ�ֵݹ�˼·
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
    public void postOrderNR(){
        if (root == null) {
            return;
        }
        Node node = root;
        Stack<Node> stack = new Stack<>();
        // ��Map���洢���ʴ���
        Map<E, Integer> map = new HashMap<>();
        while (!stack.isEmpty() || node != null){
            // ������Ϊ��
            if(node != null){
                stack.push(node);// ��ջ
                map.put(node.val, 1); // ���ʴ���Ϊ1
                node = node.left; // ������������
            }else{
                node = stack.peek(); //ȡ��ջ��
                // ������ʴ���Ϊ2
                if(map.get(node.val) == 2){
                    stack.pop();// ȡ��ջ��
                    System.out.println(node.val);// ִ������
                    node = null;// ��ֵΪ�ռ���ѭ��
                }else{
                    //������ʴ�������2�����ýڵ���ʴ�����Ϊ2
                    map.put(node.val, 2);
                    // ���������Һ���
                    node = node.right;
                }
            }
        }
    }

    /**
     *  ���������������ȱ��������ǵݹ�
     *  �ö��е�ʵ��
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
    public void levelOrder(){
        // ��������ʵ�ֶ���
        Queue<Node> queue = new LinkedList<>();
        // ���ڵ����
        queue.add(root);
        while (!queue.isEmpty()){
            // ����
            Node curr = queue.remove();
            System.out.println(curr.val);

            // �����������
            if (curr.left != null){
                ((LinkedList<Node>) queue).add(curr.left);
            }
            // ���Һ��������
            if (curr.right != null){
                ((LinkedList<Node>) queue).add(curr.right);
            }
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
