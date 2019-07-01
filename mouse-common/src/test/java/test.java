import java.util.ArrayList;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: test
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/6/17 16:20
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/6/17        shilun           v1.0.0               创建
 */
public class test {

    private String name;


    public static void main(String[] args) {

//        for (int i = 0; i < 3; i++) {
//            System.out.println(i);
//            for (int j = 0; j < 10; j++) {
//                if(j==5){
//                    return;
//                }
//                System.out.println(j);
//            }
//        }
        test t = null;
        t.setName(t.getName());

        System.out.println(!false);


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
