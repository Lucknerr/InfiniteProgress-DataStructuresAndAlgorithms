# AC自动机(AhoCorasick)

### 1.图示



### 2.过程

### 3.实现

```java
// AC自动机实现
// 本自动机只能找26小写字母
public class AhoCorasick {
    // 记录根节点
    private Node root;
    // 节点内部类
    private class Node {
        // 通过什么而来
        Character path;
        // 记录所有可能的子节点
        Node[] nextS;
        // 当匹配失败的时候,引导游标根据该指针找到其他路径上匹配成功的可能
        Node fail;
        // 接收完整单词,该节点endStr不为空,说明存在某个完整单词以该节点结尾
        String endStr;
        // 构造方法
        public Node(Character path) {
            // 初始化来路
            this.path = path;
            // 初始化所有子节点
            this.nextS = new Node[26];
            // 初始化fail指针
            this.fail = null;
            // 初始化
            this.endStr = null;
        }
    }

    // 构造方法
    public AhoCorasick() {
        // 初始化根节点
        this.root = new Node(null);
    }

    // 向前缀数中添加单词
    public boolean addWord(String word) {
        // 判断边界条件
        // 当要添加的单词为空时
        if (word == null || word.length() == 0) {
            // 没有要操作什么的必要
            return false;
        }
        // 获取字符串的长度
        int len = word.length();
        // 将字符串转为字符数组
        char[] chars = word.toCharArray();
        // 创建从root开始的游标
        Node cur = root;
        // 遍历单词所有字符
        for (int i = 0; i < len; i++) {
            // 找到字符应该去的节点
            int to = chars[i] - 'a';
            // 判断将要去的节点是否已经存有字符
            if (cur.nextS[to] == null) {
                // 如果没有将创建来路为当前字符的节点,添加到将要去的节点
                cur.nextS[to] = new Node(chars[i]);
            }
            // 游标来到目标节点
            cur = cur.nextS[to];
        }
        // 当所有字符都存完之后,现在游标的位置自然实在刚存的单词的最后一个,此时当前节点的endStr存放整个单词的字符串
        cur.endStr = word;
        // 添加完后构建AC自动机,也就是校准fail指针
        return buildAC();
    }

    // 在大文本中查找出现了哪些单词
    public List<Node> findWord(String largeText) {
        // 辅助数组
        List<Node> list = new ArrayList<>();
        // 大文本如果为空
        if (largeText == null) {
            // 不需要继续
            return null;
        }
        // 获取大文本长度
        int len = largeText.length();
        // 将大文本转换为字符数组
        char[] chars = largeText.toCharArray();
        // 创建游标,起始为根节点
        Node cur = root;
        // 创建遍历索引
        int i = 0;
        /// 遍历大文本
        while (i < len) {
            // 获取当前字符所处索引,索引去到下一个字符
            int to = chars[i] - 'a';
            // 如果当前游标的目标子节点为空
            if (cur.nextS[to] == null) {
                if (cur == root) {
                    i++;
                    continue;
                }
                // 这里游标去到fail
                cur = cur.fail;
            } else {
                i++;
                // 游标去到目标子节点
                cur = cur.nextS[to];
                // 游标单词探测
                detect(cur,list);
            }
        }
        // 索引遍历完成返回辅助数组
        return list;
    }

    // 构建AC自动机
    private boolean buildAC() {
        // 创建游标
        Node cur = root;
        // 创建辅助队列
        Deque<Node> help = new ArrayDeque<>();
        // 将当前游标的几点放入队列中
        help.add(cur);
        // 构建AC自动机
        buildAC(help);
        return true;
    }

    // 广度优先遍历前缀树的同时娇正fail指针
    private void buildAC(Queue<Node> help) {
        // 如果辅助队列为空,说明遍历完成
        if (help.isEmpty()) {
            return;
        }
        // 拿出最早进入队列的节点
        Node cur = help.poll();
        // 遍历该节点所有可能的子节点
        for (int i = 0; i < 26; i++) {
            // 拿到当前遍历到的子节点
            Node next = cur.nextS[i];
            // 如果当前遍历到的子节点为空
            if (next == null) {
                // 跳过
                continue;
            }
            // 如果游标所处位置的节点的fail指针指向的节点为空
            if (cur.fail == null) {
                // 说明当前游标所处位置还是处于根节点
                // 所有根节点的所有子节点的fail指针均指向根节点
                next.fail = root;
            } else {
                // 如果当前遍历到的子节点为空
                // 获取遍历到的字符将要去的节点索引
                int tmp = next.path - 'a';
                // 首先找到当前遍历到的子节点的fail指针,并将该指针指向所找
                // 以cur所在节点视角找fail指针
                next.fail = findFail(tmp,cur);
            }
            // 将已经构建完成的节点放入队列中
            help.add(next);
        }
        // 递归调用,继续构建队列中剩余的节点
        buildAC(help);
    }

    // 根据指定节点及来路确定子节点fail指针指向的位置
    private Node findFail(int tmp,Node cur) {
        // 如果当前游标所在节点的来路是空,说明游标所处根节点
        if (cur.path == null) {
            // 返回根节点
            return root;
        }
        // 如果来路不是空肯定不是根节点,获取到fail指针节点
        Node target = cur.fail;
        // fail指针节点的子节点如果存在与待指定fail指针的节点来路一样的字符
        if (target.nextS[tmp] != null) {
            // 返回这个子节点
            return target.nextS[tmp];
        } else {
            // 如果不存在
            // 以当前游标fail所在节点事件找fail
            return findFail(tmp,target);
        }
    }

    // 临时游标探测,探测来到的位置的endStr是否存在,也就是说来到的位置是否时单价结尾节点
    private void detect(Node tmp,List list) {
        // 判断临时游标所处位置是否在根节点
        if (tmp == root) {
            // 如果是根节点不用再探测了,因为tmp是根据当前所在节点的fail移动的根节点fail为null已经没有移动依据了
            return;
        }
        // 判断临时游标所处位置的endStr是否存有字符串
        if (tmp.endStr != null) {
            // 如果有,说明已经找到了一个单词,将找到的单词存入结果数组
            list.add(tmp.endStr);
        }
        // 临时游标去到到当前递归序下游标的fail所指节点
        detect(tmp.fail,list);
    }
}
```

