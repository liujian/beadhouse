package com.beadhouse.service;

import com.beadhouse.in.*;
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

    /**
     * 老人求问题
     */
    BasicData toAskMe(AskMeParam param);

    /**
     * 获取日程
     * @return
     */
    BasicData getSchedule(TokenParam param);

}
