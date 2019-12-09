package cy.queue;

import cy.array.Array;

/**
 *  基于动态数组队列
 *  局限性：在删除元素是需要整体位移1位。复杂度为O(n)
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> data;

    public ArrayQueue(int capacity){
        data = new Array<>(capacity);
    }

    public ArrayQueue(){
        data = new Array<>();
    }

    /**
     *  获取队列容量
     */
    public int getCapacity(){
        return data.getCapacity();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmply();
    }

    /**
     *  入队
     */
    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    /**
     *  出队
     */
    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("队列为空！");
        }
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("队列为空！");
        }
        return data.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        for(int i = 0 ; i < data.getSize() ; i ++){
            res.append(data.get(i));
            if(i != data.getSize() - 1){
                res.append(", ");
            }
        }
        res.append("] tail\n");
        res.append("-------------------------------");
        return res.toString();
    }
}
