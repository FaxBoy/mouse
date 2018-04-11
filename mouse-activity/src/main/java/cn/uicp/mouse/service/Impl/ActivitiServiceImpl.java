package cn.uicp.mouse.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.uicp.mouse.service.ActivitiService;

@Service
public class ActivitiServiceImpl implements ActivitiService{
	// 注入为我们自动配置好的服务
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	// 开始流程，传入申请者的id以及公司的id
	public void startProcess(Long personId, Long compId) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("personId", personId);
		variables.put("compId", compId);
		runtimeService.startProcessInstanceByKey("joinProcess", variables);
	}

	// 获得某个人的任务别表
	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().taskCandidateUser(assignee).list();
	}

	// 完成任务
	public void completeTasks(Boolean joinApproved, String taskId) {
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("joinApproved", joinApproved);
		taskService.complete(taskId, taskVariables);
	}
	
	@Override  
    public boolean startActivityDemo() {  
        System.out.println("method startActivityDemo begin....");  
        Map<String,Object> map = new HashMap<String,Object>();  
        map.put("apply","zhangsan");  
        map.put("approve","lisi");  
        //流程启动  
        ExecutionEntity pi1 = (ExecutionEntity) runtimeService.startProcessInstanceByKey("leave",map);  
        String processId = pi1.getId();  
        String taskId = pi1.getTasks().get(0).getId();  
        taskService.complete(taskId, map);//完成第一步申请  
          
        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();  
        String taskId2 = task.getId();  
        map.put("pass", false);  
        taskService.complete(taskId2, map);//驳回申请  
        System.out.println("method startActivityDemo end....");  
        return false;  
    }  
}
