package cn.uicp.mouse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/demo")
    public String demo() {
        return "hello world";
    }

    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response) throws IOException {
        //在SSH框架中，可以通过HttpServletResponse response=ServletActionContext.getResponse();取出Respond对象
        //清空一下response对象，否则出现缓存什么的
        response.reset();
        //指明这是一个下载的respond
        response.setContentType("application/x-download");
        //这里是提供给用户默认的文件名
        String filename = "json.json";
        //双重解码、防止乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition","attachment;filename=" + filename);
        /*
         * 这里是最关键的一步。
         * 直接把这个东西写到response输出流里面，给用户下载。
         * 如果输出到c:\b.txt就是：
         * PrintWriter printwriter = new PrintWriter(new FileWriter("c:\\b.txt",true));
         * 现在写好respond头，就写成：
         * PrintWriter printwriter = new PrintWriter(response.getOutputStream());
         * 把PrintWriter的输出点改一下
         */
        PrintWriter printwriter = new PrintWriter(response.getOutputStream());
        printwriter.println("呵呵！kafdjkljfdlkjafs");
        //打印流的所有输出内容，必须关闭这个打印流才有效
        printwriter.close();
        return null;
    }
    //这里指定是条状的jsp界面
    @RequestMapping(value = "/index")
    public ModelAndView index(Model model) {
        model.addAttribute("sb", "this is my fries测试不是好领导了副经理看fjldj 1123123");
        return new ModelAndView("WebSocket");
    }

}
