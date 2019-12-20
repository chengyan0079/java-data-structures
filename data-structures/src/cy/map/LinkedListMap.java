package cy.map;

public class LinkedListMap<K ,V> implements Map<K ,V> {

    private class Node{

        public K key;
        public V value;

        public Node next;

        public Node(K key, V value ,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value){
            this(key, value, null);
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString(){
            return key.toString()+":"+value.toString();
        }
    }

    private Node dummyHead;

    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
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

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    private Node getNode(K key){
        Node curr = dummyHead.next;
        while(curr != null){
            if(curr.key.equals(key)){
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    /**
     *  根据key找节点
     */
    @Override
    public V get(K key) {
        return getNode(key) == null ? null : getNode(key).value;
    }

    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            dummyHead.next = new Node(key, value, dummyHead.next);
            size ++;
        }else{
            node.value = value;
        }

    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if(node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    @Override
    public V remove(K key) {
        Node preNode = dummyHead;
        while (preNode.next != null){
            if(preNode.next.key.equals(key)){
                break;
            }
            preNode = preNode.next;
        }

        if(preNode.next != null){
            Node delNode = preNode.next;
            preNode.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }
        return null;
    }


}
