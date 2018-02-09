package cn.uicp.mouse.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


@Component
@ServerEndpoint("/websocket")  //该注解表示该类被声明为一个webSocket终端
public class MySocket {
    //初始在线人数
    private static int online_num = 0;
    //线程安全的socket集合
    private static CopyOnWriteArraySet<MySocket> webSocketSet = new CopyOnWriteArraySet<MySocket>();
    //会话
    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有链接加入，当前人数为:"+getOnline_num());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有链接关闭,当前人数为:"+getOnline_num());
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException{
        System.out.println("来自客户端的消息:"+message);
        for(MySocket item:webSocketSet){
            //this.session.getAsyncRemote().sendText(String.valueOf(getOnline_num()));
            this.session.getBasicRemote().sendText(String.valueOf(getOnline_num()));
        }
    }
    public synchronized int getOnline_num(){
        return MySocket.online_num;
    }
    public synchronized int subOnlineCount(){
        return MySocket.online_num--;
    }
    public synchronized int addOnlineCount(){
        return MySocket.online_num++;
    }
}
