package com.zl.class002;

import static com.zl.class002.InsertSort.insertSort;

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
