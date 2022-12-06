package com.zl.pasterspell;

// 贴纸拼词 暴力递归->动态规划
public class PasterSpell {

    /**
     * 暴力递归
     * 最少用多少张贴纸
     * @param tags 可选的贴纸
     * @param target 目标单词
     * @return 最少用多少张贴纸
     */
    public int pasterSpell(String[] tags,String target) {
        int min = pasterSpells(tags,target);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     *
     * @param tags 可选的贴纸
     * @param res 剩余字符
     * @return
     */
    public int pasterSpells(String[] tags,String res) {
        if (res == null || res.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String tag : tags) {
            String tmp = countChar(tag,res);
            if (tmp.length() != res.length()) {
                min = Math.min(min,pasterSpells(tags,tmp));
            }
        }
        return min == Integer.MAX_VALUE ? Integer.MAX_VALUE : ++min;
    }

    /**
     * 抵消字符
     * @param tag 贴纸
     * @param res 剩余字符
     * @return 剩余字符被抵消后剩的字符
     */
    public String countChar(String tag,String res){
        char[] tags = tag.toCharArray();
        char[] resS = res.toCharArray();
        int[] tmp = new int[26];
        for (char r : resS) {
            tmp[r - 'a']++;
        }
        for (char t : tags) {
            tmp[t - 'a']--;
        }
        res = "";
        for (int t : tmp) {
            res += t > 0 ? (char) (t + 'a') : "";
        }
        return res;
    }


    /**
     * 贪心
     * 最少用多少张贴纸
     * @param tags 可选的贴纸
     * @param target 目标单词
     * @return 最少用多少张贴纸
     */
    public int pasterSpell1(String[] tags,String target) {
        int min = pasterSpells(tags,target);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
