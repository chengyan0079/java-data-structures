package cy.queue;

public interface Queue<E> {

    int getSize();
    boolean isEmpty();

    /**
     *  入队
     */
    void enqueue(E e);

    /**
     *  出队
     */
    E dequeue();

    /**
     *  队头
     */
    E getFront();
}
