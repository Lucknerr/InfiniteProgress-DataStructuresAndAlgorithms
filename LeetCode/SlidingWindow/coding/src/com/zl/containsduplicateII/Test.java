package com.zl.containsduplicateII;



public class Test {
    public static void main(String[] args) {
        ContainsDuplicateII containsDuplicateII = new ContainsDuplicateII();
        int[] nums = {1,2,3,1};
        int k = 3;
        System.out.println(containsDuplicateII.containsNearbyDuplicate(nums,k));
    }
}
