package com.zl.tree;

import com.zl.queue.Queue;
import com.zl.queue.impl.QueueArray;
import com.zl.queue.impl.QueueLink;

// 二叉搜索树
public class BinarySearchTree<K extends Comparable<K>,V> {
    // 记录节点个数
    private int size = 0;
    // 记录跟节点
    private Node root;
    // 节点类
    private class Node {
        // 键
        K key;
        // 值
        V val;
        // 左子节点
        Node left;
        // 右子节点
        Node right;
        // 节点内部类
        public Node(K key,V val,Node left,Node right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 构造方法
    public BinarySearchTree(K key,V val) {
        this.root = new Node(key,val,null,null);
        size++;
    }

    // 在整棵树中添加节点
    public void put(K key,V val) {
        root = put(root,key,val);
    }

    // 在指定树中添加节点,如果键一样直接改为新值
    public Node put(Node node,K key,V val) {
        // 当指定节点为空时,直接将新节点添加
        if(node == null) {
            // 节点个数加一
            size++;
            return new Node(key,val,null,null);
        }
        // 比较要添加的键和指定节点的键
        int com = key.compareTo(node.key);
        // 将要添加的键更大
        if (com > 0) {
            /*
            更大的键要添加指定节点的右树,递归条用添加方法
            由于是递归调用,所有指定当前递归序指定的节点的右子节点传入
            这样递归到一个节点为空时,就会在最开始判断节点是否为空时返回,返回值会返回上一层函数
            所以这里要将返回值赋值给新添加的节点的父节点,也就是函数返回时的上一层函数的指定节点的右子节点
            */
            node.right = put(node.right,key,val);
        } else if (com < 0) { // 指定节点的键更大
            /*
            较小的键要添加指定节点的左树中,递归条用添加方法
            由于是递归调用,所有指定当前递归序指定的节点的左子节点传入
            这样递归到一个节点为空时,就会在最开始判断节点是否为空时返回,返回值会返回上一层函数
            所以这里要将返回值赋值给新添加的节点的父节点,也就是函数返回时的上一层函数的指定节点的左子节点
            */
             node.left = put(node.left,key,val);
        } else { // 剩下的情况就只有指定键与要添加的键相等
            // 当键相等时,直接更改值内容
            node.val = val;
        }
        // 返回插入的节点
        return  node;
    }

    // 在整棵树中根据键删除节点
    public void del(K key) {
        root = del(root,key);
    }

    // 在指定树中根据键删除节点
    public Node del(Node node,K key) {
        // 找不到节点时,返回null
        if (node == null) {
            return null;
        }
        int com = key.compareTo(node.key);
        if (com > 0) {
            node.right =  del(node.right,key);
        } else if (com < 0) {
            node.left = del(node.left,key);
        } else {
            size--;
            // 当指定节点的左子树为空时,那么删除该节点时就直接将该节点的右子树接到该节点的父节点即可
            if (node.left == null) {
                return node.right;
            }
            // 当指定节点的右子树为空时,那么删除该节点时就直接将该节点的右子树接到该节点的父节点即可
            if (node.right == null) {
                return node.left;
            }
            // 获取将被删除节点右子树中最小节点
            Node minNode = minKey(node.right);
            // 如果最小节点就是将被删除节点的右子节点,这种情况不需要考虑最小节点的右子树
            if (minNode == node.right) {
                // 只需要将最小节点的左指针指向将被删除节点的左子树
                minNode.left = node.left;
                // 返回最小节点,相当于返回值返回给上一层递归,也就是被删删除节点的原父节点
                return minNode;
            }
            // 获取将被删除节点右子树中最小节点的父节点
            Node minNodePat = minPatK(node.right);
            // 这里要考虑最小节点存不存在右子树,如果存在,需要将最小节点的父节点的左指针指向做小节点的右子树,不存在直接设置为空
            minNodePat.left = minNode.right != null ? minNode.right : null;

            // 最小节点的左指针指向被删除节点的左子树
            minNode.left = node.left;
            // 最小节点的右指针指向被删除节点的右子树
            minNode.right = node.right;
            // 返回最小节点,相当于返回值返回给上一层递归,也就是被删删除节点的原父节点
            return minNode;
        }
        // 这句其实没有什么实际意义,为了不报错
        return node;
    }
    

    // 获取节点个数
    public int size() {
        return size;
    }

    // 在整棵树中根据键获取节点
    public V get(K key) {
        return get(root,key);
    }

    // 在指定树中根据键获取节点
    public V get(Node node,K key) {
        // 找不到节点返回null
        if (node == null) {
            return null;
        }
        int com = key.compareTo(node.key);
        if (com > 0) {
            return get(node.right,key);
        } else if (com < 0) {
            return get(node.left,key);
        } else {
            return node.val;
        }
    }

    // 获取整棵树最小键节点
    public Node minKey() {
        return minKey(root);
    }

    // 指定树最小键节点
    private Node minKey(Node node) {
        return node.left == null ? node : minKey(node.left);
    }

    // 获取整棵树最大键节点
    public Node maxKey() {
        return maxKey(root);
    }

    public V getV(Node node) {
        return node.val;
    }

    public K getK(Node node) {
        return node.key;
    }

    // 指定树最大键节点
    public Node maxKey(Node node) {
        return node.right == null ? node : maxKey(node.right);
    }

    // 找以node为跟节点的树中最小键的节点的父节点
    private Node minPatK(Node node) {
        if (node == null || node.left == null){
            return null;
        }
        return node.left.left == null ? node : node.left;
    }

    // 整棵树前序遍历
    public Queue<K> preErgodic() {
        Queue<K> queue = new QueueArray<K>();
        return preErgodic(root,queue);
    }

    // 指定树前序遍历
    public Queue<K> preErgodic(Node node,Queue<K> queue) {
        if (node == null) {
            return null;
        }
        /*
        将键放入队列
        纵观整个递归过程,这里将键放入队列操作可以看作一次对节点的访问
        那么此时该位置的访问时机就是第一次来到节点的时机
        */
        queue.enqueue(node.key);

        // 如果左树不空,那么继续递归调用函数 (指定节点为当前节点的左子树) 直到指定节点的左子树为空时返回上一层
        // 此时该位置的访问时机就是第二次来到节点的时机
        if(node.left != null) {

            // 继续以深度优先遍历的方式遍历
            preErgodic(node.left,queue);
        }


        // 如果右树不空,那么继续递归调用函数 (指定节点为当前节点的右子树) 直到指定节点的右子树为空时返回上一层
        // 此时该位置的访问时机就是第三次来到节点的时机
        if (node.right != null) {
            // 继续以深度优先遍历的方式遍历
            preErgodic(node.right,queue);
        }

        return queue;
    }

    // 中序遍历
    public Queue<K> midErgodic() {
        Queue<K> queue = new QueueArray<>();
        return midErgodic(root,queue);
    }

    // 指定树中序遍历
    public Queue<K> midErgodic(Node node,Queue<K> queue) {
        if (node == null) {
            return null;
        }

        // 如果左树不空,那么继续递归调用函数 (指定节点为当前节点的左子树) 直到指定节点的左子树为空时返回上一层
        // 此时该位置的访问时机就是第一次来到节点的时机
        if(node.left != null) {
            // 继续以深度优先遍历的方式遍历
            midErgodic(node.left,queue);
        }

        /*
        将键放入队列
        纵观整个递归过程,这里将键放入队列操作可以看作一次对节点的访问
        那么此时该位置的访问时机就是第二次来到节点的时机
        */
        queue.enqueue(node.key);

        // 如果右树不空,那么继续递归调用函数 (指定节点为当前节点的右子树) 直到指定节点的右子树为空时返回上一层
        // 此时该位置的访问时机就是第三次来到节点的时机
        if (node.right != null) {
            // 继续以深度优先遍历的方式遍历
            midErgodic(node.right,queue);
        }

        return queue;
    }


    // 后序遍历
    public Queue<K> afterErgodic() {
        Queue<K> queue = new QueueArray<>();
        return afterErgodic(root,queue);
    }

    // 指定树后序遍历
    public Queue<K> afterErgodic(Node node,Queue<K> queue) {
        if (node == null) {
            return null;
        }

        // 如果左树不空,那么继续递归调用函数 (指定节点为当前节点的左子树) 直到指定节点的左子树为空时返回上一层
        // 此时该位置的访问时机就是第一次来到节点的时机
        if(node.left != null) {
            // 继续以深度优先遍历的方式遍历
            afterErgodic(node.left,queue);
        }

        // 如果右树不空,那么继续递归调用函数 (指定节点为当前节点的右子树) 直到指定节点的右子树为空时返回上一层
        // 此时该位置的访问时机就是第二次来到节点的时机
        if (node.right != null) {
            // 继续以深度优先遍历的方式遍历
            afterErgodic(node.right,queue);
        }

        /*
        将键放入队列
        纵观整个递归过程,这里将键放入队列操作可以看作一次对节点的访问
        那么此时该位置的访问时机就是第三次来到节点的时机
        */
        queue.enqueue(node.key);

        return queue;
    }

    // 整棵树层序遍历
    public Queue<K> hierarchyErgodic() {
        return hierarchyErgodic(root,new QueueArray<>(),new QueueArray<>());
    }

    // 指定树层序遍历
    // queue内放节点
    // help辅助队列依次放节点的键
    private Queue<K> hierarchyErgodic(Node node,Queue<Node> queue,Queue<K> help) {
        // 首先默认指定树的根节点先放入队列
        queue.enqueue(node);
        // 遍历并取出队列内的元素,如果队列为空停止遍历,返回辅助队列
        while (!queue.isEmpty()) {
            // 弹出一个队列元素,自然就是刚放进去的元素,按找队列的先进先出原则弹出
            Node theNode = queue.dequeue();
            // 将弹出的元素的键放入辅助队列
            help.enqueue(theNode.key);
            // 判断当前操作的元素的左指针是否存在
            if (theNode.left != null) {
                // 存在,放入队列中
                queue.enqueue(theNode.left);
            }
            // 判断当前操作的元素的右指针是否存在
            if (theNode.right != null) {
                // 存在,放入队列中
                queue.enqueue(theNode.right);
            }
        }
        // 遍历完成,返回辅助数组
        return help;
    }

    // 获取整个树的最大深度
    public int maxDeep() {
        return maxDeep(root);
    }

    // 获取指定树的最大深度
    private int maxDeep(Node node) {
        // 如果指定树不存在,直接返回空
        if (node == null) {
            return 0;
        }
        // 用于记录左树深度
        int leftDeep = 0;
        // 用于记录右树深度
        int rightDeep = 0;
        // 如果左树存在
        if (node.left != null) {
            // 左树最大深度等于递归调后的返回值
            leftDeep = maxDeep(node.left);
        }
        // 如果右树存在
        if (node.right != null) {
            // 右树最大深度等于递归调用后的返回值
            rightDeep = maxDeep(node.right);
        }
        // 取左右树最大深度当中的较大值并且加一,加一的含义就是加上根节点本层深度
        return Math.max(leftDeep,rightDeep) + 1;
    }

}
