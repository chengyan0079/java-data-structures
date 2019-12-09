package cy.tree.binarySearchTree;

public class BSTreeTest {

    /**
     *               15
     *             /   \
     *           6     23
     *         /  \   /  \
     *       4    7  19  71
     *      / \
     *     2  5
     */
    public static void main(String[] args) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {15,6,23,4,7,19,71,2,5};
        for(int num : nums){
            bst.add(num);
        }
        // ǰ�������15,6,4,2,5,7,23,19,71
//        bst.proOrder();
        // �ǵݹ��㷨ǰ�����
//        bst.preOrderNR();

        // ���������2,4,5,6,7,15,19,23,71
//        bst.inOrder();
        bst.inOrderNR();
        // ���������2,5,4,7,6,19,71,23,15
//        bst.postOrder();
    }
}