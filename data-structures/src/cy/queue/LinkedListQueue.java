package cy.queue;

import cy.linkedList.LinkedList;

/**
 *  ��������Ķ���
 */
public class LinkedListQueue<E> implements Queue<E> {

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

    private Node head, tail;

    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  ��tail�˸������
     */
    @Override
    public void enqueue(E e) {
        // tailΪ�ձ�ʾ����Ϊ��
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }
        else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    /**
     *  ��head�˸������
     */
    @Override
    public E dequeue() {
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if(head == null){
            tail = null;
        }
        size --;
        return retNode.val;
    }

    @Override
    public E getFront() {
        if(isEmpty()) {
            throw new IllegalArgumentException("empty queue.");
        }
        return head.val;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail\n");
        res.append("-------------------------");
        return res.toString();
    }
}
