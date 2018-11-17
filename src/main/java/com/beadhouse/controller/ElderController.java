package com.beadhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.beadhouse.in.*;
import com.beadhouse.utils.AWSClient;
import com.beadhouse.utils.UploadUtil;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.ElderService;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.InputStream;

@RestController
public class ElderController {

    @Value("${aws.AWS_ACCESS_KEY}")
    private String AWS_ACCESS_KEY;

    @Value("${aws.AWS_SECRET_KEY}")
    private String AWS_SECRET_KEY;

    @Value("${aws.regionName}")
    private String regionName;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Value("${aws.prifix}")
    private String prifix;
    
    @Autowired
    private ElderService elderService;


    /**
     * 老人用户注册
     *
     * @param param
     * @return
     * @throws Exception
     * @author liujian
     * @Date 2018-09-25
     */
    @RequestMapping("elder/registration")
    @ResponseBody
    @Transactional
    public BasicData registration(ElderRegistrationParam param, HttpServletRequest request){
    	 if(param.getElderUserEmail() == null) param = new Gson().fromJson(request.getParameter("json"), ElderRegistrationParam.class);
        //上传头像至aws
        MultipartFile file = UploadUtil.getFile(request);
        String uploadFileName = null;
        File scratchFile = null;
        if (file != null) {
        	try {
                String fileName = file.getOriginalFilename();
                String suffic = fileName.substring(fileName.lastIndexOf("."));
                scratchFile = File.createTempFile("scratchFile", suffic, new File(prifix));  
                InputStream inputStream = file.getInputStream();
                FileUtils.copyInputStreamToFile(inputStream, scratchFile);
                AWSClient.init_with_key(AWS_ACCESS_KEY, AWS_SECRET_KEY, regionName);
                uploadFileName = AWSClient.uploadFileToBucket(scratchFile, AWSClient.getFileName() + suffic, bucketName);
                System.out.println(uploadFileName);
                param.setElderAvatar(uploadFileName);
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (scratchFile!=null&&scratchFile.exists()) {
                    scratchFile.delete();
                }
            }
        }
        return elderService.registration(param);

    }
    
    
    /**
     * 老人用户登陆
     *
     * @param param
     * @return
     * @throws Exception
     * @author liujian
     * @Date 2018-09-25
     */
    @RequestMapping("elderUserLogin")
    @ResponseBody
    @Transactional
    public BasicData elderUserLogin(@Valid @RequestBody LoginParam param, HttpServletRequest request){
        System.out.println("-------------------elderUserLogin------------------");
        return elderService.elderUserLogin(param);
    }
    
    
    
    /**
     * 忘记密码
     *
     * @param param
     * @return
     * @throws Exception
     * @author liujian
     * @Date 2018-11-05
     */
    @RequestMapping("elder/forgetPassword")
    @ResponseBody
    @Transactional
    public BasicData forgetPassword(@Valid @RequestBody NewPasswordParam param, HttpServletRequest request){

        return elderService.forgetPassword(param);
    }
    

    /**
     * 修改密码
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("elder/changePassword")
    @ResponseBody
    @Transactional
    public BasicData changePassword(@Valid @RequestBody ChangePasswordParam param, HttpServletRequest request){
        return elderService.changePassword(param);
    }

    /**
     * 更改老人信息
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("elder/updateUserInfo")
    @ResponseBody
    @Transactional
    public BasicData updateElderInfo(@Valid @RequestBody ElderInfoParam param, HttpServletRequest request){
        return elderService.updateElderInfo(param);
    }

    /**
     * 获得老人信息
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("elder/getUserInfo")
    @ResponseBody
    @Transactional
    public BasicData getElderInfo(@Valid @RequestBody TokenParam param, HttpServletRequest request){
        return elderService.getElderInfo(param);
    }

    /**
     * 更新老人头像
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
   
    @RequestMapping("elder/updateElderAvatar")
    @ResponseBody
    public BasicData updateElderAvatar(TokenParam param, HttpServletRequest request){
        if(param.getToken() == null) param = new Gson().fromJson(request.getParameter("json"), TokenParam.class);
        //上传文件至aws
        MultipartFile file = UploadUtil.getFile(request);
        String uploadFileName = null;
        File scratchFile = null;
        if (file != null) {
        	try {
                String fileName = file.getOriginalFilename();
                String suffic = fileName.substring(fileName.lastIndexOf("."));
                scratchFile = File.createTempFile("scratchFile", suffic, new File(prifix));  
                InputStream inputStream = file.getInputStream();
                FileUtils.copyInputStreamToFile(inputStream, scratchFile);
                AWSClient.init_with_key(AWS_ACCESS_KEY, AWS_SECRET_KEY, regionName);
                uploadFileName = AWSClient.uploadFileToBucket(scratchFile, AWSClient.getFileName() + suffic, bucketName);
                System.out.println(uploadFileName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (scratchFile!=null&&scratchFile.exists()) {
                    scratchFile.delete();
                }
            }
        }
        return elderService.updateElderAvatar(param, uploadFileName);
    }

    /**
     * 获取绑定家人
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("elder/getContacts")
    @ResponseBody
    @Transactional
    public BasicData getElderContacts(@Valid @RequestBody TokenParam param, HttpServletRequest request){
        return elderService.getElderContacts(param);
    }
   
   /**
    * 获取老人端绑定码 
    * @param param
    * @param request
    * @return
    */
    @RequestMapping("elder/getcode")
    @ResponseBody
    @Transactional
    public BasicData getElderCode(@Valid @RequestBody TokenParam param, HttpServletRequest request){
        return elderService.getElderCode(param);
    }
   
    /**
     * 发送验证码至用户邮箱
     * @param param
     * @param request
     * @return
     */
     @RequestMapping("elder/sendcode")
     @ResponseBody
     @Transactional
     public BasicData sendcode(@Valid @RequestBody ElderSendCodeParam param, HttpServletRequest request){
         return elderService.sendCode(param);
     }

     /**
      * 发送验证码至用户邮箱
      * @param param
      * @param request
      * @return
      */
      @RequestMapping("elder/getwaitquests")
      @ResponseBody
      @Transactional
      public BasicData getwaitquests(@Valid @RequestBody TokenParam param, HttpServletRequest request){
          return elderService.getwaitquests(param);
      }

     
}
