package cy.linkedList;

import javafx.util.Pair;

/**
 *  ���������õݹ�Recursionʵ��
 */
public class LinkedListRecursion<E> {

    /**
     *  ��װ�ڵ����
     */
    private class Node{
        E val;
        // ��һ���ڵ�
        Node next;

        public Node(E e, Node next){
            this.val = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return val.toString();
        }
    }

    private int size;

    // ������ĵݹ�ʵ���У����ǲ�ʹ������ͷ��㣬Ҳ���޲���Ĵ���λ��0�����⣺��
    private Node head;

    public LinkedListRecursion(){
        head = null;
        size = 0;
    }

    // ��ȡ�����е�Ԫ�ظ���
    public int getSize(){
        return size;
    }

    // ���������Ƿ�Ϊ��
    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E e){
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        head = add(head, index, e);
        size ++;
    }

    // �ݹ鷽��
    private Node add(Node node, int index, E e){

        if(index == 0) {
            return new Node(e, node);
        }
        node.next = add(node.next, index - 1, e);
        return node;
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void addLast(E e){
        add(size, e);
    }

    public E get(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        return get(head, index);
    }

    // ����nodeΪͷ���������У��ҵ���index��Ԫ�أ��ݹ��㷨
    private E get(Node node, int index){
        if(index == 0) {
            return node.val;
        }
        return get(node.next, index - 1);
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    public void set(int index, E e){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Update failed. Illegal index.");
        }
        set(head, index, e);
    }

    // �޸���nodeΪͷ���������У���index(0-based)��λ�õ�Ԫ��Ϊe���ݹ��㷨
    private void set(Node node, int index, E e){
        if(index == 0){
            node.val = e;
            return;
        }
        set(node.next, index - 1, e);
    }

    public boolean contains(E e){
        return contains(head, e);
    }

    // ����nodeΪͷ���������У������Ƿ����Ԫ��e���ݹ��㷨
    private boolean contains(Node node, E e){
        if(node == null){
            return false;
        }
        if(node.val.equals(e)){
            return true;
        }
        return contains(node.next, e);
    }

    // ��������ɾ��index(0-based)λ�õ�Ԫ��, ����ɾ����Ԫ��
    public E remove(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        Pair<Node, E> res = remove(head, index);
        size --;
        head = res.getKey();
        return res.getValue();
    }

    // ����nodeΪͷ���������У�ɾ����indexλ�õ�Ԫ�أ��ݹ��㷨
    // ����ֵ��������Ԫ�أ�ɾ���������ͷ����ɾ����ֵ����
    private Pair<Node, E> remove(Node node, int index){
        if(index == 0){
            return new Pair<>(node.next, node.val);
        }
        Pair<Node, E> res = remove(node.next, index - 1);
        node.next = res.getKey();
        return new Pair<>(node, res.getValue());
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public void removeElement(E e){
        head = removeElement(head, e);
    }

    // ����nodeΪͷ���������У�ɾ��Ԫ��e���ݹ��㷨
    private Node removeElement(Node node, E e){
        if(node == null){
            return null;
        }
        if(node.val.equals(e)){
            size --;
            return node.next;
        }
        node.next = removeElement(node.next, e);
        return node;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}
