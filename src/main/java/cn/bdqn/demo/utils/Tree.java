package cn.bdqn.demo.utils;


import cn.bdqn.demo.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    /**
     * 使用递归方法建菜单
     * @param list
     * @return
     */
    public static List<TreeNode> buildTree(List<TreeNode> list) {
        List<TreeNode> trees = new ArrayList<>();
        for (TreeNode TreeNode : list) {
            if (TreeNode.getParents().equals(TreeNode.getId().replace("0x","0x00"))){
                System.out.println(TreeNode);
                trees.add(findChildren(TreeNode,list));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static TreeNode findChildren(TreeNode TreeNode, List<TreeNode> treeNodes) {
        TreeNode.setChildren(new ArrayList<TreeNode>());
        for (TreeNode tree : treeNodes) {
            StringBuilder str = new StringBuilder(TreeNode.getParents());
            if(str.append(tree.getId().replace("0x", "00")).toString().equals(tree.getParents())){
                if (TreeNode.getChildren() == null) {
                    TreeNode.setChildren(new ArrayList<>());
                }
                System.out.println(tree);
                TreeNode.getChildren().add(findChildren(tree,treeNodes));
            }
        }
        return TreeNode;
    }
}
