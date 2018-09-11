package org.dto;

import java.util.List;

public class CommentListDto {
	//评论列表数据显示关键数据
	private boolean hasmore;
	private List<CommentDto> data;

	public boolean isHasmore() {
		return hasmore;
	}

	public void setHasmore(boolean hasmore) {
		this.hasmore = hasmore;
	}

	public List<CommentDto> getData() {
		return data;
	}

	public void setData(List<CommentDto> data) {
		this.data = data;
	}

	
    
}
