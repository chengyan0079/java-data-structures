package cy.queue;

public interface Queue<E> {

    int getSize();
    boolean isEmpty();

    /**
     *  ���
     */
    void enqueue(E e);

    /**
     *  ����
     */
    E dequeue();

    /**
     *  ��ͷ
     */
    E getFront();
}
