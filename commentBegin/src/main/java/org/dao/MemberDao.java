package org.dao;

import org.bean.Member;

public interface MemberDao {
     Member selectByPhone(Member member);
     
     int selectById(Member member);
     
     int insert(Member member);
     
     int modify(Member member);
     
     int delete(int id);
}
