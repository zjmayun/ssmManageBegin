package org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bean.Action;
import org.bean.Group;
import org.bean.GroupAction;
import org.bean.GroupMenu;
import org.bean.Menu;
import org.dao.GroupActionDao;
import org.dao.GroupDao;
import org.dao.GroupMenuDao;
import org.dto.ActionDto;
import org.dto.GroupDto;
import org.dto.MenuDto;
import org.service.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private GroupActionDao groupActionDao;
	
	@Autowired
	private GroupMenuDao groupMenuDao;
	
	
	//分配相对应的menu以及action
	public GroupDto selectByGroupId(Integer groupId) {
		//GroupDto里面包含了菜单，动作，各种数据
		GroupDto groupDto=new GroupDto();
		List<MenuDto> menuList=new ArrayList<MenuDto>();
		List<ActionDto> actionList=new ArrayList<ActionDto>();
		groupDto.setActionListDto(actionList);
		groupDto.setMenuListDto(menuList);
		Group groupResult=groupDao.selectById(groupId);
		if(groupResult!=null) {
			for(Menu menu:groupResult.getMenuList()) {
				MenuDto menuDto=new MenuDto();
				BeanUtils.copyProperties(menu, menuDto);
				menuList.add(menuDto);
			}
			
			for(Action action:groupResult.getActionList()) {
				ActionDto actionDto=new ActionDto();
				BeanUtils.copyProperties(action, actionDto);
				actionList.add(actionDto);
			}
		}
		return groupDto;
	}

	@Override
	public List<GroupDto> selectListGroup(Group group) {
		List<GroupDto> list=new ArrayList<GroupDto>();
		List<Group> listGroup=groupDao.selectListGroup(group);
		for(Group groupOne:listGroup) {
			GroupDto groupDto=new GroupDto();
			BeanUtils.copyProperties(groupOne, groupDto);
			groupDto.setpId(0);
			list.add(groupDto);
		}
		return list;
	}

	/*
	 * 新增用戶組
	 */
	public boolean add(GroupDto groupDto) {
		Group group=new Group();
		BeanUtils.copyProperties(groupDto, group);
		int i=groupDao.insert(group);
		return i==1;
	}
	
	/*
	 * 修改用户组
	 */
	public boolean modify(GroupDto groupDto) {
		Group group=new Group();
		BeanUtils.copyProperties(groupDto, group);
		int i=groupDao.update(group);
		return i==1;
	}
	
	/*
	 * 根据id进行删除
	 */
	public boolean delete(Integer id) {
		int i=groupDao.delete(id);
		groupActionDao.deleteByGroupId(id);
		groupMenuDao.deleteByGroupId(id);
		return i==1;
	}
	
	/*
	 * 根据id进行查询
	 */
	public GroupDto selectById(Integer id) {
		GroupDto groupDto=new GroupDto();
		Group group=groupDao.select(id);
		BeanUtils.copyProperties(group, groupDto);
		return groupDto;
	}
	
	/*
	 * 为用户组分配菜单以及动作
	 */
	public boolean assingnMenuAndAction(GroupDto groupDto) {
		groupActionDao.deleteByGroupId(groupDto.getId());
		groupMenuDao.deleteByGroupId(groupDto.getId());
		List<GroupAction> actionList = new ArrayList<GroupAction>();
		List<GroupMenu> menuList = new ArrayList<GroupMenu>();
		// 先将用户组与菜单项先给进行插入
		if (groupDto.getMenuListId() != null && groupDto.getMenuListId().size() > 0) {
			for (Integer menuId : groupDto.getMenuListId()) {
				if(menuId!=null) {
				GroupMenu groupMenu = new GroupMenu();
				groupMenu.setGroupId(groupDto.getId());
				groupMenu.setMenuId(menuId);
				menuList.add(groupMenu);
				}
			}
			groupMenuDao.insertBatch(menuList);
		}
		// 现在写用户组与动作的关系 了
		if (groupDto.getActionListId() != null && groupDto.getActionListId().size() > 0) {
			for (Integer actionId : groupDto.getActionListId()) {
				if(actionId!=null) {
				GroupAction groupAction = new GroupAction();
				groupAction.setActionId(actionId);
				groupAction.setGroupId(groupDto.getId());
				actionList.add(groupAction);
				}
			}
			groupActionDao.insertBatch(actionList);
		}
		return true;
	}

	

	

}
