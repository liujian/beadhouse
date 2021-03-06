package com.beadhouse.utils;

import com.google.cloud.storage.*;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *  * 
 *  * @author lin
 *  *
 *  
 */
public class GoogleStorageUtil {
    // 存储空间
    private final static String bucketName = "rootz-media-bucket";

    //服务
    private static Storage storage = null;

    static {
        storage = StorageOptions.getDefaultInstance().getService();
    }

    /**
     * 上传图片到Google云存储
     *
     * @param file : 文件
     * @throws IOException
     * @return 
     */
    @SuppressWarnings("deprecation")
    public static String uploadFile(MultipartFile file) {
        final String fileName = getFileName() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
        System.err.println("fileName:" + fileName);
        //上传文件 BlobInfo blobInfo =
        try {
            storage.create(BlobInfo.newBuilder(bucketName, fileName)
                    // Modify access list to allow all users with link to read file
                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
                    .setContentType("image/jpeg").build(), file.getInputStream());
        } catch (IOException e) {
            return null;
        }
        return fileName;
    }

    /**
     * 上传图片到Google云存储
     *
     * @param file : 文件
     * @throws IOException
     * @return 
     */
    @SuppressWarnings("deprecation")
    public static BlobInfo uploadFile(File file) {
        String type = ".mp4";
        if (file.getName().endsWith(".amr")) type = ".amr";
        final String fileName = getFileName() + type;
        System.err.println("fileName:" + fileName);
        //上传文件 
        BlobInfo blobInfo;
        try {
            storage.create(blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                    // Modify access list to allow all users with link to read file
                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
                    .setContentType(type.equals(".mp4") ? "video/mp4" : "application/octet-stream").build(), new FileInputStream(file));
//            storage.delete(blobInfo.getBlobId());
        } catch (IOException e) {
            return null;
        }
        return blobInfo;
    }

    private static String getFileName() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = df.format(new Date());
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            format += r.nextInt(10);
        }
        System.out.println(format);
        return format;
    }

    public static void deleteFile(BlobInfo newBlobInfo) {
        storage.delete(newBlobInfo.getBlobId());
    }

    public static void deleteFile(String name) {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, name).build();
        storage.delete(blobInfo.getBlobId());
    }

}
