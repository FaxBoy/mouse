package cn.uicp.mouse.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.uicp.mouse.dao.CompRepository;
import cn.uicp.mouse.dao.PersonRepository;
import model.Comp;
import model.Person;

import java.util.Arrays;
import java.util.List;

public interface JoinService {

	// 加入公司操作，可从DelegateExecution获取流程中的变量
	public void joinGroup(DelegateExecution execution);

	// 获取符合条件的审批人，演示这里写死，使用应用使用实际代码
	public List<String> findUsers(DelegateExecution execution);
}
