package cn.uicp.mouse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;

@Controller
public class ErrorHandleController implements ErrorController{

    /**
     * @Description TODO(错误页面)
     * @author shil <sl166199@163.com>
     * @date 2017/11/27 13:50
     * @param []
     * @return java.lang.String
     */
    @Override
    public String getErrorPath() {
        System.out.println("错误页面");
        return "/error";
    }
}
