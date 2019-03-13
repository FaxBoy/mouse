import java.util.HashMap;
import java.util.Map;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName TowSum
 * @Description TODO
 * 找出一个数组中两数之和大于指定值的下标组合
 * 如 {1,3,4,5,4}
 * 输入 6 输出 [1,2] [1,3] [1,4] [2,3] [2,4] [3,4]
 *
 * @date 2018/9/14 11:02
 */
public class TowSum {
    public static void main(String[] args) {
        int [] arr = new int[]{1,3,4,5,6,4,6,2};
        Map<Integer,Integer> map = new HashMap<>();
        int num = 8;
        for (int i = 0; i < arr.length; i++) {
            int n = num - arr[i];
            if (map.containsKey(n)){
                System.out.println("["+map.get(n)+","+i+"]");
            }else {
                map.put(arr[i],i);
            }
        }
//        int num = 8;
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i+1; j < arr.length; j++) {
//                int l = num -arr[i];
//                if (l<=arr[j]){
//                    System.out.println("["+i+","+j+"]");
//                }
//            }
//        }

    }
}
