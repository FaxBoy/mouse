package designPattern;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName Single 单例模式
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public class Single {

    private static Single single;

    static {
        single = new Single();
    }

    private Single(){}

    private static Single getInstance(){
        return single;
    }

    public static void main(String args[]){
        Single s1=Single.getInstance();
        Single s2=Single.getInstance();
        if(s1==s2)
            System.out.println("此类是单例模式");
        else
            System.out.println("此类不是单例模式");
    }

}
