package cy.set;

import cy.linkedList.LinkedList;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> data;

    public LinkedListSet(){
        data = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if(!data.contains(e)){
            data.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        data.removeElement(e);
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
    public boolean contains(E e) {
        return data.contains(e);
    }
}
