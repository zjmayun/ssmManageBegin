package org.dao;

import java.util.List;

import org.bean.Ad;

public interface AdDao {
    List<Ad> selectListByPage(Ad ad);
    
    int add(Ad ad);
    
    int insert(Ad ad);
    
    int update(Ad ad);
    
    Ad selectById(int id);
    
    int delete(int id);
}
