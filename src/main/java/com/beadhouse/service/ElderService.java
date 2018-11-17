package com.beadhouse.service;

import com.beadhouse.in.ChangePasswordParam;
import com.beadhouse.in.ContactsParam;
import com.beadhouse.in.ElderInfoParam;
import com.beadhouse.in.ElderRegistrationParam;
import com.beadhouse.in.ElderSendCodeParam;
import com.beadhouse.in.GetCodeParam;
import com.beadhouse.in.LoginParam;
import com.beadhouse.in.NewPasswordParam;
import com.beadhouse.in.RegistrationParam;
import com.beadhouse.in.TokenParam;
import com.beadhouse.in.UserInfoParam;
import com.beadhouse.out.BasicData;

public interface ElderService {
	

	/**
	 * 忘记密码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	 BasicData forgetPassword(NewPasswordParam param);


    /**
     * 老人登录
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData elderUserLogin(LoginParam param);

    
    /**
     * 用户注册
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData registration(ElderRegistrationParam param);


    /**
     * 修改密码
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData changePassword(ChangePasswordParam param);

    /**
     * 更改老人信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData updateElderInfo(ElderInfoParam param);

    /**
     * 获取老人信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData getElderInfo(TokenParam param);

    /**
     * 更改老人头像
     *
     * @param param
     * @return
     * @throws Exception
     */
    BasicData updateElderAvatar(TokenParam param, String filePath);
    
    /**
     * 获取绑定家人
     */
    BasicData getElderContacts(TokenParam param);
    
    /**
     * 获取绑定码
     */
    BasicData getElderCode(TokenParam param);
  
    /**
     * 发送验证码至指定邮箱
     */
    BasicData sendCode(ElderSendCodeParam param);
    
    /**
     * 获取老人等待回答的问题
     */
    BasicData getwaitquests(TokenParam param);
    
    
}
