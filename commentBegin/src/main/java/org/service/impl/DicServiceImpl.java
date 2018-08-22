package org.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bean.Ad;
import org.bean.Dic;
import org.dao.AdDao;
import org.dao.DicDao;
import org.dto.AdDto;
import org.service.AdService;
import org.service.DicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.util.FileUtil;

@Service
public class DicServiceImpl implements DicService{

	@Autowired
	private DicDao dicDao;
	
	

	@Override
	public List<Dic> selectByType(String dic) {
		Dic dicO=new Dic();
		dicO.setType(dic);
		return dicDao.selectByListType(dicO);
	}

	

}
