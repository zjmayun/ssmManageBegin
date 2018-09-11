package org.dto;

import org.bean.Action;

public class ActionDto extends Action{

	@Override
	public String toString() {
		return "ActionDto [getName()=" + getName() + ", getUrl()=" + getUrl() + ", getMethod()=" + getMethod()
				+ ", toString()=" + super.toString() + "]";
	}

	

	
}
