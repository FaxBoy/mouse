package cn.uicp.mouse.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.uicp.mouse.dao.ReptileDataDao;
import cn.uicp.mouse.service.TestService;
import cn.uicp.mouse.util.UUIDUtil;
import cn.uicp.mouse.util.mybatis.paginator.domain.PageList;
import model.ReptileData;
import model.User;


/**
 * 
* @ClassName: LoginController 
* @Description: TODO(登录Controller) 
* @author shil
* @date 2017年4月24日 下午5:53:49 
*
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Resource
	private TestService testService;
	
	@Resource
	private ReptileDataDao reptileDataDao;
	
	static Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value = "",method=RequestMethod.POST)  
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response){
		String username = request.getParameter("Email");
		//testPage();//分页
		//test();//测试
		//test2();//测试事务
		try {
			User user=testService.getUserName("admin");
			ModelAndView mav= new ModelAndView();
			mav.addObject("userName",user.getName());
			mav.addObject("passWord",user.getPass());
			//登录验证并返回登录成功用户对象  
	        //User user=loginResult(userPhone, passWord, code, request);  
	        //把用户对象放入到session中，将会触发LoginListenner中的attributeAdded事件  
	        request.getSession().setAttribute("loginuser", user);
			mav.setViewName("index");
			logger.debug("登录成功："+user.getName());
			return mav;
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.debug("登录失败：用户不存在！");
			ModelAndView mav= new ModelAndView();
			mav.setViewName("login");
			return mav;
		}
		
	}
	
	private void testPage() {
		JSONObject json = new JSONObject();
		Map params = new HashMap();
		params.put("s", "s");
		List<?> list=this.testService.selectPage(params, 2, 3);
		PageList pagelist=(PageList)list;
		json.put("pageNumber", pagelist.getPaginator().getTotalCount());
		json.put("pageCount", pagelist.getPaginator().getTotalPages());
		json.put("curPage", pagelist.getPaginator().getPage());
		json.put("result", new JSONArray(pagelist));
		System.out.println(json);
		
		
	}

	private void test() {
		// TODO Auto-generated method stub
		Map map = testService.getUserNameByMap("yangyi");
		System.out.println(map.get("UNAME"));
		System.out.println(testService.getUserNameByString("yangyi"));
	}
	


	@RequestMapping(value = "",method=RequestMethod.GET)  
	public ModelAndView logi(HttpServletRequest request,
			HttpServletResponse response){
		User test = (User) request.getSession().getAttribute("loginuser");
		ModelAndView mav= new ModelAndView();
		if(test==null){
			mav.setViewName("project/login/loginHome");
			return mav;
		}else{
			mav.addObject("userName",test.getName());
			mav.addObject("passWord",test.getPass());
			mav.setViewName("project/home");
			return mav;
		}
	}
	
private static ArrayList<String> listname = new ArrayList<String>();  
	
	public static void readAllFile(String filepath) {  
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
		String path="/home/test/data2/";
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
				data.setKeyword("默认");
				data.setId(UUIDUtil.getUUID(16));
				data.setSourceurl("www.qichacha.com");
				sum++;
				try {
					reptileDataDao.insert(data);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				//System.out.println(ce11 + "\t" + ce10+ "\t" + ce0+ "\t" + ce1+ "\t" + ce2+ "\t" + ce3+ "\t" + ce4+ "\t" + ce5 + "\t" + ce6+ "\t" + ce7+ "\t" + ce8+ "\t" + ce9);
			}
			System.out.println(path+listname.get(i)+"写入完成;剩余:"+(count--) +"文件");
			System.out.println("已经插入："+sum);
			file.renameTo(new File(path+listname.get(i)+"bak")); 
			Thread.sleep(5000);
        }
		System.out.println("end");
		return null;
	}
	
}
