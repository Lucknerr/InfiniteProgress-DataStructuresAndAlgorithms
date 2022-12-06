package com.zl.tree;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.zl.queue.Queue;
import com.zl.queue.impl.QueueArray;

// 红黑树
public class RedBlackTree<K extends Comparable<K>,V> {
    // 记录节点个数
    private int N;
    // 记录根节点
    private Node root;
    // 标识为红色
    private static final boolean RED = true;
    // 标识为黑色
    private static final boolean BLACK = false;
    // 节点类
    private class Node {
        // 键
        K K;
        // 值
        V V;
        // 左子节点
        Node L;
        // 右子节点
        Node R;
        // 节点颜色
        boolean C;
        // 构造方法,初始化节点
        public Node(K K,V V,Node L,Node R,boolean C) {
            this.K = K;
            this.V = V;
            this.L = L;
            this.R = R;
            this.C = C;
        }
    }
    // 初始化红黑树
    public RedBlackTree(K K,V V) {
        this.root = new Node(K,V,null,null,BLACK);
        this.N++;
    }

    // 向以root为根节点的树中添加元素
    public void put(K K,V V) {
        root = put(root,K,V);
        root.C = BLACK;
    }

    // 向指定node为根节点的树中添加元素
    private Node put(Node node,K K,V V) {
        if (node == null) {
            N++;
            return new Node(K,V,null,null,RED);
        }
        int com = K.compareTo(node.K);
        if (com > 0) {
            node.R = put(node.R,K,V);
        } else if (com < 0) {
            node.L = put(node.L,K,V);
        } else {
            node.V = V;
        }
        // 颜色反转:当前节点的左右指针都为红色
        if (node.L != null && node.R != null) {
            if (node.L.C == RED && node.R.C == RED) {
                rollBack(node);
            }
        }
        // 左旋:当前节点的左指针为黑色,右指针为红色
        if (node.R != null) {
            if (node.R.C == RED) {
                node = leftRotate(node);
            }
        }
        // 右旋:当前节点的左指针为红色并且左子节点的左指针为红色
        if (node.L != null && node.L.L != null) {
            if (node.L.C == RED && node.L.L.C == RED) {
                node = rightRotate(node);
            }
        }
        return node;
    }

    // 在以root为根节点的树中删除键为K的节点并返回其值
    public void del(K K) {
        root = del(root,K);
    }

    public void unBreakDel(K K) {
        unBreakDel(root,K);
    }
    
    // 在指定node为根节点的树中删除键为K的节点并返回其值
    private Node del(Node node,K K) {
        if (node == null) {
            return null;
        }
        int com = K.compareTo(node.K);
        if (com > 0) {
            node.R = del(node.R,K);
        } else if (com < 0) {
            node.L = del(node.L,K);
        } else {
            if (node.L == null) {
                return node.R;
            }
            if (node.R == null) {
                return node.L;
            }
            Node successor = getSuccessor(node.R);
            if (successor == node.R) {
                successor.L = node.L;
                return successor;
            }
            Node successorPat = getSuccessorPat(node.R);
            successorPat.C = BLACK;
            successorPat.L = successor.R != null ? successor.R : null;
            successor.L = node.L;
            successor.R = node.R;
            return successor;
        }
        return node;
    }

    private void unBreakDel(Node node,K k) {
        Node successor = getSuccessor(node.R);
        if (successor == node.R) {
            setNode(root,k,successor);
            return;
        }
        Node successorPat = getSuccessorPat(node.R);
        setNode(node,k,successor.K,successor.V);
        successorPat.L = successor.R != null ? successor.R : null;

    }

    private void setNode(Node node,K k,K kv,V v) {
        if (node == null) {
            return;
        }
        int com = k.compareTo(node.K);
        if (com > 0) {
            setNode(node.R,k,kv,v);
        } else if (com < 0) {
            setNode(node.L,k,kv,v);
        } else {
            if (node.L == null) {
                setNode(root,k,node.R.K,node.R.V);
                return;
            }
            if (node.R == null) {
                setNode(root,k,node.L.K,node.L.V);
                return;
            }
            node.V = v;
            node.K = kv;
        }
    }

    private Node setNode(Node node,K k,Node newNode) {
        if (node == null) {
            return null;
        }
        int com = k.compareTo(node.K);
        if (com > 0) {
            node.R = setNode(node.R,k,newNode);
        } else if (com < 0) {
            node.R = setNode(node.L,k,newNode);
        } else {
            return newNode;
        }
        return node;
    }

    // 在以root为根节点的树中修改键为K元素的值
    public void set(K K,V V) {

    }

    // 在指定node为根节点的树中修改键为K元素的值
    private void set(Node node,K K,V V) {

    }

    // 在以root为根节点的树中查找键为K元素的值
    public V get(K K) {
        return get(root,K);
    }

    // 在指定node为根节点的树中查找键为K元素的值
    public V get(Node node,K K) {
        if (node == null) {
            return null;
        }
        int com = K.compareTo(node.K);
        if (com > 0) {
            return get(node.R,K);
        } else if (com < 0) {
            return get(node.L,K);
        } else {
            return node.V;
        }
    }

    private Node getSuccessor(Node node) {
        return node.L == null ? node : getSuccessor(node.L);
    }

    private Node getSuccessorPat(Node node) {
        if (node.L == null) {
            return null;
        }
        return node.L.L == null ? node : getSuccessorPat(node.L);
    }

    // 判断树是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    // 获取树的节点个数
    public int size() {
        return N;
    }

    // 左旋
    private Node leftRotate(Node node) {
        Node right = node.R;
        // 当前节点的右指针指向当前节点右子节点左子节点
        node.R = node.R.L;
        // 再将当前节点的右子节点的左指针指向当前节点
        right.L = node;
        // 再将当前节点的原右子节点由父节点指向的指针颜色变为当前节点由父节点指向的指针颜色
        right.C = node.C;
        // 再将当前节点由父节点指向的指针颜色变为红色
        node.C = RED;
        // 返回当前节点原右子节点
        return right;
    }

    // 右旋
    private Node rightRotate(Node node) {
        Node left = node.L;
        // 当前节点的左指针指向当前节点的左子节点的右子节点
        node.L = node.L.R;
        // 再将当前节点的左子节点的右指针指向当前节点
        left.R = node;
        // 再将当前节点的原左子节点由父节点指向的指针颜色变为当前节点由父节点指向的指针颜色
        left.C = node.C;
        // 再将当前节点由父节点指向的指针颜色变为红色
        node.C = RED;
        // 返回当前节点原左子节点颜色反转后样子
        return rollBack(left);
    }

    // 颜色反转
    private Node rollBack(Node node) {
        node.L.C = BLACK;
        node.R.C = BLACK;
        node.C = RED;
        return node;
    }

    // 前序遍历整棵树
    public Queue<K> preErgodic() {
        Queue<K> queue = new QueueArray<>();
        return preErgodic(root,queue);
    }

    // 前序遍历指定node为根节点的树
    private Queue<K> preErgodic(Node node,Queue<K> queue) {
        if (node == null) {
            return null;
        }
        queue.enqueue(node.K);
        if (node.L != null) {
            preErgodic(node.L,queue);
        }
        if (node.R != null) {
            preErgodic(node.R,queue);
        }
        return queue;
    }

    // 前序遍历整棵树
    public Queue<Node> preNodeErgodic() {
        Queue<Node> queue = new QueueArray<>();
        return preNodeErgodic(root,queue);
    }

    // 前序遍历指定node为根节点的树,队列中存放的是节点
    private Queue<Node> preNodeErgodic(Node node,Queue<Node> queue) {
        if (node == null) {
            return null;
        }
        queue.enqueue(node);
        if (node.L != null) {
            preNodeErgodic(node.L,queue);
        }
        if (node.R != null) {
            preNodeErgodic(node.R,queue);
        }
        return queue;
    }

    // 中序遍历整棵树
    public Queue<K> midErgodic() {
        Queue<K> queue = new QueueArray<>();
        return midErgodic(root,queue);
    }

    // 中序遍历指定node为根节点的树
    private Queue<K> midErgodic(Node node,Queue<K> queue) {
        if (node == null) {
            return null;
        }
        if (node.L != null) {
            midErgodic(node.L,queue);
        }
        queue.enqueue(node.K);
        if (node.R != null) {
            midErgodic(node.R,queue);
        }
        return queue;
    }

    // 后序遍历整棵树
    public Queue<K> postErgodic() {
        Queue<K> queue = new QueueArray<>();
        return postErgodic(root,queue);
    }

    // 后序遍历指定node为根节点的树
    private Queue<K> postErgodic(Node node,Queue<K> queue) {
        if (node == null) {
            return null;
        }
        if (node.L != null) {
            postErgodic(node.L,queue);
        }
        if (node.R != null) {
            postErgodic(node.R,queue);
        }
        queue.enqueue(node.K);
        return queue;
    }

    // 层序遍历整棵树
    public Queue<K> layerErgodic() {
        return layerErgodic(root,new QueueArray<>(),new QueueArray<>());
    }

    // 层序遍历指定node为根节点的树
    public Queue<K> layerErgodic(Node node,Queue<Node> queue,Queue<K> help) {
        queue.enqueue(node);
        while (!queue.isEmpty()) {
            Node tmpNode = queue.dequeue();
            help.enqueue(tmpNode.K);
            if (tmpNode.L != null) {
                queue.enqueue(tmpNode.L);
            }
            if (tmpNode.R != null) {
                queue.enqueue(tmpNode.R);
            }
        }
        return help;
    }

    // 测试呈现
    public String toString() {
        Queue<Node> queue = preNodeErgodic();
        String tmp = "";
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            tmp += "\t[" + (node.C == false ? "黑" : "红") + ":" + node.K + "-"
                         + (node.L != null ? (node.L.C == false ? "黑" : "红") : null) + ":" + (node.L != null ? node.L.K : null) + "-"
                         + (node.R != null ? (node.R.C == false ? "黑" : "红") : null) + ":" + (node.R != null ? node.R.K : null) + "]";
        }
        return tmp;

    }
}
