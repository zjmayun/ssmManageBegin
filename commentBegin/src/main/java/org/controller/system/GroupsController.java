package org.controller.system;

import java.util.List;

import org.bean.Group;
import org.constant.PageCodeEnum;
import org.dto.GroupDto;
import org.dto.PageCodeDto;
import org.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/groups")
public class GroupsController {
    
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<GroupDto> groupList(Model model){
		return groupService.selectListGroup(new Group());
	}
	
	/*
	 * 添加用户组
	 */
	@RequestMapping(method=RequestMethod.POST)
	public PageCodeDto add(GroupDto groupDto){
		PageCodeDto pageDto;
		if(groupService.add(groupDto)) {
			pageDto=new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
		}else {
			pageDto=new PageCodeDto(PageCodeEnum.ADD_FAIL);
		}
		return pageDto;
	}
	
	/*
	 * 为用户组分配菜单
	 */
	@RequestMapping(value="/{id}/menus",method=RequestMethod.POST)
	public PageCodeDto assignMenu(GroupDto groupDto){
		PageCodeDto pageDto;
		if(groupService.assingnMenuAndAction(groupDto)) {
			pageDto=new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
		}else {
			pageDto=new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
		}
		return pageDto;
	}
	
	/*
	 * 初始化修改用户组界面
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public GroupDto init(@PathVariable(value="id")Integer id){
         return groupService.selectById(id);		
	}
	
	/*
	 * 分配菜单以及动作
	 */
	@RequestMapping(value="/{id}/menus",method=RequestMethod.GET)
	public GroupDto assingn(@PathVariable(value="id")Integer id){
         return groupService.selectByGroupId(id);		
	}
	
	/*
	 * 初始化修改用户组界面
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public PageCodeDto modify(GroupDto groupDto){
        PageCodeDto pageDto;
        if(groupService.modify(groupDto)) {
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
	public PageCodeDto delete(GroupDto groupDto){
        PageCodeDto pageDto;
        if(groupService.delete(groupDto.getId())) {
        	pageDto=new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        }else {
        	pageDto=new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
        }
        return pageDto;
	}
	
	
	
	
	
}
