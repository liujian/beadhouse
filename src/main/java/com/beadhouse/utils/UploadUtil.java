package com.beadhouse.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Camile on 2017/10/26.
 */
public class UploadUtil {

    public static MultipartFile getFile(HttpServletRequest request) {
    	MultipartFile file = null;
    	if(request instanceof MultipartHttpServletRequest){
    		  MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
    		  if(req!=null){
    			  Iterator<String> iter = req.getFileNames();
      	        if (iter.hasNext()) {
      	            String fileName = iter.next();
      	            file = req.getFile(fileName);
      	        }
    		  }        
    	}
        return file;
    }

   
}
