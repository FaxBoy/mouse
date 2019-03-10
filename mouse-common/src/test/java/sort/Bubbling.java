package sort;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName Bubbling 冒泡排序
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public class Bubbling {

    public static void main(String[] args) {
        int [] arr = new int[]{3,2,4,5,1,2,5,11,8,9};
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                //将数组中最大或最小的值放到数组最后一位
                if(arr[j]<arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

}
