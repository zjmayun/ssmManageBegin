package org.dao;

import java.util.List;

import org.bean.Action;

public interface ActionDao {
   Action selectById(Action action);
   
   List<Action> select();
   
   int insert(Action action);
   
   int delete(Integer id);
   
   int modify(Action action);
   
}
