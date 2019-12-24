package cy.tree.segment;

/**
 *   此处为两个元素逻辑接口
  */
public interface Merger<E> {

    // 两个元素之和
    E merge(E a, E b);
}
