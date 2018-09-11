package org.dto;

import java.util.List;

import org.bean.Group;

public class GroupDto extends Group{
	private Integer pId;

	private List<Integer> actionListId;
	private List<Integer> menuListId;
	private List<ActionDto> actionListDto;
	private List<MenuDto> menuListDto;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public List<Integer> getActionListId() {
		return actionListId;
	}

	public void setActionListId(List<Integer> actionListId) {
		this.actionListId = actionListId;
	}

	public List<Integer> getMenuListId() {
		return menuListId;
	}

	public void setMenuListId(List<Integer> menuListId) {
		this.menuListId = menuListId;
	}

	public List<ActionDto> getActionListDto() {
		return actionListDto;
	}

	public void setActionListDto(List<ActionDto> actionListDto) {
		this.actionListDto = actionListDto;
	}

	public List<MenuDto> getMenuListDto() {
		return menuListDto;
	}

	public void setMenuListDto(List<MenuDto> menuListDto) {
		this.menuListDto = menuListDto;
	}
    
}
