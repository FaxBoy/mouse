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
import cn.uicp.mouse.service.UserDataService;
import cn.uicp.mouse.util.ResultVO;
import cn.uicp.mouse.util.ThreadTest2;
import cn.uicp.mouse.util.UUIDUtil;
import model.ReptileData;
import model.UserData;



@Controller
@RequestMapping("/t")
public class TestController {
	
	@Resource  
    private UserDataService userDataService;
	
	@Autowired  
    private HttpSolrServer httpSolrServer;  
	
	@Resource
	private ReptileDataDao reptileDataDao;
	
	@RequestMapping(value = "/getTime",method=RequestMethod.GET)  
	@ResponseBody
	public UserData getTime() throws SolrServerException, IOException{
		UserData userData= userDataService.selectByPrimaryKey("aa");
		int num =0,count=0;
		for (int i = 19; i < 100; i++) {
			num=i*1000;
			Map m = new HashMap<>();
			m.put("i", num);
			List list =userDataService.select(m);
			for (int k = 0; k < list.size(); k++) {
				userData=(UserData) list.get(k);
				SolrInputDocument doc = new SolrInputDocument();
				doc.addField("id", userData.getId()); 
				doc.addField("item_name", userData.getName()); 
				doc.addField("item_tel", userData.getTel()); 
				doc.addField("item_address", userData.getAddress()); 
				doc.addField("item_companyname", userData.getCompanyname()); 
				doc.addField("item_keyword", userData.getKeyword()); 
				doc.addField("item_management", userData.getManagement()); 
				doc.addField("item_comurl", userData.getComUrl());
				doc.addField("item_urlsource", userData.getUrlsource());
				this.httpSolrServer.add(doc); 
				//System.out.println(response);  
		        this.httpSolrServer.commit();
			}
			count +=list.size();
			System.out.println("执行成功："+count+"条");
			num++;
		}
		return userData;
	}
	
	@RequestMapping(value = "/query",method=RequestMethod.GET)  
	@ResponseBody
	public ResultVO<?> query() throws SolrServerException, IOException{
		List list = null;
		SolrQuery query = new SolrQuery();
		//query.addFilterQuery("id:0323d0542271");
		query.setQuery("id:0323d0542271");
		QueryResponse rsp=httpSolrServer.query(query);
		SolrDocumentList results = rsp.getResults();
		//获取查询到的数据总量
		long numFound = results.getNumFound();
		//判断总量是否大于0，
		if(numFound <= 0) {
			//如果小于0，表示未查询到任何数据，返回null
			return null;
		}else {
			//如果大于0，表示有数据
			//创建list存储每条数据
			list = new ArrayList<>();
			//遍历结果集
			for (SolrDocument doc : results) {
				//得到每条数据的map集合
				Map<String, Object> map = new Hashtable<String, Object>();
				map=doc.getFieldValueMap();
				System.out.println(map.get("id"));
				//添加到list
				list.add(map);
			}
			//返回list集合
//			list.clear();
//			Map<String, Object> m = new HashMap<>();
//			m.put("a", "rr");
//			m.put("b", "rr");
//			list.add(m);
			//JSONArray jsonArray = JSONArray.fromObject(lis);
			return ResultVO.success(null, list);
			//return ResultVO.success(null, null);
		}
	}
	
	private static ArrayList<String> listname = new ArrayList<String>();  
	
	public static void readAllFile(String filepath) {  
		//listname.clear();
        File file= new File(filepath);  
        if(!file.isDirectory()){  
            listname.add(file.getName());  
        }else if(file.isDirectory()){  
            System.out.println("文件");  
            String[] filelist=file.list();  
            for(int i = 0;i<filelist.length;i++){  
                File readfile = new File(filepath);  
                if (!readfile.isDirectory()) {  
                    listname.add(readfile.getName());  
                } else if (readfile.isDirectory()) {  
                    readAllFile(filepath + "//" + filelist[i]);//递归  
                }  
            }  
        }  
        for(int i = 0;i<listname.size();i++){  
            System.out.println(listname.get(i));  
        }  
    }  
	
	@RequestMapping(value = "/setData",method=RequestMethod.GET)  
	@ResponseBody
	public JSONObject setData() throws IOException, InterruptedException{
		//File file = new File("/tools/data/8.xls");
		listname.clear();
		String path="/home/test/data/";
        readAllFile(path);
        System.out.println("共有："+listname.size());
        int count=listname.size(),sum=0;
        for (int i = 0; i < listname.size(); i++) {
        		if(listname.get(i).indexOf("bak")!=-1) 
        			continue;
			System.out.println(path+listname.get(i));
			File file = new File(path+listname.get(i));  
			//InputStream input = new FileInputStream(file);
			FileInputStream fi = new FileInputStream(file);
			HSSFWorkbook work = new HSSFWorkbook(fi);
			HSSFSheet sheet = work.getSheetAt(0);
			//得到第一个sheet
			int rowNo = sheet.getLastRowNum()+1;
			//得到行数
			for (int j = 1; j < rowNo; j++) {
				HSSFRow row = sheet.getRow(j);
				HSSFCell cell0 = row.getCell((short) 0);
				HSSFCell cell1 = row.getCell((short) 1);
				HSSFCell cell2 = row.getCell((short) 2);
				HSSFCell cell3 = row.getCell((short) 3);
				HSSFCell cell4 = row.getCell((short) 4);
				HSSFCell cell5 = row.getCell((short) 5);
				HSSFCell cell6 = row.getCell((short) 6);
				HSSFCell cell7 = row.getCell((short) 7);
				HSSFCell cell8 = row.getCell((short) 8);
				HSSFCell cell9 = row.getCell((short) 9);
				ReptileData data = new ReptileData();
				
				data.setCompname(cell0 == null?"空":cell0.getStringCellValue().trim());
				data.setCode(cell1 == null?"空":cell1.getStringCellValue().trim());
				data.setLinkman(cell2 == null?"空":cell2.getStringCellValue().trim());
				data.setCreationdate(cell3 == null?"空":cell3.getStringCellValue().trim());
				data.setCapital(cell4 == null?"空":cell4.getStringCellValue().trim());
				data.setAddress(cell5 == null?"空":cell5.getStringCellValue().trim());
				data.setEmail(cell6 == null?"空":cell6.getStringCellValue().trim());
				data.setTel(cell7 == null?"空":cell7.getStringCellValue().trim());
				data.setManagement(cell8 == null?"空":cell8.getStringCellValue().trim());
				data.setCompurl(cell9 == null?"空":cell9.getStringCellValue().trim());
				data.setCompurl(data.getCompurl().split("[?]")[0]);
				data.setKeyword(listname.get(i).split("-")[0]);
				data.setId(UUIDUtil.getUUID(16));
				data.setSourceurl("www.qichacha.com");
				sum++;
				try {
					reptileDataDao.insert(data);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println(path+listname.get(i)+"写入完成;剩余:"+(count--) +"文件");
			System.out.println("已经插入："+sum);
			file.renameTo(new File(path+listname.get(i)+"bak")); 
			Thread.sleep(5000);
        }
		System.out.println("end");
		return null;
	}
	
	
	
	@RequestMapping(value = "/setReptileData",method=RequestMethod.GET)  
	@ResponseBody
	public ResultVO<?> setReptileData() throws SolrServerException, IOException, InterruptedException{
		// 准备数据\
		//httpSolrServer = new HttpSolrServer("http://47.98.179.168:8080/solr/reptile_core");
		int num=0;
		for (int j = 0; j < 3; j++) {
			//httpSolrServer = new HttpSolrServer("http://47.98.179.168:8080/solr/reptile_core");
			Map m = new HashMap<>();
			m.put("i", j);
			//System.out.println(m.get("i"));
			List list =reptileDataDao.select(m);
			for (int i = 0; i < list.size(); i++) {
                // 单个线程中的数据
                ReptileData reptileData = new ReptileData();
	    			reptileData=(ReptileData) list.get(i);
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
	    			this.httpSolrServer.add(doc); 
	    			//System.out.println(response);  
			    this.httpSolrServer.commit();
            }
            System.out.println("add--" + num + "--" + (num+=list.size()) + "--");
            
		}
		
		
        return ResultVO.success(null, num);
	}
	
	
	@RequestMapping(value = "/setReptileDatatest",method=RequestMethod.GET)  
	@ResponseBody
	public ResultVO<?> setReptileDatatest() throws SolrServerException, IOException, InterruptedException{
		// 准备数据\
		httpSolrServer = new HttpSolrServer("http://47.98.179.168:8080/solr/reptile_core");
		int num=0;
		for (int j = 0; j < 3; j++) {
			Map m = new HashMap<>();
			m.put("i", j);
			System.out.println(m.get("i"));
			List list =reptileDataDao.select(m);
			for (int i = 0; i < list.size(); i++) {
                // 单个线程中的数据
                ReptileData reptileData = new ReptileData();
	    			reptileData=(ReptileData) list.get(i);
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
            }
            System.out.println("提交--" + num + "--" + (num+=list.size()) + "--");
            try {
				httpSolrServer.commit();
			} catch (SolrServerException | IOException e) {
				e.printStackTrace();
			} 
		}
        return ResultVO.success(null, num);
	}
	
}
