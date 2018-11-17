package com.beadhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.beadhouse.in.*;
import com.beadhouse.utils.AWSClient;
import com.beadhouse.utils.UploadUtil;
import com.beadhouse.utils.Utils;
import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beadhouse.out.BasicData;
import com.beadhouse.out.GetCodeResult;
import com.beadhouse.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RestController
public class UserController {

    @Value("${aws.AWS_ACCESS_KEY}")
    private String AWS_ACCESS_KEY;

    @Value("${aws.AWS_SECRET_KEY}")
    private String AWS_SECRET_KEY;

    @Value("${aws.regionName}")
    private String regionName;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Autowired
    private UserService userService;


    /**
     * 用户注册
     *
     * @param param
     * @return
     * @throws Exception
     * @author liujian
     * @Date 2018-09-25
     */
    @RequestMapping("registration")
    @ResponseBody
    @Transactional
    public BasicData registration(@Valid @RequestBody RegistrationParam param, HttpServletRequest request){

        return userService.registration(param);
    }
    
    /**
     * 获取邮箱验证码
     * @param param
     * @return
     */
    @RequestMapping("getCode")
    @ResponseBody
    public BasicData getCode(@Valid @RequestBody GetCodeParam param){
    	BasicData result = new BasicData();
		String code =Utils.getRandNum(6);
		GetCodeResult data = new GetCodeResult();
		data.setFlag(this.userService.sendCode(param,code));
		result.setData(data);
    	return result;
    }
    


    /**
     * 用户登陆
     *
     * @param param
     * @return
     * @throws Exception
     * @author liujian
     * @Date 2018-09-25
     */
    @RequestMapping("userLogin")
    @ResponseBody
    @Transactional
    public BasicData userLogin(@Valid @RequestBody LoginParam param, HttpServletRequest request){

        return userService.login(param);
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
    @RequestMapping("forgetPassword")
    @ResponseBody
    @Transactional
    public BasicData forgetPassword(@Valid @RequestBody NewPasswordParam param, HttpServletRequest request){

        return userService.forgetPassword(param);
    }
    


    /**
     * 关联联系人
     *
     * @param param
     * @return
     * @throws Exception
     * @author liujian
     * @Date 2018-09-25
     */
    @RequestMapping("bindingContacts")
    @ResponseBody
    @Transactional
    public BasicData bindingContacts(@Valid @RequestBody ContactsParam param, HttpServletRequest request){

        return userService.bindingContacts(param);
    }

    /**
     * 修改密码
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("changePassword")
    @ResponseBody
    @Transactional
    public BasicData changePassword(@Valid @RequestBody ChangePasswordParam param, HttpServletRequest request){
        return userService.changePassword(param);
    }

    /**
     * 更新用户信息
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    @Transactional
    public BasicData updateUserInfo(@Valid @RequestBody UserInfoParam param, HttpServletRequest request){
        return userService.updateUserInfo(param);
    }

    /**
     * 获得用户信息
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("getUserInfo")
    @ResponseBody
    @Transactional
    public BasicData getUserInfo(@Valid @RequestBody TokenParam param, HttpServletRequest request){
        return userService.getUserInfo(param);
    }

    /**
     * 更新用户头像
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @Value("${aws.prifix}")
    private String prifix;
    @RequestMapping("updateUserAvatar")
    @ResponseBody
    public BasicData updateUserAvatar(TokenParam param, HttpServletRequest request){
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
        return userService.updateUserAvatar(param, uploadFileName);
    }

    /**
     * 检查token是否过期
     */
    @RequestMapping("checkToken")
    @ResponseBody
    public BasicData checkToken(@RequestParam String token, HttpServletRequest request){

        return BasicData.CreateErrorInvalidUser();
    }
   

}
