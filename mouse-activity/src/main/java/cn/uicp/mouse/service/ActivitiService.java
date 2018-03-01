package cn.uicp.mouse.service;

import java.util.List;

import javax.transaction.Transactional;

import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface ActivitiService {

	// 开始流程，传入申请者的id以及公司的id
	public void startProcess(Long personId, Long compId);

	// 获得某个人的任务别表
	public List<Task> getTasks(String assignee);

	// 完成任务
	public void completeTasks(Boolean joinApproved, String taskId);
	
}
