package org.dao;

import java.util.List;

import org.bean.GroupMenu;

public interface GroupMenuDao {
    int deleteByGroupId(Integer groupId);
   
    int insertBatch(List<GroupMenu> list);
}
