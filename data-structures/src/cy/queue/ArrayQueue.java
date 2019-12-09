package cy.queue;

import cy.array.Array;

/**
 *  ���ڶ�̬�������
 *  �����ԣ���ɾ��Ԫ������Ҫ����λ��1λ�����Ӷ�ΪO(n)
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
     *  ��ȡ��������
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
     *  ���
     */
    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    /**
     *  ����
     */
    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("����Ϊ�գ�");
        }
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("����Ϊ�գ�");
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
