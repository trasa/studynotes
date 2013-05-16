package com.meancat.study.datastructures;

import org.junit.Test;

public class BinaryTreeTest {

    @Test
    public void buildTree() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(50);
        tree.add(25);
        tree.add(75);
        tree.add(40);
        tree.add(41);
        tree.add(66);
        tree.add(76);
        /*             50
                  25          75
                     40     66  76
                        41
         */
        System.out.println(tree.toString());

        System.out.println(tree.preorder());
    }

}
