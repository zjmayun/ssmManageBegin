package org.dao;

import org.bean.SysParam;

public interface SysParamDao {
	SysParam selectByKey(String paramKey);
	
	int update(SysParam sysParam);
}
