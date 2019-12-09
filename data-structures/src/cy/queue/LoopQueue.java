package cy.queue;

/**
 *  动态扩容循环队列
 *  申请时空间多1
 *  tail + 1 = front 则队列为满（tail+1和data.length取余数，等于front则为满）
 *  tail == front 则队列为空
 *
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    // front为队列头，tail为队列尾
    private int front, tail;

    private int size;

    public LoopQueue(int capacity){
        // 多申请1个空间
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    /**
     *  获取队列容量
     */
    public int getCapacity(){
        // 由于申请时多了1个空间，所以这里减1
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
     *  入队
     */
    @Override
    public void enqueue(E e) {
        // 如果入队已满
        if((tail + 1) % data.length == front){
            // 扩容
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    /**
     *  出队
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
     *  获取队列头
     */
    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    /**
     *  扩容，缩容
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
