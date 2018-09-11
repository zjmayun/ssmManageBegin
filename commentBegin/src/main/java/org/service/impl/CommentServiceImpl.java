package org.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bean.Business;
import org.bean.Comment;
import org.bean.Member;
import org.bean.Orders;
import org.bean.Page;
import org.constant.CommentState;
import org.dao.CommentDao;
import org.dao.OrdersDao;
import org.dto.CommentDto;
import org.dto.CommentForBuyDto;
import org.dto.CommentListDto;
import org.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private OrdersDao ordersDao;

	public boolean insert(CommentForBuyDto commentForBuyDto) {
		// 进行数据的迁移
		Comment comment = new Comment();
		comment.setCreateTime(new Date());
		comment.setComment(commentForBuyDto.getComment());
		comment.setStar(commentForBuyDto.getStar());
		comment.setOrdersId(commentForBuyDto.getId());
		commentDao.insert(comment);
        
		// 此时订单已经被评论，需要更改评论的状态comment_state
		Orders order = new Orders();
		order.setId(commentForBuyDto.getId());
		Orders order1 = ordersDao.selectById(order);
		order1.setCommentState(CommentState.COMMENT_STATUS);
		int i = ordersDao.update(order1);
		return i == 1;
	}

	@Override
	public CommentListDto getCommentList(Integer businessId, Page page) {
		CommentListDto dto = new CommentListDto();
		Comment comment = new Comment();
		Business business = new Business();
		Orders orders = new Orders();
		comment.setOrders(orders);
		orders.setBusiness(business);
		business.setId(businessId);
		page.setCurrentPage(page.getCurrentPage() + 1);
		comment.setPage(page);
		// 关键是评论列表要显示用户的手机号，而且评论是和用户挂钩的，所以表的连接挺多的
		List<Comment> list = commentDao.selectByPage(comment);
		List<CommentDto> listDto = new ArrayList<CommentDto>();
		for (Comment comment1 : list) {
			CommentDto comDto = new CommentDto();
			BeanUtils.copyProperties(comment1, comDto);
			StringBuffer sbf = new StringBuffer(comment1.getOrders().getMember().getPhone());
			comDto.setUsername(String.valueOf(sbf.replace(3, 7, "****")));
			listDto.add(comDto);
		}
		dto.setData(listDto);
		dto.setHasmore(comment.getPage().getCurrentPage() < comment.getPage().getTotalPage());
		return dto;
	}

}
