package org.util;

import java.io.File;


public class FileUtil {
    public static File getFile(String fileName,String savePath) {
             File fileR=new File(savePath+fileName);
             if(fileR.isDirectory()) {
          	   fileR.mkdirs();
             }
          	 return fileR;
    }
}
