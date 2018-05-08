package cn.uicp.mouse.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.formula.functions.T;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.uicp.mouse.dao.ReptileDataDao;
import cn.uicp.mouse.note.thread.Factory;
import cn.uicp.mouse.service.UserDataService;
import cn.uicp.mouse.util.ResultVO;
import cn.uicp.mouse.util.ThreadTest2;
import cn.uicp.mouse.util.UUIDUtil;
import model.ReptileData;
import model.UserData;

@Controller
@RequestMapping("/thread")
public class ThreadController {
	
	@Resource  
    private UserDataService userDataService;
	
	@Autowired  
    private HttpSolrServer httpSolrServer;  
	
	@Resource
	private ReptileDataDao reptileDataDao;
	
	private static List list=null;
	
	private static int num=0;
	
	@RequestMapping(value = "/set",method=RequestMethod.GET)  
	@ResponseBody
	public ResultVO<?> set() throws SolrServerException, IOException{
		Map m = new HashMap<>();
		m.put("i", 0);
		list =reptileDataDao.select(m);
		num=list.size();
//		ReptileData reptileData = new ReptileData();
//		for (int k = 0; k < list.size(); k++) {
//			reptileData=(ReptileData) list.get(k);
//			SolrInputDocument doc = new SolrInputDocument();
//			doc.addField("id", reptileData.getId()); 
//			doc.addField("tel", reptileData.getTel()); 
//			doc.addField("address", reptileData.getAddress()); 
//			doc.addField("keyword", reptileData.getKeyword()); 
//			doc.addField("item_management", reptileData.getManagement()); 
//			this.httpSolrServer.add(doc); 
//	        this.httpSolrServer.commit();
//		}
		//Factory factory = new Factory();
		Person p1 = getPerson();
		Person p2 = getPerson();
		Person p3 = getPerson();
		Person p4 = getPerson();
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		return ResultVO.success(null, null);
	}
	
	public int getzhuantou() {
		synchronized(this) {//synchronized锁最小的范围，效率最快
			if (num == 0) {
				System.out.println("结束");
				throw new RuntimeException(Thread.currentThread().getName() + ",没有砖头搬了!");
			}
			// 获取线程名（工人名） 及 剩下砖头数
			ReptileData reptileData = new ReptileData();
			reptileData=(ReptileData) list.get(num-1);
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
	        
			Thread.yield();
			return num--;
		}
	}
	
	@RequestMapping(value = "/set2",method=RequestMethod.GET)  
	@ResponseBody
	public ResultVO<?> set2() throws SolrServerException, IOException, InterruptedException{
		System.out.println(num);
		// 准备数据
		ThreadTest2 test = new ThreadTest2();
		Map m = new HashMap<>();
		m.put("i", 3);
		System.out.println(m.get("i"));
		List list =reptileDataDao.select(m);
		System.out.println(list.size());
        LinkedList<ReptileData> data = new LinkedList<ReptileData>();
        for (int i = 0; i < list.size(); i++) {
            data.add((ReptileData) list.get(i));
        }
        httpSolrServer = new HttpSolrServer("http://47.98.179.168:8080/solr/reptile_core");
        test.handleList(data, 10,httpSolrServer);
       // data.clear();
        //Thread.sleep(5000);
        System.out.println("end");
		return ResultVO.success(null, num++);
	}
	
	
	// 工人
	class Person extends Thread {
		// 不停的搬砖
		public void run() {
			while (num!=0) {
				
		        //getzhuantou();
		        System.out.println(getName() + "处理了第" + (getzhuantou()-1) + "条数据");
				// 当线程的run方法中出现了异常，且我们没有 解决，那么该线程终止并死亡。但不会影响 当前进程中的其他线程。
				Thread.yield();
			}
		}
	}

		// 获取工人
		public Person getPerson() {
			return new Person();
		}
	
	
	
}
