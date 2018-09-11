package org.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bean.SysParam;
import org.constant.SysParamKeyConst;
import org.dao.BusinessDao;
import org.dao.SysParamDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("BusinessTask")
public class BusinessTask {
    
	@Autowired
	private BusinessDao businessDao;
	
	@Autowired
	private SysParamDao sysParamDao;
	
	private static final Logger logger=LoggerFactory.getLogger(BusinessTask.class);
	
	public void synNumber() {
		logger.info("synNumber start");
		SysParam sysParam=sysParamDao.selectByKey(SysParamKeyConst.LAST_SYNC_NUM_TIME);
		Map<String,Date> map=new HashMap<String,Date>();
		map.put("startTime", sysParam.getParamValue());
		Date date=new Date();
		map.put("endTime", date);
		int i=businessDao.updateBusinessNum(map);
		SysParam sysParamUpdate=new SysParam();
		sysParamUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_NUM_TIME);
		sysParamUpdate.setParamValue(date);
		int j=sysParamDao.update(sysParamUpdate);
		logger.info("synNumber end");
	}
	
	public void synStar() {
		logger.info("synStar start");
		SysParam sysParam=sysParamDao.selectByKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
		Map<String,Date> map=new HashMap<String,Date>();
		map.put("starTime", sysParam.getParamValue());
		Date date=new Date();
		map.put("endTime", date);
		int i=businessDao.updateStar(map);
		SysParam sysParamUpdate=new SysParam();
		sysParamUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
		sysParamUpdate.setParamValue(date);
		int j=sysParamDao.update(sysParamUpdate);
		logger.info("synStar end");
		
	}
}
