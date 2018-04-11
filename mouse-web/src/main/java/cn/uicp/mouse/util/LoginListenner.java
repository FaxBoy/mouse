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
            if (map.get(user.getUname()) != null) {  
                HttpSession session = map.get(user.getUname());  
                session.removeAttribute(user.getUname());  
                session.invalidate();  
            }  
            map.put(user.getUname(), event.getSession());  
        }
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();  
		  
        if (name.equals("loginuser")) {  
            Test user = (Test) event.getValue();  
            map.remove(user.getUname());  
  
        } 
		
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
	}

}
