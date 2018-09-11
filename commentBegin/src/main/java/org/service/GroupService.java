package org.service;

import java.util.List;

import org.bean.Group;
import org.dto.GroupDto;

public interface GroupService {
    GroupDto selectByGroupId(Integer groupId);
    
    List<GroupDto> selectListGroup(Group group);
    
    boolean add(GroupDto groupDto);
    
    boolean modify(GroupDto groupDto);
    
    boolean delete(Integer id);
    
    GroupDto selectById(Integer id);
    
    boolean assingnMenuAndAction(GroupDto groupDto);
}
