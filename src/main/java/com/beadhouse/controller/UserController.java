package com.beadhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.beadhouse.in.*;
import com.beadhouse.utils.GoogleStorageUtil;
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
import java.io.InputStream;

@RestController
public class UserController {

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
    public BasicData registration(@Valid @RequestBody RegistrationParam param, HttpServletRequest request) {

        return userService.registration(param);
    }

    /**
     * 获取邮箱验证码
     *
     * @param param
     * @return
     */
    @RequestMapping("getCode")
    @ResponseBody
    public BasicData getCode(@Valid @RequestBody GetCodeParam param) {
        BasicData result = new BasicData();
        String code = Utils.getRandNum(6);
        GetCodeResult data = new GetCodeResult();
        data.setFlag(this.userService.sendCode(param, code));
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
    public BasicData userLogin(@Valid @RequestBody LoginParam param, HttpServletRequest request) {
        return userService.login(param);
    }

    /**
     * 用户登出
     *
     * @param param
     * @return
     * @throws Exception
     * @author liujian
     * @Date 2018-09-25
     */
    @RequestMapping("userLogout")
    @ResponseBody
    @Transactional
    public BasicData userLogout(@Valid @RequestBody TokenParam param, HttpServletRequest request) {
        return userService.logout(param);
    }


    /**
     * Google Facebook 登录
     * @param param
     * @param request
     * @return
     */
    @RequestMapping("loginGoogleOrFacebook")
    @ResponseBody
    @Transactional
    public BasicData loginGoogleOrFacebook(@Valid @RequestBody GoogleAndFacebookParam param, HttpServletRequest request) {
        return userService.loginGoogleOrFacebook(param);
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
    public BasicData forgetPassword(@Valid @RequestBody NewPasswordParam param, HttpServletRequest request) {

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
    public BasicData bindingContacts(@Valid @RequestBody ContactsParam param, HttpServletRequest request) {

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
    public BasicData changePassword(@Valid @RequestBody ChangePasswordParam param, HttpServletRequest request) {
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
    public BasicData updateUserInfo(@Valid @RequestBody UserInfoParam param, HttpServletRequest request) {
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
    public BasicData getUserInfo(@Valid @RequestBody TokenParam param, HttpServletRequest request) {
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

    @RequestMapping("updateUserAvatar")
    @ResponseBody
    public BasicData updateUserAvatar(TokenParam param, HttpServletRequest request) {
        if (param.getToken() == null) param = new Gson().fromJson(request.getParameter("json"), TokenParam.class);
        //上传文件至google
        MultipartFile file = UploadUtil.getFile(request);
        String uploadFileName = null;
        if (file != null) {
            uploadFileName = GoogleStorageUtil.uploadFile(file);
            if (uploadFileName == null) return BasicData.CreateErrorMsg("File upload fail");
        }
        return userService.updateUserAvatar(param, uploadFileName);
    }


    /**
     * 上传老人屏保
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("uploadElderScreen")
    @ResponseBody
    public BasicData uploadElderScreen(TokenParam param, HttpServletRequest request) {
        if (param.getToken() == null) param = new Gson().fromJson(request.getParameter("json"), TokenParam.class);
        //上传文件至google
        MultipartFile file = UploadUtil.getFile(request);
        String uploadFileName = null;
        if (file != null) {
            System.out.println(file.getName());
            uploadFileName = GoogleStorageUtil.uploadFile(file);
            if (uploadFileName == null) return BasicData.CreateErrorMsg("File upload fail");
        }
        return userService.uploadElderScreen(param, uploadFileName);
    }

    /**
     * 获取老人屏保
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("getElderScreenList")
    @ResponseBody
    public BasicData getElderScreenList(@RequestBody TokenParam param, HttpServletRequest request) {
        return userService.getElderScreenList(param);
    }

    /**
     * 获取老人屏保
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteElderScreen")
    @ResponseBody
    public BasicData deleteElderScreen(@RequestBody ImageParam param, HttpServletRequest request) {
        return userService.deleteElderScreen(param);
    }

    /**
     * 设置提醒
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("setFireBaseToken")
    @ResponseBody
    public BasicData setFireBaseToken(@RequestBody FireBaseTokenParam param, HttpServletRequest request) {
        return userService.setFireBaseToken(param);
    }

    /**
     * 设置提醒
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("setNotifyType")
    @ResponseBody
    public BasicData setNotifyType(@RequestBody NotifyParam param, HttpServletRequest request) {
        return userService.setNotifyType(param);
    }


    /**
     * 检查token是否过期
     */
    @RequestMapping("checkToken")
    @ResponseBody
    public BasicData checkToken(@RequestParam String token, HttpServletRequest request) {
        return BasicData.CreateErrorInvalidUser();
    }


}
