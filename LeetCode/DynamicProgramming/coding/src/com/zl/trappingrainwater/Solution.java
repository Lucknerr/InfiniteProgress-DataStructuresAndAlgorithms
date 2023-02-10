package com.zl.trappingrainwater;

import java.util.*;

public class Solution {
//    public static int findMinStep(String board, String hand) {
//        char[] s = board.toCharArray(), h = hand.toCharArray();
//        int n = s.length, m = h.length, ans = f(s,h,n,m,(1 << n) - 1,(1 << m) - 1,0);
//        return ans == Integer.MAX_VALUE ? -1 : ans;
//    }
//
//    public static int f(char[] board, char[] hand, int n, int m, int sta, int state, int i) {
//        if (sta == 0) return 0;
//        if (state == 0 || i > n) return Integer.MAX_VALUE;
//        int min = f(board,hand,n,m,sta,state,i+1);
//        for (int j = 0;j < m;j++) {
//            if ((state & (1 << j)) != 0) {
//                int tmp = vanish(board,sta,i,hand[j]);
//                if (tmp != -1) {
//                    int p = f(board,hand,n,m,tmp,state ^ (1 << j),i);
//                    p = p == Integer.MAX_VALUE ? Integer.MAX_VALUE : p+1;
//                    min = Math.min(min,p);
//                }
//            }
//        }
//        return min;
//    }
//
//    public static int vanish(char[]board, int state, int i, char c) {
//        int l = 0, r = 0, n = 0, s = 0, sa = state, len = board.length;
//        while (r <= len) {
//            if ((state & (1 << s)) == 0) {
//                s++; l++; r++;
//            } else {
//                if (r != len && (state & (1 << r)) == 0) {
//                    n++; r++;
//                } else {
//                    if (r == len || board[l] != board[r]) {
//                        int t = r - l - n;
//                        int tmp = i >= l && i <= r && board[l] == c && state == sa ? 1 : 2;
//                        if (t > tmp) {
//                            while (t > 0) {
//                                if ((state & (1 << l)) == 0) l += n;
//                                state ^= 1 << l++; t--;
//                            }
//                            l = s; r = s; n = 0;
//                        } else if (r == len) {
//                            break;
//                        } else l = r;
//                    } else r++;
//                }
//            }
//        }
//        if (state < sa) return state;
//        return -1;
//    }

    static Map<String,Integer> dp = new HashMap<>();
    static String hands;
    static int m;
    public static int findMinStep(String board, String hand) {
        hands = hand;
        m = hand.length();
        int ans = f(new StringBuilder(board),(1 << m) - 1);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int f(StringBuilder board, int state) {
        System.out.println(board + "" + state);
        int n = board.length();
        if (n == 0) return 0;
        if (state == 0) return Integer.MAX_VALUE;
        String key = board + "" + state;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0;i <= n;i++) {
            for (int j = 0;j < m;j++) {
                if ((state & (1 << j)) != 0 && !((i > 0 && i < n && board.charAt(i) == board.charAt(i - 1) && board.charAt(i - 1) != hands.charAt(j)) || (i < n && board.charAt(i) == hands.charAt(j)))) {
                    int p = f(vanish(new StringBuilder(board).insert(i, hands.charAt(j))),state ^ (1 << j));
                    p = p == Integer.MAX_VALUE ? Integer.MAX_VALUE : p+1;
                    min = Math.min(min,p);
                }
            }
        }

        dp.put(key,min);
        return min;
    }

    public static StringBuilder vanish(StringBuilder board) {
        int len = board.length();
        for (int l = 0, r = 0;r <= len;r++) {
            if(r == len || board.charAt(l) != board.charAt(r)) {
                if (r - l > 2) {
                    return vanish(board.delete(l,r));
                }
                l = r;
            }
        }
        return board;
    }

//    int INF = 0x3f3f3f3f;
//    String b;
//    int m;
//    Map<String, Integer> map = new HashMap<>();
//    public int findMinStep(String a, String _b) {
//        b = _b;
//        m = b.length();
//        int ans = dfs(a, 1 << m);
//        return ans == INF ? -1 : ans;
//    }
//    int dfs(String a, int cur) {
//        if (a.length() == 0) return 0;
//        String hashKey = a + "_" + cur;
//        if (map.containsKey(hashKey)) return map.get(hashKey);
//        int ans = INF;
//        int n = a.length();
//        for (int i = 0; i < m; i++) {
//            if (((cur >> i) & 1) == 1) continue;
//            int next = (1 << i) | cur;
//            for (int j = 0; j <= n; j++) {
//                boolean ok = false;
//                if (j > 0 && j < n && a.charAt(j) == a.charAt(j - 1) && a.charAt(j - 1) != b.charAt(i)) ok = true;
//                if (j < n && a.charAt(j) == b.charAt(i)) ok = true;
//                if (!ok) continue;
//                StringBuilder sb = new StringBuilder();
//                sb.append(a.substring(0, j)).append(b.substring(i, i + 1));
//                if (j != n) sb.append(a.substring(j));
//                int k = j;
//                while (0 <= k && k < sb.length()) {
//                    char c = sb.charAt(k);
//                    int l = k, r = k;
//                    while (l >= 0 && sb.charAt(l) == c) l--;
//                    while (r < sb.length() && sb.charAt(r) == c) r++;
//                    if (r - l - 1 >= 3) {
//                        sb.delete(l + 1, r);
//                        k = l >= 0 ? l : r;
//                    } else {
//                        break;
//                    }
//                }
//                ans = Math.min(ans, dfs(sb.toString(), next) + 1);
//            }
//        }
//        map.put(hashKey, ans);
//        return ans;
//    }
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        String s1 = "RRGGBBYYWWRRGGBB";
        String s2 = "RGBYW";
        long d1 = new Date().getTime();
        System.out.println(findMinStep(s1,s2));
        long d2 = new Date().getTime();
        System.out.println(d2-d1);
    }
}
