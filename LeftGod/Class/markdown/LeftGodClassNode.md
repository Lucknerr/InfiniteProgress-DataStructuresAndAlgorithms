## Class002 : 排序、二分法

## 排序

###### 排序顺序：小 -> 大

### 选择排序

###### 作用：

1. 可以对线性的数据结构进行权值排序

###### 核心思想：

1. 从下标 0 ~ N-1 依次做为起始位置
2. 从起始位置开始遍历，找当前起始位置到N-1位置范围内最小值，并将其与每个位置的起始位置做交换

```java
/**
 * 选择排序
 */
public class SelectionSort {
    /**
     * 选择排序
     * @param nums 被排序的数组
     */
    public static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                min = nums[j] < nums[min] ? j : min;
            }
            if (i != min) {
                swap(nums, i, min);
            }
        }
    }

    /**
     * 数组中两指定位置元素交换
     * @param nums 被处理数组
     * @param i 指定位置下标
     * @param j 指定位置下标
     */
    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
```



### 冒泡排序

###### 作用：

1. 可以对线性的数据结构进行权值排序

###### 核心思想：

1. 以下标 0 ~ N-1 依次做为结尾
2. 从0位置开始遍历，每次遍历到当前位置都与下一个位置做比较，如果前面比后面大则交换，如果小就到下一个位置继续同样的操作，自然就形成了有传递性的将最大值传到每一轮的结尾

```java
/**
 * 冒泡排序
 */
public class BubbleSort {
    /**
     * 冒泡排序
     * @param nums 被排序的数组
     */
    public static void bubbleSort(int[] nums) {
        for (int i = nums.length-1;i >= 0;i--) {
            for (int j = 0;j < i;j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums,j,j+1);
                }
            }
        }
    }

    /**
     * 数组中两指定位置元素交换
     * @param nums 被处理数组
     * @param i 指定位置下标
     * @param j 指定位置下标
     */
    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
```



### 插入排序

###### 作用：

1. 可以对线性的数据结构进行权值排序

###### 核心思想：

1. 以下标 0 ~ N-1 依次做为结尾
2. 从每一轮的结尾开始到0，每次遍历到当前位置都与前一个位置做比较，如果前面比后面大交换，如果小就去到前一个位置继续同样的操作，自然就形成了有传递性的将最小值向0位置传递，最大值向每一轮的结尾传递

```java
/**
 * 插入排序
 */
public class InsertSort {
    /**
     * 插入排序
     * @param nums 被排序的数组
     */
    public static void insertSort(int[] nums) {
        for (int i = 1;i < nums.length;i++) {
            for (int j = i;j > 0;j--) {
                if (nums[j] < nums[j-1]) {
                    swap(nums,j,j-1);
                }
            }
        }
    }

    /**
     * 数组中两指定位置元素交换
     * @param nums 被处理数组
     * @param i 指定位置下标
     * @param j 指定位置下标
     */
    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
```



### 慢排序汇总

```java
/**
 * 慢排序汇总
 */
public class Sort {

    /**
     * 选择排序
     * @param nums 被排序的数组
     */
    public static void selectSort(int[] nums) {
        for (int i = 0;i < nums.length;i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                min = nums[j] < nums[min] ? j : min;
            }
            if (i != min) {
                swap(nums, i, min);
            }
        }
    }

    /**
     * 插入排序
     * @param nums 被排序的数组
     */
    public static void insertSort(int[] nums) {
        for (int i = 1;i < nums.length;i++) {
            for (int j = i;j > 0;j--) {
                if (nums[j] < nums[j-1]) {
                    swap(nums,j,j-1);
                }
            }
        }
    }

    /**
     * 冒泡排序
     * @param nums 被排序的数组
     */
    public static void bubbleSort(int[] nums) {
        for (int i = nums.length-1;i >= 0;i--) {
            for (int j = 0;j < i;j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums,j,j+1);
                }
            }
        }
    }

    /**
     * 数组中两指定位置元素交换
     * @param nums 被处理数组
     * @param i 指定位置下标
     * @param j 指定位置下标
     */
    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    /**
     * 对数器
     */
    public static void verifier() {
        int testTime = 1000;
        for (int i = 0;i < testTime;i++) {
            int N = (int) (Math.random() * 10000);
            int range = 200;
            int[] nums1 = randomNums(N, range);
            int[] copy1 = copyNums(nums1, N);
            int[] copy2 = copyNums(nums1, N);
            selectSort(nums1);
            insertSort(copy1);
            bubbleSort(copy2);
            boolean n1 = compareNums(nums1, copy1, N);
            boolean n2 = compareNums(copy2, copy1, N);
            if (!(n1 && n2)) {
                System.out.println(toString(nums1));
                System.out.println(toString(copy1));
                System.out.println(toString(copy2));
                System.out.println("!.!");
                return;
            }
        }
        System.out.println("^.^");
    }

    /**
     * 拷贝一个新数组
     * @param nums 被拷贝的数组
     * @param N 数组长度
     * @return 拷贝出的新数组
     */
    public static int[] copyNums(int[] nums, int N) {
        int[] newNums = new int[N];
        for (int i = 0;i < N;i++) {
            newNums[i] = nums[i];
        }
        return newNums;
    }

    /**
     * 生成随机数组
     * @param N 随机数组的长度
     * @param range 随机数组中的随机元素的随机值范围
     * @return 返回随机后的数组
     */
    public static int[] randomNums(int N, int range) {
        int[] nums = new int[N];
        for (int i = 0;i < N;i++) {
            nums[i] = (int) (Math.random() * (range+1)) - (int) (Math.random() * range);
        }
        return nums;
    }

    /**
     * 比较两个数组是否相同
     * @param nums1 数组1
     * @param nums2 数组2
     * @param N 数组长度
     * @return 相同返回true,不相同返回false;
     */
    public static boolean compareNums(int[] nums1, int[] nums2, int N) {
        for (int i = 0;i < N;i++) {
            if (nums1[i] != nums2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 呈现数组
     * @param nums 被呈现的数组
     * @return 数组内的元素拼接返回字符串
     */
    public static String toString(int[] nums) {
        String tmp = "";
        for (int n : nums) {
            tmp += n + "\t";
        }
        return tmp;
    }

    public static void main(String[] args) {
        verifier();
    }
}
```



### 二分法

###### 前提条件：有序列表

###### 作用：

1. 快速从列表中找到指定下标的元素

###### 核心思想：

1. 找中点，中点位置值与目标值比较，较大则去中点左侧范围继续找中点二分，较小则去中点右侧范围继续找中点二分

```java
/** * 二分法
 */
public class BinarySearch {

    /**
     * 二分查找
     * 在一个有序数组中，找某个数是否存在
     * @param nums 数组
     * @param target 目标数
     * @return 是否能在数组中找到目标数,找到返回true,找不到返回false
     */
    public static boolean binarySearch(int[] nums, int target) {
        // 保证数组有序
        insertSort(nums);
        // 左指针
        int l = 0;
        // 右指针
        int r = nums.length-1;
        // 开始二分
        while (l <= r) { // 右指针到左指针左边去了停止二分
            int m = l + ((r - l) >> 1); // 找当前范围中点位置
            if (nums[m] > target) { // 如果中点位置大于目标值
                r = m - 1; // 去中点左边继续二分
            } else if (nums[m] < target) { // 如果中点位置小于目标值
                l = m + 1; // 去中点右边继续二分
            } else { // 如果中点位置等于目标值
                return true; // 说明找到了目标值
            }
        }
        return false;
    }

    /**
     * 暴力
     * 在一个有序数组中，找某个数是否存在
     * @param nums 数组
     * @param target 目标数
     * @return 找到返回true,找不到返回false
     */
    public static boolean binarySearch1(int[] nums, int target) {
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * 二分查找
     * 在一个有序数组中，找>=某个数最左侧的位置
     * @param nums 数组
     * @param target 目标数
     * @return 返回下标
     */
    public static int searchTargetRightTheLeft(int[] nums, int target) {
        insertSort(nums);
        int l = 0;
        int r = nums.length-1;
        int ans = -1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] >= target) { // 如果中点位置大于等于目标值
                ans = m; // 将中点位置值暂存ans中
                r = m - 1; // 去中点左侧继续二分
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    /**
     * 暴力
     * 在一个有序数组中，找>=某个数最左侧的位置
     * @param nums 数组
     * @param target 目标数
     * @return 返回下标,没有则返回 -1
     */
    public static int searchTargetRightTheLeft1(int[] nums, int target) {
        insertSort(nums);
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 对数器
     * 在一个有序数组中，找某个数是否存在
     * @param testTime 测试次数
     * @param N 随机数组长度返回
     * @param range 数组内元素取值范围 正负range
     * @param target 目标值取值范围 正负range
     */
    public static void verifierBinarySearch(int testTime, int N, int range, int target) {
        for (int i = 0;i < testTime;i++) {
            int len = (int) (Math.random() * (N+1));
            int rge = (int) (Math.random() * (range+1));
            int tar = (int) (Math.random() * (target+1));
            int[] nums = randomNums(len,rge);
            int[] copy = copyNums(nums,len);
            boolean n1 = binarySearch(nums,tar);
            boolean n2 = binarySearch1(copy,tar);
            if (n1 != n2) {
                System.out.println(tar);
                System.out.println(toString(nums));
                System.out.println(n1);
                System.out.println(toString(copy));
                System.out.println(n2);
                System.out.println("!.!");
                return;
            }
        }
        System.out.println("^.^");
    }

    /**
     * 对数器
     * 在一个有序数组中，找>=某个数最左侧的位置
     * @param testTime 测试次数
     * @param N 随机数组长度返回
     * @param range 数组内元素取值范围 正负range
     */
    public static void verifierSearchTargetRightTheLeft(int testTime, int N, int range) {
        for (int i = 0;i < testTime;i++) {
            int len = (int) (Math.random() * (N+1) + 1);
            int rge = (int) (Math.random() * (range+1));
            int[] nums = randomNums(len,rge);
            int tar = nums[0];
            int[] copy = copyNums(nums,len);
            int n1 = searchTargetRightTheLeft(nums,tar);
            int n2 = searchTargetRightTheLeft1(copy,tar);
            if (n1 != n2) {
                System.out.println(tar);
                System.out.println(toString(nums));
                System.out.println(n1);
                System.out.println(toString(copy));
                System.out.println(n2);
                System.out.println("!.!");
                return;
            }
//            System.out.println(tar);
//            System.out.println(toString(nums));
//            System.out.println(n1);
//            System.out.println(toString(copy));
//            System.out.println(n2);
        }
        System.out.println("^.^");
    }

    /**
     * 拷贝一个新数组
     * @param nums 被拷贝的数组
     * @param N 数组长度
     * @return 拷贝出的新数组
     */
    public static int[] copyNums(int[] nums, int N) {
        int[] newNums = new int[N];
        for (int i = 0;i < N;i++) {
            newNums[i] = nums[i];
        }
        return newNums;
    }

    /**
     * 生成随机数组
     * @param N 随机数组的长度
     * @param range 随机数组中的随机元素的随机值范围
     * @return 返回随机后的数组
     */
    public static int[] randomNums(int N, int range) {
        int[] nums = new int[N];
        for (int i = 0;i < N;i++) {
            nums[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
        }
        return nums;
    }

    /**
     * 呈现数组
     * @param nums 被呈现的数组
     * @return 数组内的元素拼接返回字符串
     */
    public static String toString(int[] nums) {
        String tmp = "";
        for (int n : nums) {
            tmp += n + "\t";
        }
        return tmp;
    }

    public static void main(String[] args) {
        verifierBinarySearch(10000,10000,1000,1000);
        verifierSearchTargetRightTheLeft(10000,10000,1000);
    }
}
```

