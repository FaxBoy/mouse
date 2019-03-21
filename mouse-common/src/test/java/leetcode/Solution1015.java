package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName Solution1015
 * @Description TODO
 * @date $ $
 */
public class Solution1015 {

    public static void main(String[] args) {
        System.out.println(numDupDigitsAtMostN(99));
    }

    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w: weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2, need = 1, cur = 0;
            for (int w: weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (need > D) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static int numDupDigitsAtMostN(int N) {
        if(N<10){
            return 0;
        }
        int l =0;
        for (int i = 11; i <= N; i++) {
            Map<Integer,Integer> map = new HashMap<>();
            String str = String.valueOf(i);
            for (int j = 0; j < str.length(); j++) {
                if (map.containsKey(Integer.valueOf(str.substring(j,j+1)))){
                    //System.out.println(i);
                    l++;
                    break;
                }else {
                    map.put(Integer.valueOf(str.substring(j,j+1)),1);
                }
            }

        }
        return l;
    }

    public static int numDupDigitsAtMostN2(int N) {
        ArrayList<Integer> L = new ArrayList<Integer>();
        for (int x = N + 1; x > 0; x /= 10)
            L.add(0, x % 10);

        // Count the number with digits < N
        int res = 0, n = L.size();
        for (int i = 1; i < n; ++i)
            res += 9 * A(9, i - 1);

        // Count the number with same prefix
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i > 0 ? 0 : 1; j < L.get(i); ++j)
                if (!seen.contains(j))
                    res += A(9 - i, n - i - 1);
            if (seen.contains(L.get(i))) break;
            seen.add(L.get(i));
        }
        return N - res;
    }

    public static int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }

}
