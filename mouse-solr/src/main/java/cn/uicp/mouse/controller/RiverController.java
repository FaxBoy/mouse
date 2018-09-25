package cn.uicp.mouse.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.uicp.mouse.service.RiverService;
import cn.uicp.mouse.service.UserDataService;
import cn.uicp.mouse.util.ResultVO;
import model.River;

/**
 * 
* @ClassName: RiverController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author shil
* @date 2018年9月15日 上午9:37:10 
*
 */
@Controller
@RequestMapping("/river")
public class RiverController {

	@Resource  
    private RiverService riverService;
	
	/**
	 * 
	* @Title: setReptileData 
	* @Description: TODO(初始化河流数据)
	* @author shil
	* @date 2018年9月15日 上午9:43:45 
	* @param @return
	* @param @throws SolrServerException
	* @param @throws IOException
	* @param @throws InterruptedException    设定文件 
	* @return ResultVO<?>    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/initRiverData",method=RequestMethod.GET)  
	@ResponseBody
	public ResultVO<?> setReptileData() throws SolrServerException, IOException, InterruptedException{
		
		String json = "";
        try {
            File jsonFile = ResourceUtils.getFile("classpath:river.json");  
            json = FileUtils.readFileToString(jsonFile);
        } 
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println(json);
        JSONArray riverArr = JSONArray.parseArray(JSONObject.parseObject(json).getString("data"));
        int sum = 0;
		for (int i = 0; i < riverArr.size(); i++) {
			River river = new River();
			JSONObject obj = JSONObject.parseObject(riverArr.get(i).toString());
			
			river.setTybm(obj.getString("ST_TYBM"));
			river.setRiverName(obj.getString("ST_NAME"));
			sum += riverService.insert(river);
		}
		return ResultVO.success("success",sum);
	}
	
	
}
