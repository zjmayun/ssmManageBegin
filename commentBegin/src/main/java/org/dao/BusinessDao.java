package org.dao;

import java.util.List;

import org.bean.Ad;
import org.bean.Business;

public interface BusinessDao {
     Business selectById(int id);
     
     List<Business> selectByPage(Business business);
     
     int delete(int id);
     
     int add(Business business);
     
     int update(Business business);
     
     List<Business> selectLikeByPage(Business business);
     
}
