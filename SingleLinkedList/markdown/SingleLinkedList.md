# 单向链表(SingleLinkedList)

### 1.图示

![SingleLinkedListFull.drawio](..\images\SingleLinkedListFull.png)

### 2.过程

### 3.实现

```java
// 单向链表
public class SingleLinkedList<T> {
    // 记录链表内含元素个数
    private int size = 0;
    // 存放链表头节点
    private Node<T> head;
    // 创建节点类型
    private class Node<T> {
        // 存放节点值
        T value;
        // 记录下一个节点内存地址,可以看做指向下一个节点的指针
        Node<T> next;
        // 添加含参构造方法
        public Node(T value,Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    // 指定类似索引添加节点元素
    public void add(int index,T value) {
        // 当index大于size时则视为无效索引
        if (index > size) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
        // 当index等于0时,也就是往原来的头节点添加节点元素
        if (index == 0) {
            // 创捷节点对象时新节点的下一个节点就是原来的头节点,当前头节点来到新加入的节点
            head = new Node<T>(value,head);
            // 节点个数加一
            size++;
            return;
        }
        // 找到对应位置前一个节点
        Node<T> prev = findNodeOfIndex(index-1);
        // 为需要添加的元素创建Node对象,并在创建对象的时候接入包含指定插入位置内后面所有的节点
        // 再将指定插入位置前所有的节点连接起来
        prev.next = new Node<T>(value,prev.next);
        // 节点个数加一
        size++;
    }

    // 删除尾节点元素
    public T remove(int index) {
        // 判断下标是否合法并抛出异常
        checkIndex(index);
        // 当索引为时
        if (index == 0) {
            // 头节点来到原来头节点的下一个位置
            head = head.next;
            // 节点数减一
            size--;
            // 返回头节点元素值
            return head.value;
        }
        // 找到指定索引前一个节点
        Node<T> prev = findNodeOfIndex(index-1);
        // 将指定索引的节点存入对象
        Node<T> remove = prev.next;
        // 直接将指定索引前一个节点的下一个位置设置为指定索引的下一个节点
        prev.next = prev.next.next;
        // 节点个数减一
        size--;
        // 返回被移除的节点值
        return remove.value;
    }

    // 指定位置设置节点元素值
    public void set(int index,T value) {
        // 判断下标是否合法并抛出异常
        checkIndex(index);
        // 将指定索引节点值设置为指定值
        findNodeOfIndex(index).value = value;
    }

    // 查看指定位置元素值
    public T get(int index) {
        // 判断下标是否合法并抛出异常
        checkIndex(index);
        // 返回指定索引节点值
        return findNodeOfIndex(index).value;
    }

    // 找到指定类似索引位置的节点
    private Node<T> findNodeOfIndex(int index) {
        // 创建节点对象并初始化为头节点
        Node<T> node = head;
        // 通过index控制遍历次数,当index=0时一次都不遍历,就返回头节点的位置,当index=2时遍历两次,所以返回链表中第三个节点
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
	
    // 测试呈现
    public String toString() {
        String tmp = "";
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            tmp += "\t" + node.value + ";";
            node = node.next;
        }
        return tmp;
    }

    // 判断下标是否合法并抛出异常
    private void checkIndex(int index) {
        // 当指定下标不小于size视为不合法下标
        if (index >= size) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
    }

}
```

