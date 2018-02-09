package cn.uicp.mouse.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/message")
public class MessageControlle {

	@RequestMapping(value = "")
	public ModelAndView index(Model model) {
        return new ModelAndView("message");
    }
	
}
