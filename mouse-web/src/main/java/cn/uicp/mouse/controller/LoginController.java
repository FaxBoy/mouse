package cn.uicp.mouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.uicp.mouse.pojo.Test;
import cn.uicp.mouse.service.TestService;
import cn.uicp.mouse.util.mybatis.paginator.domain.PageList;
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
	
	private void test2(){
		List<Test> list = new ArrayList();
		Test test = new Test();
//		test.setUname("掌声");
//		test.setUpaw("zhangsheng");
//		test.setAge(20);
//		test.setSex("男");
//		list.add(test);
//		
//		test = new Test();
//		test.setUname("掌声2");
//		test.setUpaw("zhangsheng");
//		test.setAge(20);
//		test.setSex("男");
//		list.add(test);
//		
//		test = new Test();
//		test.setUname("清风");
//		test.setUpaw("qingf");
//		test.setAge(21);
//		test.setSex("女");
		list.add(test);
		
		testService.insertList(list);
		
		
	}

	@RequestMapping(value = "",method=RequestMethod.GET)  
	public ModelAndView logi(HttpServletRequest request,
			HttpServletResponse response){
		Test test = (Test) request.getSession().getAttribute("loginuser");
		ModelAndView mav= new ModelAndView();
		if(test==null){
			mav.setViewName("project/login/loginHome");
			return mav;
		}else{
			mav.addObject("userName",test.getSt_name());
			mav.addObject("passWord",test.getSt_pass());
			mav.setViewName("project/home");
			return mav;
		}
	}
	
}
