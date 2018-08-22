package org.dao;

import org.bean.Member;

public interface MemberDao {
     Member selectByPhone(Member member);
}
