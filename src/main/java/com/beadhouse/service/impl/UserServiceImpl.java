package com.beadhouse.service.impl;


import java.util.Date;

import com.beadhouse.in.*;
import com.beadhouse.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.beadhouse.dao.ContactsMapper;
import com.beadhouse.dao.ElderUserMapper;
import com.beadhouse.dao.UserMapper;
import com.beadhouse.domen.Contact;
import com.beadhouse.domen.ElderUser;
import com.beadhouse.domen.User;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.UserService;
import com.beadhouse.utils.Utils;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContactsMapper contactsmapper;
    @Autowired
    private ElderUserMapper elderUserMapper;
    @Autowired
    private RedisService redisService;
    @Value("${aws.SERVER_IMAGE}")
    private String SERVER_IMAGE;

    //private static Logger log = Logger.getLogger(UserServiceImpl.class);


    @Override
    public BasicData registration(RegistrationParam param) {
    	
    	 if (param.getEmailAddress() == null || param.getEmailAddress().isEmpty()) {
             return BasicData.CreateErrorMsg("Email address is empty");
         }
         if (param.getPassword() == null || param.getPassword().isEmpty()) {
             return BasicData.CreateErrorMsg("Password is empty");
         }
         if (param.getFirstName() == null || param.getFirstName().isEmpty()) {
             return BasicData.CreateErrorMsg("First name is empty");
         }
         if (param.getLastName() == null || param.getLastName().isEmpty()) {
             return BasicData.CreateErrorMsg("Last name is empty");
         }
         if (param.getBirthday() == null || param.getBirthday().isEmpty()) {
             return BasicData.CreateErrorMsg("Birthday is empty");
         }
         if (param.getCode() == null || param.getCode().isEmpty()) {
             return BasicData.CreateErrorMsg("Code is empty");
         }
         
    	String code =param.getCode();
    	String emailAddress=param.getEmailAddress();
    	if(!redisService.exists(emailAddress)){
    		return BasicData.CreateErrorMsg("The verification code has expired. Please revalidate the code!");
    	}else if(!redisService.getObj(emailAddress).equals(code)){
    		return BasicData.CreateErrorMsg("The verification code is incorrect. Please re input!");
    	}

        User user = userMapper.selectByEmailAddress(param.getEmailAddress());
        if (user != null) {
            return BasicData.CreateErrorMsg("This account already exists!");
        }
       

        user = new User();
        user.setEmailAddress(param.getEmailAddress());
        user.setPassword(param.getPassword());
        user.setFirstName(param.getFirstName());
        user.setLastName(param.getLastName());
        user.setPhone(param.getPhone());
        user.setBirthday(param.getBirthday());
        user.setCreateDate(new Date());

        userMapper.insertUser(user);
        String token = Utils.getToken();

        user.setToken(token);
        userMapper.updateToken(user);
        return BasicData.CreateSucess(user);
    }

    public BasicData login(LoginParam param) {
        String EmailAddress = param.getEmailAddress();
        String password = param.getPassword();
        if (EmailAddress == null || EmailAddress.isEmpty()) {
            return BasicData.CreateErrorMsg("Email address is empty");
        }
        if (password == null || password.isEmpty()) {
            return BasicData.CreateErrorMsg("Password is empty");
        }

        User user = userMapper.selectByEmailAddress(EmailAddress);
        if (user == null) {
            return BasicData.CreateErrorMsg("The username or password you entered is incorrect!");
        }
        if (!password.equals(user.getPassword())) {
            return BasicData.CreateErrorMsg("The username or password you entered is incorrect!");
        }

        String token = Utils.getToken();

        user.setToken(token);
        userMapper.updateToken(user);


        return BasicData.CreateSucess(user);
    }

    @Override
    public BasicData bindingContacts(ContactsParam param) {
         
        User user = userMapper.selectByToken(param.getToken());

        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }
        if (param.getCode() == null || param.getCode().isEmpty()) {
            return BasicData.CreateErrorMsg("Code is empty");
        }
        String eldercode="eldercode"+param.getCode();
        if(!redisService.exists(eldercode)){
        	 return BasicData.CreateErrorMsg("The verification code is invalid");
        }

        ElderUser elderUser = elderUserMapper.selectByElderUserEmail(redisService.getObj(eldercode).toString());
        if (elderUser == null) {
            return BasicData.CreateErrorMsg("The affiliate account does not exist!");
        }
        Contact contact = new Contact();
        contact.setLoginUserId(user.getId());
        contact.setElderUserId(elderUser.getElderUserId());
        contact.setCreateDate(new Date());
        Contact cont = contactsmapper.selectBycontacts(contact);
        if (cont != null) {
            return BasicData.CreateErrorMsg("Repeat binding account");
        }

        contactsmapper.insertContact(contact);
        return BasicData.CreateSucess();
    }

    @Override
    public BasicData changePassword(ChangePasswordParam param) {
        User user = userMapper.selectByToken(param.getToken());
        if (user == null) return BasicData.CreateErrorInvalidUser();

        String oldPassword = param.getOldPassword();
        String newPassword = param.getNewPassword();
        if (oldPassword == null || oldPassword.isEmpty()) {
            return BasicData.CreateErrorMsg("The old password is empty");
        }
        if (newPassword == null || newPassword.isEmpty()) {
            return BasicData.CreateErrorMsg("The new password is empty");
        }

        if (!oldPassword.equals(user.getPassword())) {
            return BasicData.CreateErrorMsg("The old password is wrong, please re-enter the old password!");
        }
        user.setPassword(param.getNewPassword());
        user.setUpdateDate(new Date());
        userMapper.updatePassword(user);
        return BasicData.CreateSucess();
    }

    @Override
    public BasicData updateUserInfo(UserInfoParam param) {
        User user = userMapper.selectByToken(param.getToken());
        if (user == null) return BasicData.CreateErrorInvalidUser();

        String firstName = param.getFirstName();
        String lastName = param.getLastName();
        String phone = param.getPhone();
        String birthday = param.getBirthday();
        if (firstName == null || firstName.isEmpty()) {
            return BasicData.CreateErrorMsg("The first name is empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            return BasicData.CreateErrorMsg("The last name is empty");
        }
        if (phone == null || phone.isEmpty()) {
            return BasicData.CreateErrorMsg("The phone is empty");
        }
        if (birthday == null || birthday.isEmpty()) {
            return BasicData.CreateErrorMsg("The birthday is empty");
        }

        user.setFirstName(param.getFirstName());
        user.setLastName(param.getLastName());
        user.setPhone(param.getPhone());
        user.setBirthday(param.getBirthday());
        user.setUpdateDate(new Date());
        userMapper.updateUserInfo(user);

        return BasicData.CreateSucess(user);
    }

    @Override
    public BasicData updateUserAvatar(TokenParam param, String filePath) {
        User user = userMapper.selectByToken(param.getToken());
        if (user == null) return BasicData.CreateErrorInvalidUser();
        user.setUserAvatar(SERVER_IMAGE + filePath);
        userMapper.updateUserAvatar(user);

        return BasicData.CreateSucess(SERVER_IMAGE + filePath);
    }

    @Override
    public BasicData getUserInfo(TokenParam param) {
        User user = userMapper.selectByToken(param.getToken());
        if (user == null) return BasicData.CreateErrorInvalidUser();
        return BasicData.CreateSucess(user);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Value("${redis.expiry}")
    private Long expiry;
    
	@Override
	public boolean sendCode(GetCodeParam param, String code) {
		String emailAddress =param.getEmailAddress();
		String msg=null;
		if("1".equals(param.getType())){
			 msg ="Verifying code is："+ code+"(Registration verification code for nursing homes should be completed within "+expiry+" minutes)。";
		}else{
			 msg ="Reset code is："+ code+"(Reset passwordn code for nursing homes should be completed within "+expiry+" minutes)。";
		}
		try {
			//邮箱发送验证码
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom(sender);
	        message.setTo(emailAddress); //自己给自己发送邮件
	        message.setSubject("theme：App verification code for nursing home");
	        message.setText(msg);
	        System.out.println("Start sending mail！");
	        System.out.println("Mailbox content："+msg);
	        mailSender.send(message);
	        System.out.println("Mail sent successfully！");
			//保存验证码至redis数据库并设置失效时间为5分钟
		   redisService.set(emailAddress, code, expiry);
		} catch (Exception e) {
			e.printStackTrace();
			return false;		
		}
		return true;
	}

	@Override
	public BasicData forgetPassword(NewPasswordParam param) {
		if (param.getEmailAddress() == null || param.getEmailAddress().isEmpty()) {
            return BasicData.CreateErrorMsg("Email address is empty");
        }
        if (param.getNewPassword() == null || param.getNewPassword().isEmpty()) {
            return BasicData.CreateErrorMsg("NewPassword is empty");
        }
        
        if (param.getCode() == null || param.getCode().isEmpty()) {
            return BasicData.CreateErrorMsg("Code is empty");
        }
     	String code =param.getCode();
   	    String emailAddress=param.getEmailAddress();
     	if(!redisService.exists(emailAddress)){
   		    return BasicData.CreateErrorMsg("The verification code has expired. Please revalidate the code!");
   	    }else if(!redisService.getObj(emailAddress).equals(code)){
   		    return BasicData.CreateErrorMsg("The verification code is incorrect. Please re input!");
   	    }
     	
 	   User user = userMapper.selectByEmailAddress(param.getEmailAddress());
       if (user == null) {
           return BasicData.CreateErrorMsg("This account does not exist!");
       }
       user.setPassword(param.getNewPassword());
       user.setUpdateDate(new Date());
       userMapper.updatePassword(user);
       
	 	 return BasicData.CreateSucess(user);
	  }
}
