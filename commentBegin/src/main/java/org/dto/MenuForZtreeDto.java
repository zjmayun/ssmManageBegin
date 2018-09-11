package org.dto;

public class MenuForZtreeDto {
	private Integer id;
	private Integer parentId;
	private String name;
	//定义树是否展开
	private boolean open;
	private String comboId;
	private String comboParentId;
    
	/*
	 * 菜单表前缀
	 */
	public static final String PREFIX_MENU="MENU_";
	
	/*
	 * 动作表前缀
	 */
	public static final String PREFIX_ACTION="ACTION_";
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getComboId() {
		return comboId;
	}

	public void setComboId(String comboId) {
		this.comboId = comboId;
	}

	public String getComboParentId() {
		return comboParentId;
	}

	public void setComboParentId(String comboParentId) {
		this.comboParentId = comboParentId;
	}

	
    
    
}
