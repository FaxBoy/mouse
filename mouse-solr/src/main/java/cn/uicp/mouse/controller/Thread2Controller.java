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
import java.util.concurrent.CountDownLatch;

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
@RequestMapping("/threads")
public class Thread2Controller {
	
	@Resource  
    private UserDataService userDataService;
	
	@Autowired  
    private HttpSolrServer httpSolrServer;  
	
	@Resource
	private ReptileDataDao reptileDataDao;
	
	//private static List list=null;
	
	private static int num=0;
	
	@RequestMapping(value = "/set",method=RequestMethod.GET)  
	@ResponseBody
	public ResultVO<?> set() throws SolrServerException, IOException, InterruptedException{
		// 准备数据
		ThreadTest2 test = new ThreadTest2();
		for (int j = 0; j < 1030; j++) {
			Map m = new HashMap<>();
			m.put("i", j*1000);
			System.out.println(m.get("i"));
			List list =reptileDataDao.select(m);
			System.out.println(list.size());
			num+=list.size();
	        LinkedList<ReptileData> data = new LinkedList<ReptileData>();
	        for (int i = 0; i < list.size(); i++) {
	            data.add((ReptileData) list.get(i));
	        }
	        test.handleList(data, 10,httpSolrServer);
	        Thread.sleep(5000);
		}
        System.out.println("end"+"共执行："+num);
		return ResultVO.success(null, "共执行："+num);
	}
	
	@RequestMapping(value = "/set2",method=RequestMethod.GET)  
	@ResponseBody
	public ResultVO<?> set2() throws SolrServerException, IOException, InterruptedException{
		System.out.println(num);
		// 准备数据
		ThreadTest2 test = new ThreadTest2();
		Map m = new HashMap<>();
		m.put("i", num);
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
	
}
