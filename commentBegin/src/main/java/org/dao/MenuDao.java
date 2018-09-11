package org.dao;

import java.util.List;

import org.bean.Menu;

public interface MenuDao {
    List<Menu> selectListMenu();
    
    Menu selectById(Menu menu);
    
    int insert(Menu menu);
    
    int modify(Menu menu);
    
    int delete(int id);
    
    List<Menu> selectMenuWithAction(Menu menu);
    
    //成为目标节点的子节点
    int updateOrderNumInner(Integer parentId);
    
    //成为目标节点的兄弟节点，放置在前面
    int updateOrderNumPrev(Integer id);
    
    //成为目标节点的兄弟节点，放置在后面
    int updateOrderNumNext(Integer id);
    
}
