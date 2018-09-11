package org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bean.Action;
import org.dao.ActionDao;
import org.dto.ActionDto;
import org.service.ActionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDao actionDao;
	
	/*
	 * 获取action列表
	 */
	public List<ActionDto> getActionList(){
		List<Action> list=actionDao.select();
		List<ActionDto> actionList=new ArrayList<ActionDto>();
		for(Action action:list) {
			ActionDto actionDto=new ActionDto();
			BeanUtils.copyProperties(action, actionDto);
			actionList.add(actionDto);
		}
		return actionList;
	}
	
	/*
	 * 根据id获取action
	 */
	public ActionDto getActionById(Integer id) {
		Action actionTo=new Action();
		actionTo.setId(id);
		Action action=actionDao.selectById(actionTo);
		ActionDto actionDto=new ActionDto();
		BeanUtils.copyProperties(action, actionDto);
		return actionDto;
	}
	
	/*
	 * 增加action
	 */
	public boolean add(ActionDto actionDto) {
		Action action=new Action();
		BeanUtils.copyProperties(actionDto, action);
		int i=actionDao.insert(action);
		return i==1;
	}
	
	/*
	 * 根据id删除action
	 */
	public boolean delete(Integer id) {
		int i=actionDao.delete(id);
	    return i==1;
	}
	
	/*
	 * 修改action
	 */
	public boolean modify(ActionDto actionDto) {
		Action action=new Action();
		BeanUtils.copyProperties(actionDto, action);
		int i=actionDao.modify(action);
		return i==1;
	}
}
