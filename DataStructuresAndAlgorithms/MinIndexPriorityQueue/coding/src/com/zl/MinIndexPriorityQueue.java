package com.zl;

// 最小索引优先队列
public class MinIndexPriorityQueue<V extends Comparable<V>> {
    // 记录元素个数
    private int size;
    // 用于索引关联元素,以元素进出的先后顺序存储
    private V[] keyVal;
    // 用于存放元素对应的关联索引,用最小优先队列的方式对元素值进行上浮下沉
    private int[] keys;
    // 用于存储keys数组的逆序
    private int[] syeK;
    // 构造函数,初始化操作
    public MinIndexPriorityQueue(int cap) {
        this.keyVal = (V[]) new Comparable[cap];
        this.keys = new int[cap];
        this.syeK = new int[cap];
        size = 0;
        for (int i = 0; i < cap; i++) {
            keys[i] = -1;
            syeK[i] = -1;
        }
    }

    // 插入元素并关联索引
    public void insert(int k,V v) {
        // 判断下标合法性
        if (k < 0 || k > keyVal.length) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
        // 元素数组k位置值设为v
        keyVal[k] = v;
        /*
        设置关联索引数组操作位置,如果原来有值则到逆序数组中查找对应的关联索引,
        原本没有值则在索引数组存在元素的最后一个位置的下一个位置设为操作位置
        */
        int index = syeK[k] == -1 ? size++ : syeK[k];
        // 关联索引操作位置索引值为k
        keys[index] = k;
        // 对新加入的关联索引,根据其关联的值对其进行上浮操作
        heapInsert(index);
        // 对新加入的关联索引,根据其关联的值对其进行下沉操作
        heapIfy(index);
        // 将逆序数组从新逆序
        toSyeK();
    }

    // 删除元素
    public V del(int k) {
        // 判断下标合法性
        checkIndex(k);
        // 判断k位置存不存在值
        if (!exist(k)) {
            throw new IndexOutOfBoundsException(k + "位置不存在!");
        }
        // 记录要删除的数据
        V r = keyVal[k];
        // 将k位置值设为null
        keyVal[k] = null;
        // 将关联索引数组中操作位置的关联索引与关联索引数组最后一个存在的关联索引交换位置
        swap(syeK[k],--size);
        // 再将换位后的最后一个存在的关联索引删除
        keys[size] = -1;
        // 对关联索引数组中操作位置的关联索引根据其关联的值对其进行下沉操作
        heapIfy(syeK[k]);
        // 将逆序数组从新逆序
        toSyeK();
        // 返回删除的数据
        return r;
    }

    // 上浮
    private void heapInsert(int i) {
        if (i == 0) {
            return;
        }
        int pat = ((i+1)>>1)-1;
        if(even(keys[pat],keys[i])) {
            swap(pat,i);
        }
        heapInsert(pat);
    }

    // 下沉
    private void heapIfy(int i) {
        int left = (i << 1) + 1;
        if (left >= size) {
            return;
        }
        int right = left + 1;
        int small = keys[right] == -1 ? left : even(keys[left],keys[right]) ? right : left;
        if (even(keys[i],keys[small])) {
            swap(i,small);
        }
        heapIfy(small);
    }

    // 判断i位置是否比j位置要小
    private boolean even(int i,int j) {
        return keyVal[i].compareTo(keyVal[j]) > 0;
    }

    // 交换两位置的索引
    private void swap(int i,int j) {
        keys[i] = keys[i] ^ keys[j];
        keys[j] = keys[i] ^ keys[j];
        keys[i] = keys[i] ^ keys[j];
    }

    // 存储keys逆序
    private void toSyeK() {
        for (int i = 0; i < syeK.length; i++) {
            syeK[i] = -1;
        }
        for (int i = 0; i < syeK.length; i++) {
            if (keys[i] != -1) {
                syeK[keys[i]] = i;
            }
        }
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 测试呈现
    public String toString() {
        String tmp = "";
        String tmp1 = "";
        String tmp2 = "";
        for (int i = 0; i < keys.length; i++) {
            tmp += "\t" + keyVal[i];
            tmp1 += "\t" + "["+ keys[i] + ":" + (keys[i] == -1 ? null : keyVal[keys[i]]) + "]";
            tmp2 += "\t" + syeK[i];
        }
        return "size:" + size + "|" + "heap:" + tmp + "\t|" + "keyVal:" + tmp1 + "\t|" + "syeK:" + tmp2;
    }

    // 判断存放元素的数组k位置是否存在元素
    public boolean exist(int k) {
        return keyVal[k] != null;
    }

    // 判断下标是否合法
    private void checkIndex(int k) {
        if (k < 0 | k >= keyVal.length) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
        if (!exist(k)) {
            throw new IndexOutOfBoundsException(k + "位置不存在!");
        }
    }

}
