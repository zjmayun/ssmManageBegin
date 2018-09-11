package org.dao;

import java.util.List;

import org.bean.GroupAction;

public interface GroupActionDao {
    int deleteByGroupId(Integer groupId);
 
    int insertBatch(List<GroupAction> list);
}
