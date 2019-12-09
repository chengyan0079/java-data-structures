package cy.stack;

public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    /**
     *  ��ջ
     */
    void push(E e);

    /**
     *  ��ջ
     */
    E pop();

    /**
     *  ջ��
     */
    E peek();
}
