package cy.tree.segment;

/**
 *   �˴�Ϊ����Ԫ���߼��ӿ�
  */
public interface Merger<E> {

    // ����Ԫ��֮��
    E merge(E a, E b);
}
