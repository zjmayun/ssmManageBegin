package org.dao;

import java.util.List;

import org.bean.Comment;

public interface CommentDao {
    boolean insert(Comment comment);
    
    List<Comment> selectByPage(Comment comment);
}
