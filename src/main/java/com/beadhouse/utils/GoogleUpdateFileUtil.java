package com.beadhouse.utils;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GoogleUpdateFileUtil {
	// 存储空间
	private final static String bucketName = "liujian";

	//服务
	private static Storage storage = null;
	//图片的存储路径
	private static String imgUrl = null;
	static {
	    storage = StorageOptions.getDefaultInstance().getService();
	 }
	
	/**
	* 上传图片到Google云存储
	* @param file : 文件
	* @param prefix : 文件名前缀 
	* @return 
	* @throws IOException
	*/
	@SuppressWarnings("deprecation")
	public static String uploadFile(MultipartFile file,String prefix) throws IOException   {
		  //文件名
		DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYYMMddHHmmss");
		DateTime dt = DateTime.now(DateTimeZone.UTC);
		String dtString = dt.toString(dtf);
		final String fileName =prefix + dtString+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),file.getOriginalFilename().length());
		System.err.println("fileName:" + fileName);
		//上传文件 
		BlobInfo blobInfo =
				 storage.create(
						 BlobInfo.newBuilder(bucketName, fileName)
						// Modify access list to allow all users with link to read file
						 .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))  
						 .setContentType("image/jpeg").build(),  file.getInputStream());
		 //System.out.println("下载连接："+blobInfo.getMediaLink());
		 imgUrl = "https://storage.googleapis.com/" + bucketName + "/" + fileName;
		 return imgUrl;
	}
}
