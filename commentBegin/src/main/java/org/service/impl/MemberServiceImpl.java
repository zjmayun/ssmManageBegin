package org.service.impl;

import org.bean.Member;
import org.cache.CodeCache;
import org.cache.TokenCache;
import org.dao.MemberDao;
import org.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	private final static Logger logger = LoggerFactory
			.getLogger(MemberService.class);
	private CodeCache codeCache;
	private TokenCache tokenCache;
	
	public boolean memberExistsByPhone(String phone) {
        Member member=new Member();
        member.setPhone(phone);
		return memberDao.selectByPhone(member)==null?false:true;
	}

	public boolean saveCode(String phone,String code) {
	    codeCache=CodeCache.getInstance();
        return codeCache.saveCode(phone, code);		
	}
	
	public String getCode(String phone) {
		String code=codeCache.getCode(phone);
		return code;
	}
	
	public boolean saveToken(String phone,String token) {
		tokenCache=TokenCache.getInstance();
		return tokenCache.saveToken(phone, token);
	}
	
	public String getToken(String phone) {
		return tokenCache.getToken(phone);
	}

	@Override
	public void sendCode(String code) {
        System.out.println(code);		
	}

	@Override
	public Member selectByPhone(String phone) {
        Member member=new Member();
        member.setPhone(phone);
        Member memberS=memberDao.selectByPhone(member); 
		return memberS;
	}
}
