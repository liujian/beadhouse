package com.beadhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.beadhouse.in.*;
import com.beadhouse.utils.GoogleStorageUtil;
import com.beadhouse.utils.UploadUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.ElderService;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ElderController {


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
        //上传文件至google
        MultipartFile file = UploadUtil.getFile(request);
        String uploadFileName = null;
        if (file != null) {
            uploadFileName = GoogleStorageUtil.uploadFile(file);
            if (uploadFileName == null) {
                return BasicData.CreateErrorMsg("文件上传失败");
            } else {
                param.setElderAvatar(uploadFileName);
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
     * 老人登出
     *
     * @param param
     * @return
     * @throws Exception
     * @author liujian
     * @Date 2018-09-25
     */
    @RequestMapping("elderLogout")
    @ResponseBody
    @Transactional
    public BasicData elderLogout(@Valid @RequestBody TokenParam param) {
        return elderService.elderLogout(param);
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
        //上传文件至google
        MultipartFile file = UploadUtil.getFile(request);
        String uploadFileName = null;
        if (file != null) {
            uploadFileName = GoogleStorageUtil.uploadFile(file);
            if (uploadFileName == null) return BasicData.CreateErrorMsg("文件上传失败");
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
      * 获取等待回答问题
      * @param param
      * @param request
      * @return
      */
      @RequestMapping("elder/getwaitquests")
      @ResponseBody
      @Transactional
      public BasicData getwaitquests(@Valid @RequestBody TokenParam param, HttpServletRequest request){
          return elderService.getWaitQuests(param);
      }


    /**
     * 老人求问题
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("elder/toAskMe")
    @ResponseBody
    @Transactional
    public BasicData toAskMe(@Valid @RequestBody AskMeParam param, HttpServletRequest request){
        return elderService.toAskMe(param);
    }

    /**
     * 获取日程
     * @param request
     * @return
     */
    @RequestMapping("elder/getSchedule")
    @ResponseBody
    public BasicData getSchedule(@Valid @RequestBody TokenParam param, HttpServletRequest request){
        return elderService.getSchedule(param);
    }
    

    /**
     * 获取日程1
     * @param request
     * @return
     */
    @RequestMapping("elder/getSchedule1")
    @ResponseBody
    public BasicData getSchedule1(@Valid @RequestBody TokenParam param, HttpServletRequest request){
        return elderService.getSchedule1(param);
    }

}
