package cy.tree.binarySearchTree;

import java.util.Stack;

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
     *  ǰ�����
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
     *  �������
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
     *  �������
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
     *  �ǵݹ�ǰ�����
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
     *  �ǵݹ��������
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
