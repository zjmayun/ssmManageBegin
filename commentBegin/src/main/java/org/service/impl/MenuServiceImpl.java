package org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bean.Action;
import org.bean.Menu;
import org.dao.MenuDao;
import org.dto.MenuDto;
import org.dto.MenuForMoveDto;
import org.dto.MenuForZtreeDto;
import org.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	/*
	 * 根据前端js中需要的属性，自己进行组装
	 * @see org.service.MenuService#selectMenuWithAction(org.bean.Menu)
	 */
	public List<MenuForZtreeDto> selectMenuWithAction(Menu menu) {
        List<MenuForZtreeDto> list=new ArrayList<MenuForZtreeDto>();
        List<Menu> menuList=menuDao.selectMenuWithAction(menu);
        for(Menu menu1:menuList) {
        	MenuForZtreeDto menuTreeDto=new MenuForZtreeDto();
        	menuTreeDto.setId(menu1.getId());
        	menuTreeDto.setName(menu1.getName());
        	menuTreeDto.setOpen(true);
        	menuTreeDto.setParentId(menu1.getParentId());
        	menuTreeDto.setComboId(MenuForZtreeDto.PREFIX_MENU+menu1.getId());
        	menuTreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU+menu1.getParentId());
        	list.add(menuTreeDto);
        	for(Action action:menu1.getActionList()) {
        		MenuForZtreeDto actionTreeDto=new MenuForZtreeDto();
        		actionTreeDto.setId(action.getId());
        		actionTreeDto.setName(action.getName());
        		actionTreeDto.setComboId(MenuForZtreeDto.PREFIX_ACTION+action.getId());
        		actionTreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU+action.getMenuId());
        		actionTreeDto.setParentId(action.getMenuId());
        		list.add(actionTreeDto);
        	}
        	
        }
		return list;
	}
	
	/*
	 * 新增菜单 
	 */
	public boolean add(MenuDto menuDto) {
		Menu menu=new Menu();
		BeanUtils.copyProperties(menuDto, menu);
		int i=menuDao.insert(menu);
		return i==1;
	}
	
	/*
	 * 删除菜单
	 */
	public boolean delete(Integer id) {
		int i=menuDao.delete(id);
		return i==1;
	}
	
	/*
	 * 查询菜单项
	 */
	public MenuDto select(Integer id) {
		MenuDto menuDto=new MenuDto();
		Menu menu1=new Menu();
		menu1.setId(id);
		Menu menu=menuDao.selectById(menu1);
		BeanUtils.copyProperties(menu, menuDto);
		return menuDto;
	}
	
	/*
	 * 修改菜单项
	 */
	public boolean modify(MenuDto menuDto) {
		Menu menu=new Menu();
		BeanUtils.copyProperties(menuDto, menu);
		int i=menuDao.modify(menu);
		return i==1;
	}
	
	/*
	 * 菜单排序，搞完这个功能我可以养老了。。。 
	 */
    public boolean sortMenu(MenuForMoveDto moveDto) {
    	
		if (moveDto != null) {
			if (moveDto.getMoveType().equals(MenuForMoveDto.MOVE_TYPE_INNER)) {
				menuDao.updateOrderNumInner(moveDto.getTargetNodeId());
				Menu menu = new Menu();
				menu.setOrderNum(1);
				menu.setParentId(moveDto.getTargetNodeId());
				menu.setId(moveDto.getDropNodeId());
				menuDao.modify(menu);
			} else if (moveDto.getMoveType().equals(MenuForMoveDto.MOVE_TYPE_PREV)) {
				menuDao.updateOrderNumPrev(moveDto.getTargetNodeId());
				Menu menuPrev = new Menu();
				menuPrev.setId(moveDto.getTargetNodeId());
				Menu menuTarget = menuDao.selectById(menuPrev);
				menuPrev.setOrderNum(menuTarget.getOrderNum());
				menuPrev.setId(moveDto.getDropNodeId());
				menuPrev.setParentId(menuTarget.getParentId());
				menuDao.modify(menuPrev);
			} else if (moveDto.getMoveType().equals(MenuForMoveDto.MOVE_TYPE_NEXT)) {
				menuDao.updateOrderNumNext(moveDto.getTargetNodeId());
				Menu menuNext = new Menu();
				menuNext.setId(moveDto.getTargetNodeId());
				Menu menuTarget = menuDao.selectById(menuNext);
				menuNext.setOrderNum(menuTarget.getOrderNum() + 1);
				menuNext.setId(moveDto.getDropNodeId());
				menuNext.setParentId(menuTarget.getParentId());
				menuDao.modify(menuNext);
			}
		} else {
			return false;
		}

		return true;
    }
	
	
}
