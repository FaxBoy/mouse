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
import javax.servlet.http.HttpServletRequest;

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
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.uicp.mouse.dao.ReptileDataDao;
import cn.uicp.mouse.service.UserDataService;
import cn.uicp.mouse.util.ResultVO;
import cn.uicp.mouse.util.StringUtils;
import cn.uicp.mouse.util.ThreadTest2;
import cn.uicp.mouse.util.UUIDUtil;
import model.ReptileData;
import model.UserData;



@Controller
@RequestMapping("/solr")
public class SolrController {
	
	@Resource  
    private UserDataService userDataService;
	
	@Autowired  
    private HttpSolrServer httpSolrServer;  
	
	@Resource
	private ReptileDataDao reptileDataDao;
	
	@Resource
	protected HttpServletRequest request;
	
	@RequestMapping(value = "/suggest",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO<?> suggest(String keyWord) throws SolrServerException {
		if(!StringUtils.isEmpty(keyWord)) {
			List<Map<String, String>> wordList = new ArrayList<Map<String, String>>();
			SolrQuery query = new SolrQuery();
			query.set("q",  keyWord);// 查询的词  
            query.set("qt", "/suggest");// 请求到suggest中  
            QueryResponse rsp = this.httpSolrServer.query(query);
            // 上面取结果的代码  
            SpellCheckResponse re = rsp.getSpellCheckResponse();// 获取拼写检查的结果集  
            if (re != null) {  
                for (Suggestion s : re.getSuggestions()) {  
                    List<String> list = s.getAlternatives();// 获取所有 的检索词  
                    for (String spellWord : list) {  
                        Map<String, String> map = new HashMap<String, String>();  
                        map.put("code", spellWord);  
                        wordList.add(map);  
                    }  
                }
                return ResultVO.success(null, wordList);
            }
            return ResultVO.success(null, "No data");
		}
		return ResultVO.success(null, "Invalid parameter");
	}
	
	@RequestMapping(value = "/demo",method=RequestMethod.GET)
	public ModelAndView demo() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("solr/demo");
		return mav;
	}
	
	
	@RequestMapping(value = "/query",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO<?> query() throws SolrServerException {
		Map maps = getParameterMap();
		List<Map<String,Object>> list = null;
		SolrQuery query = new SolrQuery();
		//query.addFilterQuery("id:0323d0542271");
		query.setQuery("item_keywords:"+maps.get("keyWord"));
		query.setHighlight(true);
		query.addHighlightField("item_keywords");
		query.addHighlightField("item_address");
		query.setStart(20*Integer.valueOf(maps.get("start").toString()));
		query.setRows(20);
		query.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀  
        query.setHighlightSimplePost("</font>");//后缀
        query.setParam("hl.fl", "item_address");
		QueryResponse rsp=httpSolrServer.query(query);
		Double qtime = 0.001*Double.valueOf(rsp.getResponseHeader().get("QTime").toString());
		System.out.println(qtime);
		SolrDocumentList results = rsp.getResults();
		//获取查询到的数据总量
		long numFound = results.getNumFound();
		//判断总量是否大于0，
		if(numFound <= 0) {
			return ResultVO.success(null, null);
		}else {
			list = new ArrayList<>();
			//遍历结果集
			for (SolrDocument doc : results) {
				//得到每条数据的map集合
				Map<String, Object> map = new Hashtable<String, Object>();
				//map=doc.getFieldValueMap();
				map.put("name", null==doc.get("item_name") ? "":doc.get("item_name"));
				map.put("address", null==doc.get("item_address") ? "":doc.get("item_address"));
				map.put("companyname", null==doc.get("item_companyname") ? "":doc.get("item_companyname"));
				map.put("keyword", null==doc.get("item_keyword") ? "":doc.get("item_keyword"));
				map.put("management", null==doc.get("item_management") ? "":doc.get("item_management"));
				map.put("tel", null==doc.get("item_tel") ? "":doc.get("item_tel"));
				list.add(map);
			}
			JSONObject json = new JSONObject();
			json.put("data", list);
			json.put("qtime", qtime);
			json.put("numFound", numFound);
			return ResultVO.success(null, json);
		}
	}
	
	/**
	 * 获取参数
	 * author:shilun
	 * 2016年3月18日下午4:31:55
	 * description:
	 * @return: 
	 */
	public Map<String, Object> getParameterMap(){
		Map<String, Object> params = new HashMap<String, Object>();
		Map<?,?> parameterMap = request.getParameterMap();
		if (parameterMap != null) {
			Object[] keyList = parameterMap.keySet().toArray();
			for (int i = 0; i < keyList.length; i++) {
				String key = (String) keyList[i];
				Object value = parameterMap.get(key);

				if (value.getClass() == String[].class) {
					String[] temp = (String[]) value;
					if (temp.length == 1) {
						if(temp[0] == null || temp[0].trim().length()==0 || ("").equals(temp[0]))
							continue;
						value = temp[0];
					}
				}
				params.put(key, value);
			}
		}
		return params;
	}
	
	
	
}
