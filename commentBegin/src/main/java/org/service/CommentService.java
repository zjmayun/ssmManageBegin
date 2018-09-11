package org.service;

import java.util.List;

import org.bean.Page;
import org.dto.CommentForBuyDto;
import org.dto.CommentListDto;

public interface CommentService {
	//CommentForBuyDto是前台传过来的，service中进行格式转换
	boolean insert(CommentForBuyDto commentForBuyDto);
	
	
	CommentListDto getCommentList(Integer businessId,Page page);
}
