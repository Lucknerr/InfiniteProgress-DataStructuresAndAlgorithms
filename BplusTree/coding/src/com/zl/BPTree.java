package com.zl;

import java.util.*;

// B+树实现
public class BPTree<K extends Comparable<K>,V> {
    // 度数: 最大子节点数
    private int degrees;
    // 节点关键字个数的上限
    private int upper;
    // 节点关键字个数的下限
    private int lower;
    // 记录根节点数组
    private BPNode root;
    // 记录叶子链表的头节点
    private BPNode head;
    // 记录叶子链表的尾节点
    private BPNode tail;
    // 节点内部类
    private class BPNode {
        // 标记为根节点
        boolean isRoot;
        // 标记为叶子节点:也就是真正存数据的节点
        boolean isLeaf;
        // 记录K值
        List<K> keyS;
        // 记录V值
        List<V> valS;
        // 记录父节点
        BPNode parent;
        // 记录子节点数组
        List<BPNode> sons;
        // 记录前一个节点
        BPNode pre;
        // 记录下一个节点
        BPNode next;
        // 构造方法初始化节点
        public BPNode(boolean isRoot,boolean isLeaf) {
            // 初始化标记节点拥有的身份
            this.isRoot = isRoot;
            this.isLeaf = isLeaf;
            // 初始化key数组
            this.keyS = new ArrayList<>();
            if (this.isLeaf) {
                // 初始化val数组
                this.valS = new ArrayList<>();
            }
        }
    }

    // 构造方法初始化B+树
    public BPTree(int degrees) {
        // 初始化的根节点即是根节点又是叶子节点
        this.root = new BPNode(true,true);
        // 初始化叶子链表的头节点
        this.head = this.root;
        // 初始化叶子链表的尾节点
        this.tail = this.root;
        // 初始化度数
        this.degrees = degrees;
        // 初始化上限
        this.upper = degrees - 1;
        // 初始化下限
        this.lower = this.upper >> 1;
    }

    // 向整棵树中添加新数据
    public boolean put(K key,V val) {
        return put(root,key,val);
    }

    // 向指定树中添加数据
    private boolean put(BPNode bpNode,K key,V val) {
        if(bpNode == null) {
            return false;
        }
        // 根据关键字在当前节点匹配将要去到子节点
        int index = finIndexToKeysForK(bpNode.keyS,key);
        // 判断目标子节点是否是叶子节点,如果是就将要添加的关键字插入目标子节点中
        if (bpNode.isLeaf) {
            // 将关键字和数据插入在节点的对应位置
            bpNode.keyS.add(index+1,key);
            bpNode.valS.add(index+1,val);
            if (bpNode.keyS.size() > upper) { // 加入后判断目标子节点的关键字数量是否已经达到上限
                // 如果达到则将目标子节点分裂
                BPNodeSplit(bpNode);
            }
            return true;
        } else { // 如果不是继续下子节点的子节点找
            put(bpNode.sons.get(index+1),key,val);
        }
        return false;
    }

    // 匹配关键字索引位置(二分查找)
    private int finIndexToKeysForK(List<K> keys,K key) {
        // 指定左指针
        int L = 0;
        // 指定右指针
        int R = keys.size() - 1;
        // 左指针还在右指针左边,继续二分
        while (L < R) {
            // 找当前两指针的中点位置
            int M = L + ((R - L) >> 1);
            // 比较指定关键字和中点位置的关键字
            int com = key.compareTo(keys.get(M));
            if (com < 0) { // 权值小
                // 右指针来到中点前一位
                R = M - 1;
            } else if (com > 0) { // 权值大
                // 左指针来到中点后一位
                L = M + 1;
            } else { // 权值相等
                // 直接返回中点位置
                return M;
            }
        }
        // 如果上文流程没有找到,说明左右指针已经重合,返回右指针
        return R;
    }

    // 节点分裂
    private void BPNodeSplit(BPNode bpNode) {
        // 创建左节点,新分裂的左右节点一定是叶子节点,肯定不是根节点
        BPNode left = new BPNode(false,bpNode.isLeaf);
        // 创建右节点
        BPNode right = new BPNode(false,bpNode.isLeaf);
        // 获取分裂点
        int M = upper>>1;
        // 获取上浮关键字
        K up = bpNode.keyS.get(M);
        // 如果指定节点为叶子节点
        if (bpNode.isLeaf) {
            // 先将新分裂的左右子节点链表指针维护
            linkedStick(bpNode,left,right);
            // 左节点关键字列表就是原节点关键字列表的前M位不包含M
            left.keyS = getKeySubList(bpNode.keyS,0,M);
            // 右节点关键字列表就是原节点关键字列的后M位包含M
            right.keyS = getKeySubList(bpNode.keyS,M,degrees);
            // 左节点数据列表就是原节点数据列表的前M位不包含M
            left.valS = getValSubList(bpNode.valS,0,M);
            // 右节点数据列表就是原节点数据列表的后M位包含M
            right.valS = getValSubList(bpNode.valS,M,degrees);
        } else { // 如果指定节点不为叶子节点
            // 指向要将关键字列表和子节点列表重新分配即可
            left.keyS = getKeySubList(bpNode.keyS,0,M);
            left.sons = getNodeSubList(bpNode.sons,0,M+1);
//            left.keyS = bpNode.keyS.subList(0,M);
//            left.sons = bpNode.sons.subList(0,M+1);       **subList: Debug时获取不了对象
            // 分配完后要让对应的子节点的父指针指向对应的父节点
            confessDad(left.sons,left);
            right.keyS = getKeySubList(bpNode.keyS,M+1,degrees);
            right.sons = getNodeSubList(bpNode.sons,M+1,degrees+1);
//            right.keyS = bpNode.keyS.subList(M+1,degrees);
//            right.sons = bpNode.sons.subList(M+1,degrees+1);
            confessDad(right.sons,right);
        }
        // 如果存在父节点
        if (bpNode.parent != null) {
            // 找到上浮关键字在父节点的插入位置
            int upIndex = finIndexToKeysForK(bpNode.parent.keyS,up);
            // 父节点的关键字列表对应位置插入上浮关键字
            bpNode.parent.keyS.add(upIndex + 1,up);
            // 移除对应位置的父节点的子节点列表
            bpNode.parent.sons.remove(upIndex + 1);
            // 父节点的子节点列表对应位置插入新分裂的左右子节点
            bpNode.parent.sons.add(upIndex + 1,left);
            bpNode.parent.sons.add(upIndex + 2,right);
            // 左右子节点父指针指向原节点的父节点
            left.parent = bpNode.parent;
            right.parent = bpNode.parent;
            // 此时插入完成上浮关键字后,判断父节点是否达到上限
            if (bpNode.parent.keyS.size() > upper) {
                // 若达到上限,继续分裂父节点
                BPNodeSplit(bpNode.parent);
            }
        } else { // 如果不存在父节点,说明当前分裂节点已经是根节点
            // 新创建根节点
            BPNode newRoot = new BPNode(true,false);
            // 新根节点的关键子列表的插入上浮关键字
            newRoot.keyS.add(up);
            // 新根节点创建子节点列表
            newRoot.sons = new ArrayList<>();
            // 新根节点的子节点列表插入新分裂的左右子节点
            newRoot.sons.add(left);
            newRoot.sons.add(right);
            // 新分裂的左右子节点的父指针指向新根节点
            left.parent = newRoot;
            right.parent = newRoot;
            // 根节点来到新根节点
            this.root = newRoot;
        }
    }

    // 深度优先遍历
    private Queue<K> DFS() {
        Queue<K> keys = new ArrayDeque<>();
        return DFS(root,keys);
    }

    // 深度优先遍历
    private Queue<K> DFS(BPNode bpNode, Queue<K> keys) {
        // 判断节点是否为空
        if (bpNode == null) {
            return keys;
        }
        // 如果节点还有子节点列表,获取子节点列表长度
        int sonsLen = bpNode.sons != null ? bpNode.sons.size() : -1;
        // 获取节点关键字列表长度
        int keysLen = bpNode.keyS.size();
        // 遍历指针
        int i = 0;
        while (i < keysLen) {
            // 遍历当前节点的关键字列表,并存入辅助队列中
            keys.add(bpNode.keyS.get(i++));
        }
        // 遍历指针
        int j = 0;
        while (j < sonsLen) {
            // 继续深度优先遍历下一个节点列表
            DFS(bpNode.sons.get(j++),keys);
        }
        return keys;
    }

    // 叶子节点链表正序遍历
    private List<K> ergodicLink() {
        List<K> list = new ArrayList<>();
        return ergodicLink(head, list);

    }

    // 叶子节点链表正序遍历
    private List<K> ergodicLink(BPNode bpNode,List<K> list) {
        // 判断节点是否为空
        if (bpNode == null) {
            return list;
        }
        // 遍历指针
        int i = 0;
        while (i < bpNode.keyS.size()) {
            // 遍历当前节点的关键字列表,并存入辅助数组中
            list.add(bpNode.keyS.get(i++));
        }
        // 继续遍历next节点列表
        return ergodicLink(bpNode.next,list);
    }

    // 叶子节点链表逆序遍历
    private List<K> reErgodicLink() {
        List<K> list = new ArrayList<>();
        return reErgodicLink(tail,list);

    }

    // 叶子节点链表逆序遍历
    private List<K> reErgodicLink(BPNode bpNode,List<K> list) {
        if (bpNode == null) {
            return list;
        }
        int i = bpNode.keyS.size()-1;
        while (i >= 0) {
            // 遍历当前节点的关键字列表,并存入辅助数组中
            list.add(bpNode.keyS.get(i--));
        }
        // 继续遍历pre节点列表
        return reErgodicLink(bpNode.pre,list);
    }

    // 叶子节点链表维护
    private void linkedStick(BPNode bpNode,BPNode left,BPNode right) {
        // 左节点的下一个节点就是右节点
        left.next = right;
        // 右节点的前一个节点就是左节点
        right.pre = left;
        // 如果当前节点的前后指针都是空,说明当前节点就是根节点
        if (bpNode.pre == null && bpNode.next == null) {
            // 头节点来到左节点
            head = left;
            // 为节点来到右节点
            tail = right;
            return;
        }
        // 当前节点是头节点
        if (bpNode == head) {
            // 头节点的下一个节点将成为右节点的下一个节点
            right.next = head.next;
            // 头节点的下一个节点的前一个节点变成右节点
            head.next.pre = right;
            // 头节点来到左节点
            head = left;
            return;
        }
        // 当前节点是尾节点
        if (bpNode == tail) {
            // 尾节点的前一个节点将成左节点的前一个节点
            left.pre = tail.pre;
            // 尾节点的前一个节点的下一个节点变成右节点
            tail.pre.next = left;
            // 尾节点来到右节点
            tail = right;
            return;
        }
        // 当前节点的前一个节点的下一个节点成为左节点
        bpNode.pre.next = left;
        // 左节点的前一个节点变成当前节点的前一个节点
        left.pre = bpNode.pre;
        // 当前节点的前一个节点变成右节点
        bpNode.next.pre = right;
        // 右节点的下一个节点变成当前节点的下一个节点
        right.next = bpNode.next;
    }

    // 认父
    private void confessDad(List<BPNode> bpNodes,BPNode parNode) {
        // 遍历节点列表
        for (BPNode bpNode : bpNodes) {
            // 当前节点的父节点指向指定的父节点
            bpNode.parent = parNode;
        }
    }

    // 以深度优先遍历打印关键字
    public String toStringForDFS() {
        Queue<K> keys = DFS();
        String tmp = "";
        for (K k : keys) {
            tmp += k + "\t";
        }
        return tmp;
    }

    // 以正序遍历链表方式打印关键字
    public String toStringForErgodicLink() {
        List<K> list = ergodicLink();
        String tmp = "";
        for (K k : list) {
            tmp += k + "\t";
        }
        return tmp;
    }

    // 以逆序遍历链表方式打印关键字
    public String toStringForReErgodicLink() {
        List<K> list = reErgodicLink();
        String tmp = "";
        for (K k : list) {
            tmp += k + "\t";
        }
        return tmp;
    }

    // 从fromIndex到toIndex不包含toIndex
    private List<K> getKeySubList(List<K> keys,int fromIndex,int toIndex) {
        List<K> subList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(keys.get(i));
        }
        return subList;
    }

    // 从fromIndex到toIndex不包含toIndex
    private List<V> getValSubList(List<V> vals,int fromIndex,int toIndex) {
        List<V> subList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(vals.get(i));
        }
        return subList;
    }

    // 从fromIndex到toIndex不包含toIndex
    private List<BPNode> getNodeSubList(List<BPNode> bpNodes,int fromIndex,int toIndex) {
        List<BPNode> subList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(bpNodes.get(i));
        }
        return subList;
    }
}
