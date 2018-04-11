package cn.uicp.mouse.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.uicp.mouse.pojo.Test;


public class LoginListenner implements HttpSessionAttributeListener {

	private Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
	public Map<String, HttpSession> getMap() {
		return map;
	}

	public void setMap(Map<String, HttpSession> map) {
		this.map = map;
	}
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();  
        if (name.equals("loginuser")) {  
            Test user = (Test) event.getValue();  
            if (map.get(user.getSt_name()) != null) {  
                HttpSession session = map.get(user.getSt_name());  
                session.removeAttribute(user.getSt_name());  
                session.invalidate();  
            }  
            map.put(user.getSt_name(), event.getSession());  
        }
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();  
		  
        if (name.equals("loginuser")) {  
            Test user = (Test) event.getValue();  
            map.remove(user.getSt_name());  
  
        } 
		
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
	}

}
