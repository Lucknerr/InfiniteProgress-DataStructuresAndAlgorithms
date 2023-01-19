## Class002 : 认识复杂度、对数器、二分法

###### 排序顺序：小 -> 大

### 选择排序

###### 作用：

1. 可以对线性的数据结构进行权值排序

###### 核心思想：

1. 从下标 0 ~ N-1 依次做为起始位置
2. 从起始位置开始遍历，找当前起始位置到N-1位置范围内最小值，并将其与每个位置的起始位置做交换

```java
public class SelectionSort {
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
public class BubbleSort {
    public static void bubbleSort(int[] nums) {
        for (int i = nums.length-1;i >= 0;i--) {
            for (int j = 0;j < i;j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums,j,j+1);
                }
            }
        }
    }

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
public class InsertSort {
    public static void insertSort(int[] nums) {
        for (int i = 1;i < nums.length;i++) {
            for (int j = i;j > 0;j--) {
                if (nums[j] < nums[j-1]) {
                    swap(nums,j,j-1);
                }
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
```



### 慢排序汇总

```java
public class Sort {

    // 选择排序
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

    // 插入排序
    public static void insertSort(int[] nums) {
        for (int i = 1;i < nums.length;i++) {
            for (int j = i;j > 0;j--) {
                if (nums[j] < nums[j-1]) {
                    swap(nums,j,j-1);
                }
            }
        }
    }

    // 冒泡排序
    public static void bubbleSort(int[] nums) {
        for (int i = nums.length-1;i >= 0;i--) {
            for (int j = 0;j < i;j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums,j,j+1);
                }
            }
        }
    }

    // 元素交换
    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    // 对数器
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
//                System.out.println(toString(nums1));
//                System.out.println(toString(copy1));
//                System.out.println(toString(copy2));
                System.out.println("!.!");
                return;
            }
        }
        System.out.println("^.^");
    }

    // 拷贝数组
    public static int[] copyNums(int[] nums, int N) {
        int[] newNums = new int[N];
        for (int i = 0;i < N;i++) {
            newNums[i] = nums[i];
        }
        return newNums;
    }

    // 生成随机数组
    public static int[] randomNums(int N, int range) {
        int[] nums = new int[N];
        for (int i = 0;i < N;i++) {
            nums[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
        }
        return nums;
    }
    
    // 比较两个数组是否相同
    public static boolean compareNums(int[] nums1, int[] nums2, int N) {
        for (int i = 0;i < N;i++) {
            if (nums1[i] != nums2[i]) {
                return false;
            }
        }
        return true;
    }
    
    // 呈现数组
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

