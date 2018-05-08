package cn.uicp.mouse.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import model.ReptileData;

public class ThreadTest2 {

    /**
     * 多线程处理list
     * 
     * @param data
     *            数据LinkedList,线程安全
     * @param threadNum
     *            线程数
     * @throws InterruptedException
     */
    public synchronized void handleList(LinkedList<ReptileData> data, int threadNum,HttpSolrServer httpSolrServer) throws InterruptedException {
        int length = data.size();
        int tl = length % threadNum == 0 ? length / threadNum : (length / threadNum + 1);
        CountDownLatch latch = new CountDownLatch(100);// 多少协作
        long a = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            int end = (i + 1) * tl;
            if ((i * tl) <= length) {
                // 继承thread启动线程
                // HandleThread thread = new HandleThread("线程[" + (i + 1) +"] ",data, i * tl, end > length ? length : end, latch);
                // thread.start();

                // 实现Runnable启动线程
                RunnableThread thread = new RunnableThread("线程[" + (i + 1) + "] ", data, i * tl, end > length ? length : end, latch,httpSolrServer);
                Thread runable = new Thread(thread);
                runable.start();
            }
        }
        latch.await();// 等待所有工人完成工作
        //latch = new CountDownLatch(100);
        System.out.println("结束*****************************");
        long b = System.currentTimeMillis();
        System.out.println("时间:" + (b - a) + "毫秒***********************");
    }

//    @Autowired  
//    private HttpSolrServer httpSolrServer;  
    
    // 继承Thread
    class HandleThread extends Thread {
        private String threadName;
        private List<ReptileData> data;
        private int start;
        private int end;
        private CountDownLatch latch;
        private HttpSolrServer httpSolrServer;

        public HandleThread(String threadName, List<ReptileData> data, int start, int end, CountDownLatch latch,HttpSolrServer httpSolrServer) {
            this.threadName = threadName;
            this.data = data;
            this.start = start;
            this.end = end;
            this.latch = latch;
            this.httpSolrServer=httpSolrServer;
        }

        public void run() {
            // TODO 这里处理数据
            List<ReptileData> l = data.subList(start, end);
            System.out.println(threadName + "--" + data.size() + "--" + start + "--" + end + "--");
            for (int i = 0; i < l.size(); i++) {
                // 单个线程中的数据
                System.out.println(l.get(i));
                
                ReptileData reptileData = new ReptileData();
	    			reptileData=(ReptileData) l.get(i);
	    			SolrInputDocument doc = new SolrInputDocument();
	    			doc.addField("id", reptileData.getId()); 
	    			doc.addField("tel", reptileData.getTel()); 
	    			doc.addField("address", reptileData.getAddress()); 
	    			doc.addField("keyword", reptileData.getKeyword()); 
	    			doc.addField("item_management", reptileData.getManagement()); 
	    			try {
	    				httpSolrServer.add(doc);
	    			} catch (SolrServerException | IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			} 
	    	        try {
	    				httpSolrServer.commit();
	    			} catch (SolrServerException | IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
                
                
            }
            latch.countDown();// 工人完成工作，计数器减一
        }
    }

    // 实现Runnable
    class RunnableThread implements Runnable {
        private String threadName;
        private List<ReptileData> data;
        private int start;
        private int end;
        private CountDownLatch latch;
        private HttpSolrServer httpSolrServer;

        public RunnableThread(String threadName, List<ReptileData> data, int start, int end, CountDownLatch latch,HttpSolrServer httpSolrServer) {
            this.threadName = threadName;
            this.data = data;
            this.start = start;
            this.end = end;
            this.latch = latch;
            this.httpSolrServer = httpSolrServer;
        }

        public void run() {
            // TODO 这里处理数据
            List<ReptileData> l = data.subList(start, end);
            System.out.println(threadName + "--" + data.size() + "--" + start + "--" + end + "--");
            for (int i = 0; i < l.size(); i++) {
                // 单个线程中的数据
                //System.out.println(l.get(i));
                ReptileData reptileData = new ReptileData();
	    			reptileData=(ReptileData) l.get(i);
	    			SolrInputDocument doc = new SolrInputDocument();
	    			doc.addField("id", reptileData.getId()); 
	    			doc.addField("tel", reptileData.getTel()); 
	    			doc.addField("code", reptileData.getCode()); 
	    			doc.addField("compname", reptileData.getCompname()); 
	    			doc.addField("linkman", reptileData.getLinkman()); 
	    			doc.addField("email", reptileData.getEmail()); 
	    			doc.addField("creationdate", reptileData.getCreationdate()); 
	    			doc.addField("capital", reptileData.getCapital()); 
	    			doc.addField("management", reptileData.getManagement()); 
	    			doc.addField("compurl", reptileData.getCompurl()); 
	    			doc.addField("sourceurl", reptileData.getSourceurl()); 
	    			doc.addField("address", reptileData.getAddress()); 
	    			doc.addField("keyword", reptileData.getKeyword()); 
	    			try {
	    				httpSolrServer.add(doc);
	    			} catch (SolrServerException | IOException e) {
	    				e.printStackTrace();
	    			} 
	    	        latch.countDown();// 工人完成工作，计数器减一
            }
            System.out.println("提交--" + start + "--" + end + "--");
            try {
				httpSolrServer.commit();
			} catch (SolrServerException | IOException e) {
				e.printStackTrace();
			} 
            
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        ThreadTest2 test = new ThreadTest2();
//
//        // 准备数据
//        LinkedList<String> data = new LinkedList<String>();
//        for (int i = 0; i < 100000; i++) {
//            data.add("item" + "  " + i);
//        }
//        test.handleList(data, 100);
        // System.out.println(ArrayUtils.toString(data));

    }
}