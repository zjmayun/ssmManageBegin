package org.service;

import org.bean.Member;

public interface MemberService {
     boolean memberExistsByPhone(String phone);   
     
     boolean saveCode(String phone,String code); 
     
     String getCode(String phone);
     
     void sendCode(String code);
     
     boolean saveToken(String phone,String token);
     
     String getToken(String phone);
     
     Member selectByPhone(String phone);
}
