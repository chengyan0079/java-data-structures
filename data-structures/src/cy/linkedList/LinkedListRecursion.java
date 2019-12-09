package cy.linkedList;

import javafx.util.Pair;

/**
 *  单向链表，用递归Recursion实现
 */
public class LinkedListRecursion<E> {

    /**
     *  封装节点对象
     */
    private class Node{
        E val;
        // 下一个节点
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

    // 在链表的递归实现中，我们不使用虚拟头结点，也能无差异的处理位置0的问题：）
    private Node head;

    public LinkedListRecursion(){
        head = null;
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }

    // 返回链表是否为空
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

    // 递归方法
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

    // 在以node为头结点的链表中，找到第index个元素，递归算法
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

    // 修改以node为头结点的链表中，第index(0-based)个位置的元素为e，递归算法
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

    // 在以node为头结点的链表中，查找是否存在元素e，递归算法
    private boolean contains(Node node, E e){
        if(node == null){
            return false;
        }
        if(node.val.equals(e)){
            return true;
        }
        return contains(node.next, e);
    }

    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    public E remove(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        Pair<Node, E> res = remove(head, index);
        size --;
        head = res.getKey();
        return res.getValue();
    }

    // 从以node为头结点的链表中，删除第index位置的元素，递归算法
    // 返回值包含两个元素，删除后的链表头结点和删除的值：）
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

    // 从以node为头结点的链表中，删除元素e，递归算法
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
