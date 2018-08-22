package org.cache;

import java.util.HashMap;
import java.util.Map;

public class CodeCache {
     private Map<String,String> map;
     private static CodeCache instance;
 	
     private CodeCache() {
    	 map=new HashMap<String, String>();
     }

     public static CodeCache getInstance() {
    	 if(instance==null) {
    		 synchronized (CodeCache.class) {
    			 if(instance==null)
			       instance=new CodeCache();	
			 }
    	 }
    	 return instance;
     }
     
     public boolean saveCode(String phone,String value) {
         	 if(map.containsKey(phone)) {
         		 return false;
         	 }
         	 map.put(phone, value);
         	 return true;
     }
     
     public String getCode(String phone) {
    	 return map.get(phone);
     }
     
     
     
     
     
     
     
}
