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

##### Edge类

```java
// 加权边实现
public class Edge implements Comparable<Edge>{
    // 顶点A
    private final int A;
    // 顶点B
    private final int B;
    // 标记边是否被访问过
    private boolean visit;
    // 权重值
    private double WEIGHT;
    // 构造方法
    public Edge(int A,int B,double WEIGHT) {
        this.A = A;
        this.B = B;
        this.visit = false;
        this.WEIGHT = WEIGHT;
    }

    // 获取A点
    public int getA() {
        return A;
    }

    // 获取B点
    public int getB() {
        return B;
    }

    // 获取指定点的另一个点
    public int getOther(int peak) {
        return peak == A ? B : A;
    }

    // 获取权重值
    public double getWeight() {
        return WEIGHT;
    }

    // 标记为被访问过
    public void setVisit() {
        this.visit = true;
    }

    // 查看边是否被标记为访问过
    public boolean getVisit() {
        return visit;
    }

    // 与指定边比较权重值
    @Override
    public int compareTo(Edge o) {
        if (this.WEIGHT > o.WEIGHT) {
            return 1;
        } else if (this.WEIGHT < o.WEIGHT) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return A + "-" + B;
    }
}
```

##### WeightedUndirectedGraphs

```java
// 加权无向图实现
public class WeightedUndirectedGraphs {
    // 记录顶点数
    private int peakSize;
    // 记录边数
    private int sideSize;
    // 邻接表,存储加权边
    private Queue<Edge>[] queues;
    // 构造方法
    public WeightedUndirectedGraphs(int peakSize) {
        // 初始化顶点数
        this.peakSize = peakSize;
        // 初始化边数
        this.sideSize = 0;
        // 初始化邻接表
        this.queues = new Queue[peakSize];
        for (int i = 0; i < peakSize; i++) {
            // 初始化每个队列
            queues[i] = new QueueArray<>();
        }
    }
    
    // 获取顶点数
    public int getPeakSize() {
        return peakSize;
    }

    // 获取边数
    public int getSideSize() {
        return sideSize;
    }

    // 向图中添加一条边
    public boolean addSide(Edge edge) {
        // 获取edge其中的一个顶点
        int A = edge.getA();
        // 获取edge其中的另一个顶点
        int B = edge.getB();
        // 将B添加到A的邻接表中
        queues[A].enqueue(edge);
        queues[B].enqueue(edge);
        sideSize++;
        return true;
    }

    // 获取图中所有加权边基于深度优先遍历
    public Queue<Edge> getAllEdgeForDFS() {
        return DFS(0);
    }
    // 获取顶点A相关联的边
    public Queue<Edge> getEdgeForA(int peak) {
        return queues[peak];
    }
    
    // 深度优先遍历入口方法
    public Queue<Edge> DFS(int peak) {
        // 用于存放遍历后的加权边
        Queue<Edge> ans = new QueueArray<>();
        // 进行深度优先遍历
        DFS(peak,ans);
        // 遍历剩下的边,如果存在非连通图的情况下
        for (int i = 0; i < peakSize; i++) {
            DFS(i,ans);
        }
        // 返回遍历完成的加权边存放队列
        return ans;
    }

    // 深度优先遍历核心方法
    private Queue<Edge> DFS(int peak,Queue<Edge> ans) {
        // 遍历当前顶点相邻的所有加权边
        while (!queues[peak].isEmpty()) {
            // 拿出当前顶点最先出来的加权边
            Edge edge = queues[peak].dequeue();
            // 获取这个加权边对应的另一个顶点
            int other = edge.getOther(peak);
            // 判断此边是否之前被访问过
            if (edge.getVisit()) {
                // 若被访问过直接跳过
                continue;
            }
            // 将此边放入结果队列
            ans.enqueue(edge);
            // 将此边标记为被访问过
            edge.setVisit();
            // 递归调用深度优先遍历,以同样的方式遍历当前边的另一个顶点相邻的边
            DFS(other,ans);
        }
        // 返回结果表
        return ans;
    }
}
```

