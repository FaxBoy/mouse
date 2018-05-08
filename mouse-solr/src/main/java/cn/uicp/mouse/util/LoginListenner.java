package cn.uicp.mouse.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import model.User;


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
        		User user = (User) event.getValue();  
            if (map.get(user.getName()) != null) {  
                HttpSession session = map.get(user.getName());  
                session.removeAttribute(user.getName());  
                session.invalidate();  
            }  
            map.put(user.getName(), event.getSession());  
        }
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();  
		  
        if (name.equals("loginuser")) {  
        	User user = (User) event.getValue();  
            map.remove(user.getName());  
  
        } 
		
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
	}

}
