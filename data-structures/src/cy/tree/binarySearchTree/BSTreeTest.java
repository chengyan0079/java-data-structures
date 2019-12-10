package cy.tree.binarySearchTree;

import java.util.List;

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
        // 前序遍历：15,6,4,2,5,7,23,19,71
//        bst.proOrder();
//        // 非递归算法前序遍历
//        bst.preOrderNR();
//        // 中序遍历：2,4,5,6,7,15,19,23,71
//        bst.inOrder();
//        // 非递归算法中序遍历
//        bst.inOrderNR();
//        // 后序遍历：2,5,4,7,6,19,71,23,15
//        bst.postOrder();
//        // 非递归算法后序遍历
//        bst.postOrderNR();

        // 非递归算法层序遍历：15,6,23,4,7,19,71,2,5
//        bst.levelOrder();

        List<Integer> preOrder = bst.preOrderTraveral();
        StringBuilder sb = new StringBuilder();
        for(Integer i: preOrder){
            sb.append(i+",");
        }
        System.out.println(sb.toString());
    }
}
