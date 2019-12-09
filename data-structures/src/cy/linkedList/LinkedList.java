package cy.linkedList;

/**
 *  单向链表
 *  增删改查 都是O(n)
 */
public class LinkedList<E> {

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

    // 虚拟头节点，表示上一个节点
    private Node dummyHead;

    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     *  任意位置添加（重点在于要找到上一个节点）
     */
    public void add(int index, E e){

        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i ++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size ++;
    }

    public void addLast(E e){
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    /**
     *  任意位置查询（重点在于要找到上一个节点）
     */
    public E get(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node curr = dummyHead.next;
        for(int i = 0 ; i < index ; i ++) {
            curr = curr.next;
        }
        return curr.val;

    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    /**
     *  任意位置重置元素（重点在于要找到上一个节点）
     */
    public void set(int index, E e){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node curr = dummyHead.next;
        for(int i = 0 ; i < index ; i ++) {
            curr = curr.next;
        }
        curr.val = e;
    }

    public boolean contains(E e){
        Node curr = dummyHead.next;
        while (curr != null){
            if(curr.val.equals(e)){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    /**
     *  任意位置删除元素（重点在于要找到上一个节点）
     */
    public E remove(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i ++) {
            prev = prev.next;
        }
        // 删除节点reNode的指针在上一节点prev.next
        Node reNode = prev.next;
        // 将删除节点reNode的下一节点指针给上一节点prev.next
        prev.next = reNode.next;
        // 删除节点reNode的next为null
        reNode.next = null;
        size --;
        return reNode.val;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    /**
     *  删除元素
     */
    public void removeElement(E e){

        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.val.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for(Node cur = dummyHead.next ; cur != null ; cur = cur.next){
            res.append(cur + "->");
        }
        res.append("NULL\n");
        res.append(String.format("LinkedList: size = %d\n", getSize()));
        res.append("-----------------------------------");
        return res.toString();
    }

}
