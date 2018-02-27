package cn.uicp.mouse.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;  
import org.activiti.engine.TaskService;  
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;  
import org.activiti.engine.task.Task;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service; 

import cn.uicp.mouse.service.ActivityConsumerService;

@Service("activityService")
public class ActivityConsumerServiceImpl implements ActivityConsumerService{

	@Autowired  
    private RuntimeService runtimeService;  
    @Autowired  
    private TaskService taskService;
	
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
