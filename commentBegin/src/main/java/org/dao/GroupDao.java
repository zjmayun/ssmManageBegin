package org.dao;


import java.util.List;

import org.bean.Group;

public interface GroupDao {
    Group selectById(Integer groupId);
    
    int update(Group group);
    
    int delete(int id);
    
    int insert(Group group);
    
    Group select(Integer id);
    
    /*
     * 有扩展，可以通过name来进行查询
     */
    List<Group> selectListGroup(Group group);
}
