package org.controller.system;

import java.util.List;

import org.bean.Group;
import org.constant.PageCodeEnum;
import org.dto.ActionDto;
import org.dto.GroupDto;
import org.dto.PageCodeDto;
import org.service.ActionService;
import org.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/actions")
public class ActionController {
    
	@Autowired
	private ActionService actionService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<ActionDto> actionList(Model model){
		return actionService.getActionList();
	}
	
	/*
	 * 添加用户组
	 */
	@RequestMapping(method=RequestMethod.POST)
	public PageCodeDto add(ActionDto actionDto){
		PageCodeDto pageDto;
		if(actionService.add(actionDto)) {
			pageDto=new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
		}else {
			pageDto=new PageCodeDto(PageCodeEnum.ADD_FAIL);
		}
		return pageDto;
	}
	
	/*
	 * 初始化修改用户组界面
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ActionDto init(@PathVariable(value="id")Integer id){
         return actionService.getActionById(id);		
	}
	
	/*
	 * 初始化修改用户组界面
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public PageCodeDto modify(ActionDto actionDto){
        PageCodeDto pageDto;
        if(actionService.modify(actionDto)) {
        	pageDto=new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        }else {
        	pageDto=new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return pageDto;
	}
	
	/*
	 * 根据id删除用户组
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public PageCodeDto delete(@PathVariable(value="id")Integer id){
        PageCodeDto pageDto;
        if(actionService.delete(id)) {
        	pageDto=new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        }else {
        	pageDto=new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
        }
        return pageDto;
	}
	
	
	
}
