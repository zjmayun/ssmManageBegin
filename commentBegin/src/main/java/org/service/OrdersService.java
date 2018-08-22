package org.service;

import java.util.List;

import org.bean.Orders;
import org.dto.OrdersDto;


public interface OrdersService {
     List<OrdersDto> selectByMemberId(int id);
     
     boolean insert(OrdersDto ordersDto);
     
     boolean update(OrdersDto ordersDto);
}
