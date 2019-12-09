package cy.stack;

import cy.linkedList.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> data;

    public LinkedListStack(){
        this.data = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(E e) {
        data.addFirst(e);
    }

    @Override
    public E pop() {
        return data.removeFirst();
    }

    @Override
    public E peek() {
        return data.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(data);
        return res.toString();
    }
}
