package org.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bean.Ad;
import org.bean.Business;

public interface BusinessDao {
     Business selectById(int id);
     
     List<Business> selectByPage(Business business);
     
     int delete(int id);
     
     int add(Business business);
     
     int update(Business business);
     
     List<Business> selectLikeByPage(Business business);
     
     //根据时间段更新星星总数 
     int updateStar(Map<String,Date> map);
     
     int updateBusinessNum(Map<String,Date> map);
}
