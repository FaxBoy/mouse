package leetcode;

import java.util.HashMap;
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
        System.out.println(numDupDigitsAtMostN(8646800));
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
                    System.out.println(i);
                    l++;
                    break;
                }else {
                    map.put(Integer.valueOf(str.substring(j,j+1)),1);
                }
            }

        }
        return l;
    }

}
