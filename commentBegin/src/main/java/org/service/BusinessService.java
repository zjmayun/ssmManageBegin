package org.service;

import java.util.List;

import org.bean.Ad;
import org.dto.AdDto;
import org.dto.BusinessDto;
import org.dto.BusinessListDto;

public interface BusinessService {
    List<BusinessDto> selectByPage(BusinessDto BusDto);
    
    BusinessDto selectById(int id);
    
    boolean delete(int id);
    
    boolean update(BusinessDto businessDto);
    
    boolean add(BusinessDto businessDto);
    
    BusinessListDto selectByPageForApi(BusinessDto businessDto);
}
