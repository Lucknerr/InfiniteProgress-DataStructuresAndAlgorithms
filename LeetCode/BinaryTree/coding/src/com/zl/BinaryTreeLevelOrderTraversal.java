package com.zl;

import java.util.ArrayList;
import java.util.List;

// 二叉树的层序遍历
public class BinaryTreeLevelOrderTraversal {
    // 树的节点内部类
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    // 主函数
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(1,root,list);
        return list;
    }

    // 深度优先版本的层序遍历
    public void dfs(int i, TreeNode node, List<List<Integer>> list) {
        if (node == null) {
            return;
        }
        // 判断整体列表的长度
        if (list.size() < i) {
            // 如果长度到达或超过了层索引 那么就新建子列表
            list.add(new ArrayList<>());
        }
        // 将当前节点值存入对应层的子列表
        list.get(i - 1).add(node.val);
        // 去左子节点继续遍历
        dfs(i + 1, node.left, list);
        // 去左子节点继续遍历
        dfs(i + 1, node.right, list);
    }

    // 差一个真正意义上的广度优先遍历版本

    // 差差一个对数器
}
