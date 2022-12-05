package com.zl.ropepindot;

// 绳子最多压中几个点 滑动窗口
public class RopePinDot {

    /**
     * 贪心
     * @param arr 有序数组
     * @param k 绳子长度
     * @return 最多压中的点数
     */
    public int pinDot(int[] arr, int k) {
        return f(arr,k,0,0);
    }

    /**
     * 滑动窗口 l,r不回退
     * @param arr 有序数组
     * @param k 绳子长度
     * @param l 左指针
     * @param r 有指针
     * @return 在r不能在往下滑的情况下,返回压中的最多点数
     */
    public int f(int[] arr,int k,int l, int r) {
        // 当r越界
        if (r == arr.length) {
            // 直接返回压中的点数
            return r - l;
        }
        // 当r l 距离超过绳子的长度,说明当前r的前一个点就已经不能再往下滑了
        if (arr[r] - arr[l] > k) {
            // 获取r在前一个点的时刻压中的点数和递归调用获取剩下没有涉及到的点单即将要压中的最大点数两者取最大值
            return Math.max(r - l,f(arr,k,++l,r)); // 由于r是滑过度了所以这里r不用再滑
        }
        // 当r不能在向右滑(这里相当于直接等于,说明肯定能不滑倒下一个点)
        if (arr[r] - arr[l] == k) {
            // 获取当前压中的点数和递归调用获取剩下没有涉及到的点单即将要压中的最大点数两者取最大值
            return Math.max(r - l + 1,f(arr,k,++l,++r));
        }
        return f(arr,k,l,++r);
    }

    /**
     * 对比方法
     * @param arr 有序数组
     * @param L 绳子长度
     * @return 最多压中的点数
     */
    public int maxPoint2(int[] arr, int L) {
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }

    /**
     * 贪心
     * @param arr 有序数组
     * @param k 绳子长度
     * @return 最多压中的点数
     */
    public int pinDot1(int[] arr, int k) {
        return f1(arr,k,0);
    }

    /**
     * 贪心
     * @param arr 有序数组
     * @param k 绳子长度
     * @param r 当前绳子最右端
     * @return 当前绳子所处位置压中最大点数
     */
    public int f1(int[] arr,int k,int r) {
        // 当绳子右端越界
        if (r == arr.length) {
            // 返回r在前一个点的时刻压中的点数
            return r - rightDot(arr,0,r-1,arr[r-1] - k);
        }
        // 当绳子最右端还在界外
        if (arr[r]-k <= 0) {
            // 返回绳子当前压中的点数与将来要压中的最大点数之间取最大值
            return Math.max(r + 1,f1(arr,k,++r));
        }
        // 绳子左端在x轴上的数
        int tmp = arr[r] - k;
        // 当前压中点数与将来要压中的最大点数之间取最大值
        return Math.max(r - rightDot(arr,0,r,tmp),f1(arr,k,++r));
    }

    /**
     * 二分查找
     * @param arr 有序数组
     * @param l 数组左边界
     * @param r 数组右边界
     * @param tmp 绳子最左所在x轴上的数
     * @return 大于绳子最左端的点有多少个
     */
    public int rightDot(int[] arr,int l, int r,int tmp) {
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (tmp >= arr[m]) {
                l = m + 1;
            } else if (tmp < arr[m]) {
                r = m - 1;
            }
        }
        return l;
    }
}
