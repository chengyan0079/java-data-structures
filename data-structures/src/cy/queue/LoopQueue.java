package cy.queue;

/**
 *  ��̬����ѭ������
 *  ����ʱ�ռ��1
 *  tail + 1 = front �����Ϊ����tail+1��data.lengthȡ����������front��Ϊ����
 *  tail == front �����Ϊ��
 *
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    // frontΪ����ͷ��tailΪ����β
    private int front, tail;

    private int size;

    public LoopQueue(int capacity){
        // ������1���ռ�
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    /**
     *  ��ȡ��������
     */
    public int getCapacity(){
        // ��������ʱ����1���ռ䣬���������1
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tail == front;
    }

    /**
     *  ���
     */
    @Override
    public void enqueue(E e) {
        // ����������
        if((tail + 1) % data.length == front){
            // ����
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    /**
     *  ����
     */
    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        E e = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return e;
    }

    /**
     *  ��ȡ����ͷ
     */
    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    /**
     *  ���ݣ�����
     */
    private void resize(int capacity){
        E[] newData = (E[]) new Object[capacity + 1];
        for(int i = 0;i < size;i ++){
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail){
                res.append(", ");
            }
        }
        res.append("] tail\n");
        res.append("-------------------------------");
        return res.toString();
    }

}
