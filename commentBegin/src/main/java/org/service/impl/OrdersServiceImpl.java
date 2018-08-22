package org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bean.Business;
import org.bean.Orders;
import org.constant.CommentState;
import org.dao.BusinessDao;
import org.dao.OrdersDao;
import org.dto.OrdersDto;
import org.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private BusinessDao businessDao;
	
	@Value("${businessImage.url}")
	private String  businessImageUrl;
	
	public List<OrdersDto> selectByMemberId(int id) {
		List<Orders> list=ordersDao.selectByMemberId(id);
		List<OrdersDto> listDto=new ArrayList<OrdersDto>();
		for(Orders orders:list) {
			OrdersDto ordersDto=new OrdersDto();
			BeanUtils.copyProperties(orders, ordersDto);
			Business business=businessDao.selectById(orders.getBusinessId());
			ordersDto.setImg(businessImageUrl+business.getImgFileName());
			ordersDto.setTitle(business.getTitle());
			ordersDto.setCount(orders.getNum());
			listDto.add(ordersDto);
		}
		return listDto;
	}

	public boolean insert(OrdersDto ordersDto) {
        Orders orders=new Orders();
        BeanUtils.copyProperties(ordersDto, orders);
        //默认新增的订单为未评论
        orders.setCommentState(CommentState.COMMENT_NO_STATUS);
        int i=ordersDao.insert(orders);
		return i==1;
	}

	public boolean update(OrdersDto ordersDto) {
        Orders orders=new Orders();
        BeanUtils.copyProperties(ordersDto, orders);
        orders.setCommentState(CommentState.COMMENT_STATUS);
        int i=ordersDao.update(orders);
		return i==1;
	}


}
