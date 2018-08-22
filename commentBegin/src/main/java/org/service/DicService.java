package org.service;

import java.util.List;

import org.bean.Ad;
import org.bean.Dic;
import org.dto.AdDto;

public interface DicService {
    List<Dic> selectByType(String dic);
}
