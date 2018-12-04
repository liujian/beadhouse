package com.beadhouse.service;


import com.beadhouse.in.*;
import com.beadhouse.out.BasicData;


public interface UserService {

	
	/**
	 * 忘记密码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	 BasicData forgetPassword(NewPasswordParam param);

    /**
     * 用户登录
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData login(LoginParam param);

    /**
     * 用户登录 (Google 和 facebook)
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData loginGoogleOrFacebook(GoogleAndFacebookParam param);


    /**
	 * 发送验证码
	 * @param phone
	 * @throws Exception
	 */
	boolean sendCode(GetCodeParam param,String code);
    
    
    /**
     * 用户注册
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData registration(RegistrationParam param);


    /**
     * 关联联系人
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData bindingContacts(ContactsParam param);

    /**
     * 修改密码
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData changePassword(ChangePasswordParam param);

    /**
     * 更新用户信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData updateUserInfo(UserInfoParam param);

    /**
     * 获取用户信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData getUserInfo(TokenParam param);

    /**
     * 更新用户头像
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData updateUserAvatar(TokenParam param, String filePath);

    /**
     * 上传老人屏保
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData uploadElderScreen(TokenParam param, String filePath);

    /**
     * 设置提醒
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData setNotifyType(NotifyParam param);
}
