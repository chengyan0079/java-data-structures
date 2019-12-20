package cy.set;

import cy.tree.bst.BinarySearchTree;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTree<E> data;

    public BSTSet(){
        data = new BinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        data.add(e);
    }

    @Override
    public void remove(E e) {
        data.remove(e);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmply();
    }

    @Override
    public boolean contains(E e) {
        return false;
    }
}
