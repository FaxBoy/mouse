package design;

/**
 * Desc
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2019  tdh
 *
 * @ClassName: Singleton
 * @Description:
 * @version: v1.0.0
 * @author: shilun <sl166199@163.com>
 * @date: 2019/4/26 16:30
 * Modification History:
 * Date             Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/4/26        shilun           v1.0.0               创建
 */
public class Singleton {

    //volatile 保证对象不被重排
    private volatile static Singleton singleton;

    private Singleton(){};

    public static Singleton getInstance(){
        if(singleton == null){
            synchronized(Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
