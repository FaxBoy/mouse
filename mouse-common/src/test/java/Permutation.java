/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName Permutation
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public class Permutation {

    private int max;
    private int[] array;
    private int[] hold;

    private static int arr[] = new int[]{7,5,2,9,71};

    public Permutation(int max) {
        this.max = max;
        array = new int[max + 1];
        hold = new int[max + 1];
    }

    public void permute(int step) {
        if (step == max + 1) {
            for (int i = 1; i <= max; i++) {
                System.out.print(arr[array[i]-1] + " ");
            }
            System.out.println();
            return;  //返回上一步, 即最近一次调用permute方法的后一行
        }
        //按照1->2->3->...->n的顺序尝试
        for (int num = 1; num <= max; num++) {
            //判断是否还持有该数字
            if (hold[num] == 0) {
                array[step] = num;
                hold[num] = 1;
                //递归: 右移一格重复遍历数字的尝试
                permute(step + 1);
                //回到当前位置时取回当前位置数字
                hold[num] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Permutation fa = new Permutation(5);
        fa.permute(1);
    }
}
