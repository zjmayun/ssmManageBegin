package org.controller.system;

import java.util.List;

import org.bean.Menu;
import org.constant.PageCodeEnum;
import org.dao.MenuDao;
import org.dto.GroupDto;
import org.dto.MenuDto;
import org.dto.MenuForMoveDto;
import org.dto.MenuForZtreeDto;
import org.dto.PageCodeDto;
import org.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/menus")
public class MenusController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<MenuForZtreeDto> listMenus(){
		return menuService.selectMenuWithAction(new Menu());
	}
	
	/*
	 * 添加菜单组
	 */
	@RequestMapping(method=RequestMethod.POST)
	public PageCodeDto add(MenuDto menuDto){
		PageCodeDto pageDto;
		if(menuService.add(menuDto)) {
			pageDto=new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
		}else {
			pageDto=new PageCodeDto(PageCodeEnum.ADD_FAIL);
		}
		return pageDto;
	}
	
	/*
	 * 初始化修改菜单组界面
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public MenuDto init(@PathVariable(value="id")Integer id){
         return menuService.select(id);		
	}
	
	/*
	 * 修改菜单组
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public PageCodeDto modify(MenuDto menuDto){
        PageCodeDto pageDto;
        if(menuService.modify(menuDto)) {
        	pageDto=new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        }else {
        	pageDto=new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return pageDto;
	}
	
	/*
	 * 菜单排序
	 */
	@RequestMapping(value="/{dropNodeId}/{targetNodeId}/{moveType}",method=RequestMethod.PUT)
	public PageCodeDto sort(MenuForMoveDto menuForMoveDto){
        PageCodeDto pageDto;
        if(menuService.sortMenu(menuForMoveDto)) {
        	pageDto=new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        }else {
        	pageDto=new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return pageDto;
	}
	
	
	/*
	 * 根据id删除菜单组
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public PageCodeDto delete(MenuDto menuDto){
        PageCodeDto pageDto;
        if(menuService.delete(menuDto.getId())) {
        	pageDto=new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        }else {
        	pageDto=new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
        }
        return pageDto;
	}
	
	
	
}
