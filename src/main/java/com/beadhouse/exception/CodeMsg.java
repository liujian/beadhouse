package com.beadhouse.exception;

public class CodeMsg {
	
	private int code;
	private String msg;
	
	//通用的错误码
	public static int COMMON_ERROR_CODE = 900;
	public static final int HEXIN_ERROR = 904;
	public static final int FACE_SYSTEM_ERROR_CODE = 700;
	public static int CAN_TRY_APPLY_TIMES_ERROR = 803;
	
	public static int LOCK_ERROR = 804;
	public static int REFUSED_CODE = 801;
	public static int REFUSED_BLACK_CODE=800;
	public static int NO_MONEY_CODE = 860;
	
	
	
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(990, "服务器异常");
	public static CodeMsg SMS_ERROR = new CodeMsg(910, "验证码错误，请重新输入");
	public static CodeMsg SMS_SEND_ERROR = new CodeMsg(911, "验证码发送失败");
	public static CodeMsg HAVE_REGISTER = new CodeMsg(COMMON_ERROR_CODE, "用户已注册");
	public static CodeMsg CHANGE_DEVICE_ERROR = new CodeMsg(910, "验证码错误");
	public static CodeMsg IMG_ERROR = new CodeMsg(920, "验证码错误，请重新输入");
	public static CodeMsg QUESTION_INTERNAL_ERROR = new CodeMsg(COMMON_ERROR_CODE, "您提交的太过频繁，请稍后再试");
	
	public static CodeMsg LOGIN_NEED_SMS = new CodeMsg(701, "长时间未登录");
	public static CodeMsg DEVICE_ERROR = new CodeMsg(403, "您当前使用的手机设备与绑定的设备不符，请验证后重新登录");
	public static CodeMsg APPLINGING = new CodeMsg(COMMON_ERROR_CODE, "正在申请中，不可重复申请");
	public static CodeMsg EXIST_SAME_CARD = new CodeMsg(COMMON_ERROR_CODE, "您的身份信息已申请过此产品，请勿重复申请");
	public static CodeMsg IDCARD_ERROR = new CodeMsg(COMMON_ERROR_CODE,"身份证号码格式错误，请检查后重新输入");
	public static CodeMsg IDCARD_DATE_ERROR = new CodeMsg(COMMON_ERROR_CODE,"签发日期有误，请检查后重新输入");
	public static CodeMsg IDCARD_EXPIRE_ERROR = new CodeMsg(COMMON_ERROR_CODE,"有效期限有误");
	public static CodeMsg PHONE_ERROR = new CodeMsg(COMMON_ERROR_CODE,"抱歉！银行卡预留手机号与注册手机号不匹配，无法通过验证");
	public static CodeMsg CLOSE_ACCOUNT = new CodeMsg(COMMON_ERROR_CODE,"账户不存在");
	
	public static CodeMsg WITHDRAW_CLEAR = new CodeMsg(COMMON_ERROR_CODE, "已结清，无记录");
	public static CodeMsg WITHDRAW_ING = new CodeMsg(811, "提现中，无记录");
	public static CodeMsg HEXIN_REFUSED = new CodeMsg(REFUSED_CODE, "经审核表明您暂时无法通过审核，请明日再试");
	public static CodeMsg WITHDRAW_REFUSED = new CodeMsg(REFUSED_CODE, "综合评分不足无法成功提现");
	public static CodeMsg HEXIN_BLACK_REFUSED = new CodeMsg(REFUSED_BLACK_CODE, "综合评分不足无法通过审核，请下次再试");
	
	public static CodeMsg HEXIN_DRAW_REFUSED = new CodeMsg(REFUSED_CODE, "经审核，您无法成功提现");
	
	public static CodeMsg EXCESS_APPLY_MAX_TIMES = new CodeMsg(802, "今日次数已达上限，请明日重新尝试");
	public static CodeMsg EXCESS_DAY_MAX_REGISTER = new CodeMsg(802, "抱歉，因系统繁忙，您暂时无法注册");
	public static CodeMsg EXCESS_MAX_APPLY = new CodeMsg(820, "抱歉，因系统繁忙，您暂时无法申请");
	public static CodeMsg ChECK_IDCARD_ERROR = new CodeMsg(COMMON_ERROR_CODE, "身份证号码不符，请检查后重新输入");
	
	public static CodeMsg FACE_SYSTEM_ERROR = new CodeMsg(FACE_SYSTEM_ERROR_CODE, "调用人脸系统出错");
	public static CodeMsg SYSTEM_HX_EXCEPTION = new CodeMsg(COMMON_ERROR_CODE, "抱歉，由于产品维护中，暂不可进行操作，请稍后再试");
	public static CodeMsg ACCESS_APPLY_MAX_DAYS = new CodeMsg(820, "尊敬的客户，您的额度申请有效期已超时，请您重新开始申请流程");
	public static CodeMsg NOT_LOGIN =  new CodeMsg(COMMON_ERROR_CODE, "用户未登录");
	public static CodeMsg NOT_APPLY =  new CodeMsg(COMMON_ERROR_CODE, "该用户还未实名认证");
	public static CodeMsg NOT_REPAY_PLAN =  new CodeMsg(COMMON_ERROR_CODE, "无还款计划数据");
	public static CodeMsg NOT_DRAW_MONEY =  new CodeMsg(COMMON_ERROR_CODE, "该用户还未成功提现");
	public static CodeMsg SAME_USING_CARD =  new CodeMsg(COMMON_ERROR_CODE, "银行卡已绑定");
	//validaor
	public static CodeMsg VALIDATOR_ERROR = new CodeMsg(COMMON_ERROR_CODE, "帐号操作过于频繁，请稍后再试");
	
	//销户提示
	public static CodeMsg ISCANCEL_LOIN_ERROR = new CodeMsg(COMMON_ERROR_CODE, "帐号不存在");
	public static CodeMsg ISCANCEL_REG_ERROR = new CodeMsg(COMMON_ERROR_CODE, "抱歉，此手机号暂无权限注册");
	
	public static CodeMsg SAME_PASSWORD = new CodeMsg(COMMON_ERROR_CODE, "新设密码不能与旧密码相同");
	//产品提示
	public static CodeMsg NOT_PRODUCT =  new CodeMsg(COMMON_ERROR_CODE, "未查到产品信息");
	public static CodeMsg PRODUCT_DOWN =  new CodeMsg(830, "抱歉，由于产品维护中，暂不可进行操作，请稍后再试");
	//增额提示
	public static CodeMsg NOT_ACCESS_ADD_DAYS = new CodeMsg(710, "抱歉，您尚未到再次提额时间，无法提升额度");
	public static CodeMsg ADD_LIMIT_NO_MIN_MONEY = new CodeMsg(710, "您已申请过提升额度，不可再次申请");
	public static CodeMsg ACCESS_ADD_FIRST_DAYS = new CodeMsg(711, "抱歉，您已超过有效时间，无法提升额度");
	public static CodeMsg ADD_LIMIT_ING = new CodeMsg(712, "抱歉，您的提额申请正在审核，请勿重复操作");
	public static CodeMsg ADD_LIMIT_NOT_APPLY = new CodeMsg(713, "未完成额度授信申请，不可进行提额操作");
	public static CodeMsg ADD_LIMIT_REFUSED = new CodeMsg(713, "您的申请尚未通过，暂无法提升额度");
	public static CodeMsg ACCESS_MAX_LIMIT = new CodeMsg(COMMON_ERROR_CODE, "已达到最高额度，暂无法提额");
	public static CodeMsg EXCESS_MAX_DAY = new CodeMsg(714, "*******");
	
	public static CodeMsg NO_USER = new CodeMsg(401, "登录超时");	
	public static CodeMsg NO_REG = new CodeMsg(COMMON_ERROR_CODE, "用户未注册");	
	public static CodeMsg NO_CAPITAL = new CodeMsg(COMMON_ERROR_CODE,"未找到对应的资方信息");
	
	public static CodeMsg IMG_TYPE_ERROR = new CodeMsg(COMMON_ERROR_CODE,"图片类型不正确");
	
	public static CodeMsg SECOND_RECHARGE_MAX_TIMES = new CodeMsg(851, "尊敬的用户，每日可充值次数已达上限，请明日再试");
	public static CodeMsg SECOND_WITHDRWA_MAX_TIMES = new CodeMsg(852, "尊敬的用户，每日可提现次数已达上限，请明日再试");
	
	public static CodeMsg NO_MONEY_CAPITAL = new CodeMsg(NO_MONEY_CODE, "本产品今日放款额度已用完，请于明日09:00后重新尝试");
	public static CodeMsg NO_MONEY_DRAW_APPLY = new CodeMsg(NO_MONEY_CODE, "今日额度不足或已用完，请稍后重新尝试");
	public static CodeMsg NO_MONEY_DRAW = new CodeMsg(NO_MONEY_CODE, "本产品今日放款额度已用完，请于明日09:00后重新尝试"); 
	
	public static CodeMsg  EXCESS_DRAW_MAX_DAY = new CodeMsg(861, "由于您长时间未完成放款，本次申请已经失效，请重新申请"); 
	
	private CodeMsg() {
	}
			
	public CodeMsg( int code,String msg ) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}
	
	
}
