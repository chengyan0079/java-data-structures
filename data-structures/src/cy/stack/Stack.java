package cy.stack;

public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    /**
     *  »Î’ª
     */
    void push(E e);

    /**
     *  ≥ˆ’ª
     */
    E pop();

    /**
     *  ’ª∂•
     */
    E peek();
}
