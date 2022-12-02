# 无向图(UnorientedGraph)

### 1.图示



### 2.过程

### 3.实现

##### List接口

```java
public interface List<T> {

    public void add(int index,T value);

    public T remove(int index);

    public void set(int index,T value);

    public T get(int index);

    public boolean isEmpty();

    public int size();
}
```

##### DynamicArray实现List接口

```java
public class DynamicArray<T> implements List<T> {
    private int size = 0;
    private final static int INIT_LENGTH = 10;

    private int dilatationNums;
    private T[] array;

    public DynamicArray() {
        this(INIT_LENGTH);
    }

    public DynamicArray(int capacity) {
        this.array = (T[]) new Object[capacity > INIT_LENGTH ? capacity : INIT_LENGTH];
    }

    @Override
    public void add(int index, T value) {
        addCheckIndex(index);
        dilatation();
        insert(index,value);
    }

    public void endAdd(T value) {
        add(size,value);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T r = array[index];
        del(index);
        unDilatation();
        return r;
    }

    @Override
    public void set(int index, T value) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index不合法");
        }
    }

    private void addCheckIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index不合法");
        }
    }
    
    private void dilatation() {
        if (size == array.length) {
            dilatationNums = array.length >> 1;
            int capacity = array.length + dilatationNums;
            copyArray(capacity);
        }
    }
    
    private void unDilatation() {
        if (array.length-size > dilatationNums) {
            int capacity = array.length - dilatationNums;
            copyArray(capacity);
        }
    }
    
    private void copyArray(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
    
    private void insert(int index, T value) {
        for (int i = size; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = value;
        size++;
    }
    
    private void del(int index) {
        for (int i = index; i < size; i++) {
            array[i] = array[i+1];
        }
        array[size--] = null;
    }
    
    public String toString() {
        String eles = "";
        for (int i = 0; i < array.length;i++) {
            eles += "\t" + array[i] + ";";
        }
        return "size:"+ size + ";\nlength:" + array.length + ";\ndilatancyNms:" + dilatationNums + ";\neles:" + eles;
    }

}
```

##### Queue接口

```java
public interface Queue<T> {

    public void enqueue(T t);

    public T dequeue();

    public int size();

    public boolean isEmpty();

}
```

##### QueueArray实现Queue接口

```java
public class QueueArray<T> implements Queue<T> {
    List<T> list = new DynamicArray<>();

    @Override
    public void enqueue(T t) {
        list.add(list.size(), t);
    }

    @Override
    public T dequeue() {
        return list.remove(0);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    public String toString() {
        return list.toString();
    }
}
```

##### UnorientedGraph接口

```java
// 无向图接口实现
public interface UnorientedGraph {
    // 获取图中顶点数量
    public int peakSize();
    // 获取图中边的数量
    public int sideSize();
    // 向图中添加一条边
    public void addSide(int p1,int p2);
    // 获取指定节点的相邻所有节点
    public Queue<Integer> getAdj(int peak);
    // 非连通,连通通用深度优先搜索
    public Queue<Integer> depthFirstSearch(int peak);
    // 非连通,连通通用广度优先搜索
    public Queue<Integer> breadthFirstSearch(int peak);
    // 记录多少个点于指定节点相连通
    public int open(int peak);
}
```

##### UnorientedGraphArray实现UnorientedGraph接口

```java
// 无向图实现
public class UnorientedGraphArray implements UnorientedGraph {
    // 记录顶点数
    private int peakSize;
    // 记录边数量
    private int sideSize;
    // 获取顶点数量
    private Queue<Integer>[] queues;

    public UnorientedGraphArray(int peakSize) {
        // 初始化顶点数
        this.peakSize = peakSize;
        // 初始化边数
        this.sideSize = 0;
        // 初始化主表
        this.queues = new QueueArray[peakSize];
        // 初始化侧链
        for (int i = 0; i < peakSize; i++) {
            queues[i] = new QueueArray<>();
        }
    }
    @Override
    public int peakSize() {
        return peakSize;
    }
    // 获取边数量
    @Override
    public int sideSize() {
        return sideSize;
    }
    // 增加一条边
    @Override
    public void addSide(int p1, int p2) {
        queues[p1].enqueue(p2);
        queues[p2].enqueue(p1);
        sideSize++;
    }
    // 获取指定节点的相邻所有节点
    @Override
    public Queue<Integer> getAdj(int peak) {
        return queues[peak];
    }

    // 非连通嵌套连通深度优先遍历,做到通用遍历
    @Override
    public Queue<Integer> depthFirstSearch(int peak) {
        Queue<Integer> help = new QueueArray<>();
        boolean[] unVirgin = new boolean[peakSize];
        help.enqueue(peak);
        unVirgin[peak] = true;
        depthFirstSearch(peak,help,unVirgin);
        for (int i = 0; i < queues.length; i++) {
            if (unVirgin[i]) {
                continue;
            }
            help.enqueue(i);
            unVirgin[i] = true;
            depthFirstSearch(i,help,unVirgin);
        }
        return help;
    }

    // 单纯连通版深度优先遍历
    private Queue<Integer> depthFirstSearch(int peak,Queue<Integer> help,boolean[] unVirgin) {
        while (!queues[peak].isEmpty()) {
            Integer adj = queues[peak].dequeue();
            if (unVirgin[adj]) {
                continue;
            }
            help.enqueue(adj);
            unVirgin[adj] = true;
            depthFirstSearch(adj,help,unVirgin);
        }
        return help;
    }

    // 非连通嵌套连通广度优先遍历,做到通用遍历
    @Override
    public Queue<Integer> breadthFirstSearch(int peak) {
        Queue<Integer> expr = new QueueArray<>();
        Queue<Integer> help = new QueueArray<>();
        boolean[] unVirgin = new boolean[peakSize];
        help.enqueue(peak);
        unVirgin[peak] = true;
        breadthFirstSearch(expr,help,unVirgin);
        for (int i = 0; i < queues.length; i++) {
            if (unVirgin[i]) {
                continue;
            }
            help.enqueue(i);
            unVirgin[i] = true;
            breadthFirstSearch(expr,help,unVirgin);
        }
        return expr;
    }

    // 单纯连通版广度优先遍历
    private Queue<Integer> breadthFirstSearch(Queue<Integer> expr,Queue<Integer> help,boolean[] unVirgin) {
        if (help.isEmpty()) {
            return expr;
        }
        int tar = help.dequeue();
        expr.enqueue(tar);
        while (!queues[tar].isEmpty()) {
            int inTar = queues[tar].dequeue();
            if (unVirgin[inTar]) {
                continue;
            }
            help.enqueue(inTar);
            unVirgin[inTar] = true;
        }
        return breadthFirstSearch(expr,help,unVirgin);
    }

    // 记录多少个点于指定节点相连通
    public int open(int peak) {
        boolean[] unVirgin = new boolean[queues.length];
        int count = 0;
        unVirgin[peak] = true;
        return open(peak,unVirgin,count);
    }

    public int open(int peak,boolean[] unVirgin,int count) {
        while (!queues[peak].isEmpty()) {
            int inTar = queues[peak].dequeue();
            if (unVirgin[inTar]) {
                continue;
            }
            unVirgin[inTar] = true;
            count = open(inTar,unVirgin,++count);
        }
        return count;
    }

    
    // 下面的代码是想说明,原本上文的遍历会破坏图数据,很容易想到用一个代理对象去做遍历操作,实际下文这样是不行的,改变的还是原图中底层的队列内的数据

    //    @Override
    //    public Queue<Integer> depthFirstSearch(int peak) {
    //        Queue<Integer>[] proxy = queues;
    //        Queue<Integer> help = new QueueArray<>();
    //        boolean[] unVirgin = new boolean[peakSize];
    //        help.enqueue(peak);
    //        unVirgin[peak] = true;
    //        depthFirstSearch(proxy,peak,help,unVirgin);
    //        for (int i = 0; i < proxy.length; i++) {
    //            if (unVirgin[i]) {
    //                continue;
    //            }
    //            help.enqueue(i);
    //            unVirgin[i] = true;
    //            depthFirstSearch(proxy,i,help,unVirgin);
    //        }
    //        return help;
    //    }
    //
    //    private Queue<Integer> depthFirstSearch(Queue<Integer>[] proxy,int peak,Queue<Integer> help,boolean[] unVirgin) {
    //        while (!proxy[peak].isEmpty()) {
    //            Integer adj = proxy[peak].dequeue();
    //            if (unVirgin[adj]) {
    //                continue;
    //            }
    //            help.enqueue(adj);
    //            unVirgin[adj] = true;
    //            depthFirstSearch(proxy,adj,help,unVirgin);
    //        }
    //        return help;
    //    }
    //
    //    @Override
    //    public Queue<Integer> breadthFirstSearch(int peak) {
    //        Queue<Integer>[] proxy = queues;
    //        Queue<Integer> expr = new QueueArray<>();
    //        Queue<Integer> help = new QueueArray<>();
    //        boolean[] unVirgin = new boolean[peakSize];
    //        help.enqueue(peak);
    //        unVirgin[peak] = true;
    //        breadthFirstSearch(proxy,expr,help,unVirgin);
    //        for (int i = 0; i < proxy.length; i++) {
    //            if (unVirgin[i]) {
    //                continue;
    //            }
    //            help.enqueue(i);
    //            unVirgin[i] = true;
    //            breadthFirstSearch(proxy,expr,help,unVirgin);
    //        }
    //        return expr;
    //    }
    //
    //    private Queue<Integer> breadthFirstSearch(Queue<Integer>[] proxy,Queue<Integer> expr,Queue<Integer> help,boolean[] 				unVirgin) {
    //        if (help.isEmpty()) {
    //            return expr;
    //        }
    //        int tar = help.dequeue();
    //        expr.enqueue(tar);
    //        while (!proxy[tar].isEmpty()) {
    //            int inTar = proxy[tar].dequeue();
    //            if (unVirgin[inTar]) {
    //                continue;
    //            }
    //            help.enqueue(inTar);
    //            unVirgin[inTar] = true;
    //        }
    //        return breadthFirstSearch(proxy,expr,help,unVirgin);
    //    }
}
```

