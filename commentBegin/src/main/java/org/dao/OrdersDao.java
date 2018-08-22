package org.dao;

import java.util.List;

import org.bean.Orders;

public interface OrdersDao {
	int insert(Orders orders);
	
	int update(Orders orders);
	
	List<Orders> selectByMemberId(Integer memberId);
	
	
}
