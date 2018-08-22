package org.cache;

import java.util.HashMap;
import java.util.Map;

public class TokenCache {
     private Map<String,String> map;
     private static TokenCache instance;
 	 
     private TokenCache() {
    	 map=new HashMap<String, String>();
     }

     public static TokenCache getInstance() {
    	 if(instance==null) {
    		 synchronized (CodeCache.class) {
    			 if(instance==null)
			       instance=new TokenCache();	
			 }
    	 }
    	 return instance;
     }
     
     public boolean saveToken(String phone,String token) {
         	 if(map.containsKey(phone)) {
         		 return false;
         	 }
         	 map.put(phone, token);
         	 return true;
     }
     
     public String getToken(String phone) {
    	 return map.get(phone);
     }
     
     
     
     
     
     
     
}
