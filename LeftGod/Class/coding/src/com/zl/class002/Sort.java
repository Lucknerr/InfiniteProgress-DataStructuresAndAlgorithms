package com.zl.class002;

// 慢排序汇总
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
