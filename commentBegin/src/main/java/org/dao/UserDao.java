package org.dao;

import java.util.List;

import org.bean.User;

public interface UserDao {
    User selectByNameAndPass(User user);
    
    List<User> selectListUser();
    
    User selectById(int id);
    
    int insert(User user);
    
    int modify(User user);
    
    int delete(int id);
}
