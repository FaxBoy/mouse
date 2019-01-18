/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName maxs
 * @Description 求一组数据连续上涨最大次数
 * @date 2018/9/14 11:02
 */
public class maxs {
    public static void main(String[] args) {

        int a[] = { 10, 23, 4, 3, 4, 6, 8, 46, 47, 77, 88,3,4,2,4,5,6,7 };
        System.out.println(Max2(a));

    }

    public static int Max2(int a[]) {
        int CurSum = 0;
        int MaxNum = 0;
        for (int i = 0; i < a.length; i++) {
            if(i==a.length-1)
                return MaxNum;
            if(a[i+1]-a[i]>0){
                CurSum ++;
                if(CurSum>MaxNum)
                    MaxNum=CurSum;
            }else
                CurSum = 0;
        }
        return MaxNum;
    }

}
