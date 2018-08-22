package org.service;

import java.util.List;

import org.bean.Ad;
import org.dto.AdDto;

public interface AdService {
    List<AdDto> selectByPage(AdDto adDto);
    
    AdDto selectById(int id);
    
    boolean delete(int id);
    
    boolean update(AdDto adDto);
    
    boolean add(AdDto adDto);
}
