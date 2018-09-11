package org.dto;

import org.bean.Comment;

public class CommentDto extends Comment{
	//隐藏4位数字的手机号码
	private String username;

	public String getUsername() {
		return username;
	}

    public void setUsername(String username) {
		this.username = username;
	}
     
     
}
