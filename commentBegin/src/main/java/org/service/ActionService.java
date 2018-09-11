package org.service;

import java.util.List;

import org.dto.ActionDto;

public interface ActionService {
	List<ActionDto> getActionList();
	
	ActionDto getActionById(Integer id);
	
	boolean add(ActionDto actionDto);
	
	boolean delete(Integer id);
	
	boolean modify(ActionDto actionDto);
}
