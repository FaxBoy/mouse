package cn.uicp.mouse.service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import model.User;


@WebService
@SOAPBinding(style =Style.RPC)  
public interface TestWebService {
	User getUserName(String userId);
}
