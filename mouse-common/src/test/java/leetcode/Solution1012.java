package leetcode;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName Solution1012
 * @Description TODO
 * @date $ $
 */
public class Solution1012 {
    public static void main(String[] args) {
        StringBuilder binary = new StringBuilder();
        int n =0;
        if (0==n){
            binary.append(0);
        }
        while (n != 0) {
            binary.insert(0, n % 2);
            n /= 2;
        }
        StringBuilder binary2 = new StringBuilder();
        for (int i = 0; i < binary.toString().length(); i++) {
            if(binary.toString().substring(i,i+1).equals("0")){
                binary2.append(1);
            }else {
                binary2.append(0);
            }
        }

        System.out.println(Integer.parseInt(binary2.toString(),2));
    }
}
