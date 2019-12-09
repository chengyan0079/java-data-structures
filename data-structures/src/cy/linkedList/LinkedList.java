package cy.linkedList;

/**
 *  ��������
 *  ��ɾ�Ĳ� ����O(n)
 */
public class LinkedList<E> {

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

    // ����ͷ�ڵ㣬��ʾ��һ���ڵ�
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
     *  ����λ����ӣ��ص�����Ҫ�ҵ���һ���ڵ㣩
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
     *  ����λ�ò�ѯ���ص�����Ҫ�ҵ���һ���ڵ㣩
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
     *  ����λ������Ԫ�أ��ص�����Ҫ�ҵ���һ���ڵ㣩
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
     *  ����λ��ɾ��Ԫ�أ��ص�����Ҫ�ҵ���һ���ڵ㣩
     */
    public E remove(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i ++) {
            prev = prev.next;
        }
        // ɾ���ڵ�reNode��ָ������һ�ڵ�prev.next
        Node reNode = prev.next;
        // ��ɾ���ڵ�reNode����һ�ڵ�ָ�����һ�ڵ�prev.next
        prev.next = reNode.next;
        // ɾ���ڵ�reNode��nextΪnull
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
     *  ɾ��Ԫ��
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
